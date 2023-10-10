package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.ProductNotFoundException;
import com.experis.no.boxinator.mappers.OrderMapper;
import com.experis.no.boxinator.models.Orders;
import com.experis.no.boxinator.models.dto.order.OrderDTO;
import com.experis.no.boxinator.models.dto.order.OrderPostDTO;
import com.experis.no.boxinator.models.dto.orderProduct.OrderProductWithFullProductDTO;
import com.experis.no.boxinator.models.dto.product.ProductDTO;
import com.experis.no.boxinator.services.orders.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    private final OrdersService ordersService;
    private final OrderMapper orderMapper;


    public OrderController(OrdersService ordersService, OrderMapper orderMapper) {
        this.ordersService = ordersService;
        this.orderMapper = orderMapper;
    }
    @GetMapping
    @Operation(summary = "Gets all Orders")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
                    }
            )
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                orderMapper.orderToOrderDTO(
                        ordersService.findAll()
                )
        );
    }
    @GetMapping(params = "fullProduct")
    @Operation(summary = "Gets all order with full product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderProductWithFullProductDTO.class)))
                    }
            )
    })
    public ResponseEntity<?> findAll(@PathParam("fullProduct") boolean fullProduct) {
        if(fullProduct)
            return ResponseEntity.ok(
                    orderMapper.orderWithProductsDTO(
                            ordersService.findAll()
                    )
            );
        else
            return ResponseEntity.ok(
                    orderMapper.orderToOrderDTO(
                            ordersService.findAll()
                    )
            );
    }
    @GetMapping(params = "userId")
    @Operation(summary = "Gets all order for a given user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderProductWithFullProductDTO.class)))
                    }
            )
    })
    public ResponseEntity<?> findAllFromUserId(@PathParam("userId") int userId) {
        if(userId != 0)
            return ResponseEntity.ok(
                    orderMapper.orderToOrderDTO(
                            ordersService.findAllByUserId(userId)
                    )
            );
        else
            return ResponseEntity.badRequest().build();
    }
    @GetMapping("{id}")
    @Operation(summary = "Gets a order by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                    orderMapper.orderToOrderDTO(
                            ordersService.findById(id)
                    )

            );
        } catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Adds a new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity<?> add(@RequestBody OrderPostDTO entity) throws URISyntaxException {
        Orders orders = ordersService.add(orderMapper.ordersPostDTOToOrders(entity));
        URI uri = new URI("api/v1/order/" + orders.getId());
        return ResponseEntity.created(uri).build();
    }
}
