package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.ShipmentNotFoundException;
import com.experis.no.boxinator.mappers.ShipmentHistoryMapper;
import com.experis.no.boxinator.mappers.ShipmentMapper;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.models.dto.shipment.ShipmentDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentPostDTO;
import com.experis.no.boxinator.models.dto.shipmentHistory.ShipmentHistoryDTO;
import com.experis.no.boxinator.services.shipment.ShipmentService;
import com.experis.no.boxinator.services.shipmenthistory.ShipmentHistoryService;
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

@RestController
@RequestMapping(path = "api/v1/shipment")
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final ShipmentHistoryService historyService;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentHistoryMapper historyMapper;

    public ShipmentController(ShipmentService shipmentService, ShipmentHistoryService historyService, ShipmentMapper shipmentMapper, ShipmentHistoryMapper historyMapper) {
        this.shipmentService = shipmentService;
        this.historyService = historyService;
        this.shipmentMapper = shipmentMapper;
        this.historyMapper = historyMapper;
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
        if (email != null) {
            try {
                return ResponseEntity.ok(
                        shipmentMapper.shipmentToShipmentDTO(
                                shipmentService.findByEmail(email)
                        )
                );
            } catch (ShipmentNotFoundException shipmentNotFoundException) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.ok(
                    shipmentMapper.shipmentToShipmentDTO(
                            shipmentService.findAll()
                    )
            );
        }
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a Shipment by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentHistoryDTO.class))
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
                    shipmentMapper.shipmentToShipmentDTO(
                            shipmentService.findById(id)
                    )

            );
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Adds a new shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity<?> add(@RequestBody ShipmentPostDTO entity) {
        Shipment shipment = shipmentService.add(shipmentMapper.shipmentPostDTOToShipment(entity));
        URI uri;
        try {
            uri = new URI("api/v1/shipment/" + shipment.getId());
        } catch (URISyntaxException e) {
            return ResponseEntity.internalServerError().build();
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
    public ResponseEntity<?> updateShipment(@RequestBody ShipmentDTO entity) {
        try {
            return ResponseEntity.ok(
                    shipmentMapper.shipmentToShipmentDTO(
                            shipmentService.update(shipmentMapper.shipmentDTOToShipment(entity))
                    )

            );
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
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
                    historyMapper.shipmentHistoryToShipmentHistoryDTO(historyService.findAllByShipmentID(id))

            );
        } catch (ShipmentNotFoundException shipmentNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
}
