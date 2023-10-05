package com.experis.no.boxinator.models.dto.shipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentDTO {
    private int id;
    private Boolean gift;
    private int order_id;
    private String billing_address;
    private String delivery_instruction;
    private String destination;
    private String countries;
}
