package com.experis.no.boxinator.models.dto.orderProduct;
import com.experis.no.boxinator.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentProductWithFullProductDTO {
    public ShipmentProductWithFullProductDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    private Product product;
    private int quantity;
}
