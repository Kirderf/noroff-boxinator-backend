package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.models.dto.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductDTO productToProductDTO(Product product);


    public abstract Product productDTOToProduct(ProductDTO productDTO);


    public abstract Collection<ProductDTO> productToProductDTO(Collection<Product> productCollection);

}
