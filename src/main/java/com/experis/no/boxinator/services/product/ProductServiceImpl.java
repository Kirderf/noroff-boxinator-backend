package com.experis.no.boxinator.services.product;

import com.experis.no.boxinator.exceptions.ProductNotFoundException;
import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Integer integer) {
        return productRepository.findById(integer).orElseThrow(() -> new ProductNotFoundException(integer));
    }

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product add(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void delete(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return productRepository.existsById(integer);
    }

    @Override
    public Collection<Product> getAllActive(boolean isActive) {
        return productRepository.getProductsByisActiveIs(isActive);
    }
}
