package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.models.Status;
import com.experis.no.boxinator.models.dto.shipmentHistory.ShipmentHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ShipmentHistoryMapper {

    @Mapping(target = "status", qualifiedByName = "mapStatus")
    public abstract ShipmentHistoryDTO shipmentHistoryToShipmentHistoryDTO(ShipmentHistory shipmentHistory);

    @Mapping(target = "status", qualifiedByName = "unmapStatus")
    public abstract ShipmentHistory shipmentHistoryDTOToShipmentHistory(ShipmentHistoryDTO shipmentHistoryDTO);


    public abstract Collection<ShipmentHistoryDTO> shipmentHistoryToShipmentHistoryDTO(Collection<ShipmentHistory> shipmentHistoriesCollection);

    @Named(value = "mapStatus")
    Status mapStatus(Integer id) {
        if (id == null) {
            return null;
        }
        return Status.values()[id];
    }

    @Named(value = "unmapStatus")
    Integer unmapStatus(String id) {
        if (id == null) {
            return null;
        }
        return Status.valueOf(id).ordinal();
    }
}
