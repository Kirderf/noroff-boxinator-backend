package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.ShipmentHistoryNotFoundException;
import com.experis.no.boxinator.exceptions.ShipmentNotFoundException;
import com.experis.no.boxinator.mappers.ShipmentHistoryMapper;
import com.experis.no.boxinator.mappers.ShipmentMapper;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.models.ShipmentProduct;
import com.experis.no.boxinator.models.dto.orderProduct.ShipmentProductDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentPostDTO;
import com.experis.no.boxinator.services.OrdersProduct.ShipmentProductsService;
import com.experis.no.boxinator.services.product.ProductService;
import com.experis.no.boxinator.services.shipment.ShipmentService;
import com.experis.no.boxinator.services.shipmenthistory.ShipmentHistoryService;
import com.experis.no.boxinator.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/shipment")
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final UserService userService;
    private final ShipmentHistoryService historyService;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentProductsService shipmentProductsService;
    private final ShipmentHistoryMapper historyMapper;
    private final ProductService productService;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ShipmentController(ShipmentService shipmentService, UserService userService, ShipmentHistoryService historyService, ShipmentMapper shipmentMapper, ShipmentProductsService shipmentProductsService, ShipmentHistoryMapper historyMapper, ProductService productService) {
        this.shipmentService = shipmentService;
        this.userService = userService;
        this.historyService = historyService;
        this.shipmentMapper = shipmentMapper;
        this.shipmentProductsService = shipmentProductsService;
        this.historyMapper = historyMapper;
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Gets all Shipments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ShipmentDTO.class)))
                    }
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(@RequestParam(required = false) String email) {
        try {
            if (email != null) {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentDTO(
                                shipmentService.findByEmail(email)
                        )
                );
            } else {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentDTO(
                                shipmentService.findAll()
                        )
                );
            }
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a Shipment by UserID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ShipmentDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasAuthority('ID_' + #id) or hasRole('ADMIN')")
    public ResponseEntity<?> findByUserId(@PathVariable String id, @RequestParam(required = false) Boolean fullProduct, @RequestParam(required = false) Boolean guest) {
        if (fullProduct == null) fullProduct = false;
        if (guest == null) guest = false;
        try {
            if (guest) {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentWithFullProductDTO(
                                shipmentService.findByEmailAndGuest(
                                        userService.findById(id).getEmail()
                                )
                        )
                );
            }
            if (!fullProduct) {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentDTO(
                                shipmentService.findByUserID(id)
                        )
                );
            } else {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentWithFullProductDTO(
                                shipmentService.findByUserID(id)
                        )

                );
            }
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{shipmentId}/{userid}")
    @Operation(summary = "Updates a Shipment by Email")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ShipmentDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasAuthority('ID_' + #userid) or hasRole('ADMIN')")
    public ResponseEntity<?> updateByShipmentID(@PathVariable Integer shipmentId, @PathVariable String userid, @RequestParam(required = false) Boolean fullProduct) {
        if (fullProduct == null) fullProduct = false;
        try {
            Shipment shipment = shipmentService.findById(shipmentId);
            if (shipment.getEmail().equals(userService.findById(userid).getEmail())) {
                shipment.setUser(userService.findById(userid));
                shipmentService.update(shipment);
                if (!fullProduct) {
                    return ResponseEntity.ok(
                            shipmentMapper.shipmentToShipmentDTO(
                                    shipment
                            )
                    );
                } else {
                    return ResponseEntity.ok(
                            shipmentMapper.shipmentToShipmentWithFullProductDTO(
                                    shipment
                            )

                    );
                }
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Adds a new shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShipmentPostDTO.class))
            )
    })
    public ResponseEntity<?> add(@RequestBody ShipmentPostDTO entity) {
        URI uri;
        try {
            Shipment shipment = shipmentService.add(shipmentMapper.shipmentPostDTOToShipment(entity));
            for (ShipmentProductDTO dto : entity.getShipmentProducts()
            ) {
                ShipmentProduct shipmentProduct = new ShipmentProduct();
                shipmentProduct.setShipment(shipment);
                shipmentProduct.setQuantity(dto.getQuantity());
                shipmentProduct.setProduct(productService.findById(dto.getProductId()));
                shipmentProductsService.add(shipmentProduct);
            }
            updateShipmentHistory(shipment);
            uri = new URI("api/v1/shipment/" + shipment.getId());
        } catch (URISyntaxException e) {
            return ResponseEntity.internalServerError().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping
    @Operation(summary = "Update a Shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateShipment(@RequestBody ShipmentDTO entity) {
        try {
            logger.log(Level.INFO, "Mapping a shipmentDTO");
            Shipment shipment = shipmentMapper.shipmentDTOToShipment(entity);
            logger.log(Level.INFO, "Adding a shipmentHistory");
            updateShipmentHistory(shipment);
            logger.log(Level.INFO, "Updating a shipment");
            return ResponseEntity.ok(
                    shipmentMapper.shipmentToShipmentDTO(
                            shipmentService.update(shipment)
                    )

            );
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("{id}/history")
    @Operation(summary = "Gets a Shipments History by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentHistory.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<?> findHistoryById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                    historyMapper.shipmentHistoryToShipmentHistoryDTO(
                            historyService.findAllByShipmentID(id))

            );
        } catch (ShipmentHistoryNotFoundException shipmentHistoryNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    private void updateShipmentHistory(Shipment shipment) throws ShipmentHistoryNotFoundException {
        ShipmentHistory shipmentHistory = new ShipmentHistory();
        shipmentHistory.setStatus(shipment.getStatus());
        shipmentHistory.setShipment(shipment);
        shipmentHistory.setTimestamp(Timestamp.from(Instant.now()));
        historyService.updateShipmentHistory(shipmentHistory);
    }
}
