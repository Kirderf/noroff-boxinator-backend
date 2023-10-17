package com.experis.no.boxinator.models.dto.shipment;

import com.experis.no.boxinator.models.dto.orderProduct.ShipmentProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShipmentPostDTO {
    private String user;
    private List<ShipmentProductDTO> shipmentProducts;
    private String email;
    private String billingAddress;
    private String deliveryInstruction;
    private String destination;
    private String countries;
    private String city;
    private String phoneNumber;
    private int postalCode;
    private String status;
    private Boolean gift;
}
