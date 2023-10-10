package com.experis.no.boxinator.models.dto.orderProduct;

import com.experis.no.boxinator.models.dto.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductWithFullProductDTO {
    private int quantity;
    private ProductDTO product;

    public OrderProductWithFullProductDTO(ProductDTO product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }
}
