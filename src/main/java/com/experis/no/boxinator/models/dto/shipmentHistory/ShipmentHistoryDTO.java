package com.experis.no.boxinator.models.dto.shipmentHistory;

import com.experis.no.boxinator.models.Status;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ShipmentHistoryDTO {
    private int id;
    private Status status;
    private Timestamp timestamp;
}
