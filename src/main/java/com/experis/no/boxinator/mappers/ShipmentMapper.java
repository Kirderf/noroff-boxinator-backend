package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.Countries;
import com.experis.no.boxinator.models.Orders;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.models.Status;
import com.experis.no.boxinator.models.dto.shipment.ShipmentDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentPostDTO;
import com.experis.no.boxinator.services.countries.CountriesService;
import com.experis.no.boxinator.services.orders.OrdersService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ShipmentMapper {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CountriesService countriesService;

    @Mapping(target = "order",qualifiedByName = "mapOrderID")
    @Mapping(target = "countries", qualifiedByName = "mapCountriesID")
    @Mapping(target = "status", qualifiedByName = "mapStatus")
    public abstract ShipmentDTO shipmentToShipmentDTO(Shipment shipment);
    @Mapping(target = "order",qualifiedByName = "unmapOrderID")
    @Mapping(target = "countries",qualifiedByName = "unmapCountriesID")
    @Mapping(target = "status",qualifiedByName = "unmapStatus")
    public abstract Shipment shipmentDTOToShipment(ShipmentDTO shipmentDTO);

    @Mapping(target = "order",qualifiedByName = "unmapOrderID")
    @Mapping(target = "countries",qualifiedByName = "unmapCountriesID")
    public abstract Shipment shipmentPostDTOToShipment(ShipmentPostDTO shipmentPostDTO);
    public abstract Collection<ShipmentDTO> shipmentToShipmentDTO(Collection<Shipment> shipmentCollection);


    @Named(value = "mapOrderID")
    Integer mapOrderID(Orders orders){
        if (orders == null){
            return null;
        }
        return orders.getId();
    }

    @Named(value = "unmapOrderID")
    Orders unmapOrderID(Integer orders){
        if (orders == null || orders == 0){
            return null;
        }
        return ordersService.findById(orders);
    }

    @Named(value = "mapCountriesID")
    String mapCountriesID(Countries countries){
        if (countries == null) {
            return null;
        }
        return countries.getShortName();
    }
    @Named(value = "unmapCountriesID")
    Countries unmapCountriesID(String countries){
        if (countries == null) {
            return null;
        }
        return countriesService.findById(countries);
    }

    @Named(value = "mapStatus")
    Status mapStatus(Integer id){
        if (id == null) {
            return null;
        }
        return Status.values()[id];
    }
    @Named(value = "unmapStatus")
    Integer unmapStatus(String id){
        if (id == null) {
            return null;
        }
        return Status.valueOf(id).ordinal();
    }
}