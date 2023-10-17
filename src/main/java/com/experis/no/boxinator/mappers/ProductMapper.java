package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.models.dto.product.ProductDTO;
import com.experis.no.boxinator.models.dto.product.ProductPostDTO;
import org.mapstruct.Mapper;

import java.util.Collection;


@Mapper(componentModel = "spring")
public abstract class ProductMapper {


    public abstract ProductDTO productToProductDTO(Product product);

    public abstract Product productPostDTOToProduct(ProductPostDTO productPostDTO);

    public abstract Product productDTOToProduct(ProductDTO productDTO);


    public abstract Collection<ProductDTO> productToProductDTO(Collection<Product> productCollection);


}
