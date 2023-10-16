package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.OrdersNotFoundException;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(@RequestParam(required = false) Boolean fullProduct) {
        if (fullProduct == null) fullProduct = false;
        if (!fullProduct)
            return ResponseEntity.ok(
                    orderMapper.orderToOrderDTO(
                            ordersService.findAll()
                    )
            );
        else
            return ResponseEntity.ok(
                    orderMapper.orderWithProductsDTO(
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
    @PreAuthorize("hasAuthority('ID_' + #userId) or hasRole('ADMIN')")
    public ResponseEntity<?> findAllFromUserId(@PathParam("userId") String userId, @RequestParam(required = false) Boolean fullProduct) {
        if (fullProduct == null) fullProduct = false;
        if (!fullProduct)
            return ResponseEntity.ok(
                    orderMapper.orderToOrderDTO(
                            ordersService.findAllByUserId(userId)
                    )
            );
        else {
            return ResponseEntity.ok(
                    orderMapper.orderWithProductsDTO(
                            ordersService.findAllByUserId(userId)
                    )
            );
        }
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
    @PreAuthorize("hasAuthority('ID_' + #uid) or hasRole('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable int id, @RequestParam(required = false) Boolean fullProduct, String uid) {
        try {
            //check if user has the right access to this method
            boolean foundUserWithUid = false;
            if (!hasUserRole("ADMIN")) {
                Orders order = ordersService.findById(id);
                if (order != null) {
                    if (order.getUser() != null) {
                        foundUserWithUid = (order.getUser().getId().equals(uid));
                    }
                }
                if (foundUserWithUid) {
                    throw new OrdersNotFoundException(id);
                }
            }


            if (fullProduct == null) fullProduct = false;
            if (!fullProduct)
                return ResponseEntity.ok(
                        orderMapper.orderToOrderDTO(
                                ordersService.findById(id)
                        )

                );
            else
                return ResponseEntity.ok(
                        orderMapper.orderWithProductsDTO(
                                ordersService.findById(id)
                        )
                );
        } catch (OrdersNotFoundException ordersNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Adds a new order")
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

    private boolean hasUserRole(String role) {
        // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities() != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (("ROLE_" + role).equals(authority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }
}
