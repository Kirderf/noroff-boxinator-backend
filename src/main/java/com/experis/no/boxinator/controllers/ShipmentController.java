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
import com.experis.no.boxinator.models.dto.shipment.ShipmentWithFullProductDTO;
import com.experis.no.boxinator.models.dto.shipmentHistory.ShipmentHistoryDTO;
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
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
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

    /**
     * Fetches all shipments or shipments associated with an email.
     *
     * @param email Filter shipments by email. If null, all shipments are returned.
     * @return ResponseEntity containing a list of shipments.
     */
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
        logger.info("Starting findAll method");
        try {
            if (email != null) {
                Collection<ShipmentDTO> dtos = shipmentMapper.shipmentToShipmentDTO(
                        shipmentService.findByEmail(email)
                );
                logger.info("Successfully fetched shipments for email: " + email);
                return ResponseEntity.ok(dtos);
            } else {
                Collection<ShipmentDTO> dtos = shipmentMapper.shipmentToShipmentDTO(
                        shipmentService.findAll()
                );
                logger.info("Successfully fetched all shipments");
                return ResponseEntity.ok(dtos);
            }
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            logger.warning("Shipment not found for email: " + email + ". Error: " + shipmentNotFoundException.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Fetches a shipment by user ID.
     *
     * @param id          The user ID.
     * @param fullProduct Flag to determine if full product details should be included.
     * @param guest       Flag to identify if getting guest users
     * @return ResponseEntity containing shipment details.
     */
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
        logger.info("Starting findByUserId method. Fetching shipment for user ID: " + id);
        if (fullProduct == null) fullProduct = false;
        if (guest == null) guest = false;
        try {
            if (guest) {
                Collection<ShipmentWithFullProductDTO> dtos = shipmentMapper.shipmentToShipmentWithFullProductDTO(
                        shipmentService.findByEmailAndGuest(
                                userService.findById(id).getEmail()
                        )
                );
                logger.info("Successfully fetched shipment for user ID: " + id);
                return ResponseEntity.ok(dtos);
            }
            if (!fullProduct) {
                Collection<ShipmentDTO> dtos = shipmentMapper.shipmentToShipmentDTO(shipmentService.findByUserID(id));
                logger.info("Successfully fetched shipment for user ID: " + id);
                return ResponseEntity.ok(dtos);
            } else {
                Collection<ShipmentWithFullProductDTO> dtos = shipmentMapper.shipmentToShipmentWithFullProductDTO(
                        shipmentService.findByUserID(id)
                );
                logger.info("Successfully fetched shipment for user ID: " + id);
                return ResponseEntity.ok(dtos);
            }
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a shipment by the given shipment ID and user ID.
     *
     * @param shipmentId  The shipment ID.
     * @param userid      The user ID.
     * @param fullProduct Flag to determine if full product details should be included.
     * @return ResponseEntity containing updated shipment details.
     */
    @PatchMapping("{shipmentId}/{userid}")
    @Operation(summary = "Updates a Shipments Email by UserID")
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
        logger.info("Starting updateByShipmentID method. Updating shipment with shipment ID: " + shipmentId + " for user ID: " + userid);
        if (fullProduct == null) fullProduct = false;
        try {
            Shipment shipment = shipmentService.findById(shipmentId);
            if (shipment.getEmail().equals(userService.findById(userid).getEmail())) {
                shipment.setUser(userService.findById(userid));
                shipmentService.update(shipment);
                if (!fullProduct) {
                    ShipmentDTO dto = shipmentMapper.shipmentToShipmentDTO(shipment);
                    logger.info("Successfully updated shipment with shipment ID: " + shipmentId);
                    return ResponseEntity.ok(dto);
                } else {
                    ShipmentWithFullProductDTO dto = shipmentMapper.shipmentToShipmentWithFullProductDTO(shipment);
                    logger.info("Successfully updated shipment with shipment ID: " + shipmentId);
                    return ResponseEntity.ok(dto);
                }
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Adds a new shipment.
     *
     * @param entity The shipment details.
     * @return ResponseEntity indicating the result of the operation.
     */
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
        logger.info("Starting the add method. Adding a new shipment.");
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
            logger.info("Successfully added a new shipment.");
            return ResponseEntity.created(uri).build();
        } catch (Exception exception) {
            logger.warning("Failed to add a new shipment.");
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Updates a shipment.
     *
     * @param entity The shipment details.
     * @return ResponseEntity indicating the result of the operation.
     */
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
        logger.info("Starting the updateShipment method.");
        try {
            Shipment shipment = shipmentMapper.shipmentDTOToShipment(entity);
            ShipmentDTO dto = shipmentMapper.shipmentToShipmentDTO(
                    shipmentService.update(shipment)
            );
            logger.info("Successfully updated the shipment.");
            return ResponseEntity.ok(dto);
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            logger.warning("Failed to update the shipment.");
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            logger.warning("Failed to update the shipment.");
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Fetches a shipment's history by its ID.
     *
     * @param id The shipment ID.
     * @return ResponseEntity containing the shipment's history.
     */
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
        logger.info("Starting the findHistoryById method. Fetching history for shipment ID: " + id);
        try {
            Collection<ShipmentHistoryDTO> shipments = historyMapper.shipmentHistoryToShipmentHistoryDTO(
                    historyService.findAllByShipmentID(id));
            logger.info("Successfully fetched history for shipment ID: " + id);
            return ResponseEntity.ok(shipments);
        } catch (ShipmentHistoryNotFoundException shipmentHistoryNotFoundException) {
            logger.warning("Failed to fetch history for shipment ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates the history for a shipment.
     *
     * @param shipment The shipment whose history needs to be updated.
     * @throws ShipmentHistoryNotFoundException if the shipment history is not found.
     */
    private void updateShipmentHistory(Shipment shipment) throws ShipmentHistoryNotFoundException {
        logger.info("Starting the updateShipmentHistory method. Updating history for shipment.");
        ShipmentHistory shipmentHistory = new ShipmentHistory();
        shipmentHistory.setStatus(shipment.getStatus());
        shipmentHistory.setShipment(shipment);
        shipmentHistory.setTimestamp(Timestamp.from(Instant.now()));
        historyService.updateShipmentHistory(shipmentHistory);
        logger.info("Successfully updated the shipment history.");
    }
}
