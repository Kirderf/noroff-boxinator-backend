package com.experis.no.boxinator.models.dto.shipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentPostDTO {
    private String email;
    private Integer order;
    private String billingAddress;
    private String deliveryInstruction;
    private String destination;
    private String countries;
    private String city;
    private long phoneNumber;
    private int postalCode;
    private String status;
    private Boolean gift;
}
