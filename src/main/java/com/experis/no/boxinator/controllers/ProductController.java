package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.ProductNotFoundException;
import com.experis.no.boxinator.mappers.ProductMapper;
import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.models.dto.product.ProductDTO;
import com.experis.no.boxinator.models.dto.product.ProductPostDTO;
import com.experis.no.boxinator.services.product.ProductService;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    @Operation(summary = "Gets all Products")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll() {
        logger.info("Getting all products");
        Collection<ProductDTO> dtos = productMapper.productToProductDTO(
                productService.findAll()
        );
        logger.info("Found " + dtos.size() + " products");
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(params = "active")
    @Operation(summary = "Gets all active Products")
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
    public ResponseEntity<?> findAll(@PathParam("active") boolean active) {
        logger.info("Getting all active products");
        Collection<ProductDTO> dtos = productMapper.productToProductDTO(
                productService.findAll(active)
        );
        logger.info("Found " + dtos.size() + " active products");
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a product by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDTO.class))
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
        logger.info("Getting product with id " + id);
        try {
            ProductDTO dto = productMapper.productToProductDTO(
                    productService.findById(id)
            );
            logger.info("Found product with id " + id);
            return ResponseEntity.ok(dto);
        } catch (ProductNotFoundException productNotFoundException) {
            logger.warning("Product with id " + id + " not found");
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
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody ProductPostDTO entity) throws URISyntaxException {
        logger.info("Adding new product");
        Product product = productService.add(productMapper.productPostDTOToProduct(entity));
        URI uri = new URI("api/v1/product/" + product.getId());
        logger.info("Added new product with id " + product.getId());
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping
    @Operation(summary = "Updates a Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = Product.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody ProductDTO entity) {
        logger.info("Updating product with id " + entity.getId());
        try {
            if (!productService.exists(entity.getId())) {
                throw new ProductNotFoundException(entity.getId());
            }
            Product product = productService.update(
                    productMapper.productDTOToProduct(entity)
            );
            logger.info("Updated product with id " + entity.getId());
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException exception) {
            logger.warning("Product with id " + entity.getId() + " not found");
            return ResponseEntity.badRequest().build();
        }
    }
}
