package com.experis.no.boxinator.models.dto.order;

import com.experis.no.boxinator.models.dto.orderProduct.OrderProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderPostDTO {
    private int user;
    private List<OrderProductDTO> ordersProducts;
}
