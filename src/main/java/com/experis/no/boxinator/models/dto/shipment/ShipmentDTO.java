package com.experis.no.boxinator.models.dto.shipment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ShipmentDTO {
    private int id;
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
    private Timestamp timestamp;
    private Boolean gift;
}
