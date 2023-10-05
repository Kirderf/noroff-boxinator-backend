package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.mappers.ProductMapper;
import com.experis.no.boxinator.services.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/product")
public class productController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public productController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }
   @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                productMapper.productToProductDTO(
                        productService.findAll()
                )
        );
    }

}
