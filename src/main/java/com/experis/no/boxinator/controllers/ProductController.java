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
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

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
           )
   })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                productMapper.productToProductDTO(
                        productService.findAll()
                )
        );
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
        try {
            return ResponseEntity.ok(
                    productMapper.productToProductDTO(
                            productService.findById(id)
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
    public ResponseEntity<?> add(@RequestBody ProductPostDTO entity) throws URISyntaxException {
        Product product = productService.add(productMapper.productPostDTOToProduct(entity));
        URI uri = new URI("api/v1/product/" + product.getId());
        return ResponseEntity.created(uri).build();
    }
}
