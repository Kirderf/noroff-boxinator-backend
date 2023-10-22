package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.models.dto.shipmentHistory.ShipmentHistoryDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ShipmentHistoryMapper {

    public abstract ShipmentHistoryDTO shipmentHistoryToShipmentHistoryDTO(ShipmentHistory shipmentHistory);

    public abstract Collection<ShipmentHistoryDTO> shipmentHistoryToShipmentHistoryDTO(Collection<ShipmentHistory> shipmentHistoriesCollection);
}
