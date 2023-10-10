package com.experis.no.boxinator.models.dto.order;
import com.experis.no.boxinator.models.dto.orderProduct.OrderProductWithFullProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderWithProductsDTO {
    private int id;
    private int user;
    private List<OrderProductWithFullProductDTO> ordersProducts;
}
