package com.experis.no.boxinator.models.dto.shipment;

import com.experis.no.boxinator.models.dto.orderProduct.ShipmentProductWithFullProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ShipmentWithFullProductDTO {
    private int id;
    private String email;
    private String billingAddress;
    private String deliveryInstruction;
    private String shippingAddress;
    private String countries;
    private String city;
    private String phoneNumber;
    private int postalCode;
    private String status;
    private Timestamp timestamp;
    private Boolean gift;
    private String user;
    private List<ShipmentProductWithFullProductDTO> shipmentProducts;
}