package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.Countries;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.models.ShipmentProduct;
import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.models.dto.orderProduct.ShipmentProductDTO;
import com.experis.no.boxinator.models.dto.orderProduct.ShipmentProductWithFullProductDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentPostDTO;
import com.experis.no.boxinator.models.dto.shipment.ShipmentWithFullProductDTO;
import com.experis.no.boxinator.services.OrdersProduct.ShipmentProductsServiceImpl;
import com.experis.no.boxinator.services.countries.CountriesService;
import com.experis.no.boxinator.services.product.ProductService;
import com.experis.no.boxinator.services.shipment.ShipmentService;
import com.experis.no.boxinator.services.user.UserService;
import org.hibernate.MappingException;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Mapper(componentModel = "spring")
public abstract class ShipmentMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private ShipmentProductsServiceImpl shipmentProductsServiceImpl;
    @Autowired
    private CountriesService countriesService;


    @Mapping(target = "countries", qualifiedByName = "mapCountriesID")
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "shipmentProducts", qualifiedByName = "shipmentToShipmentProductId")
    @Named("shipmentToShipmentDTO")
    public abstract ShipmentDTO shipmentToShipmentDTO(Shipment shipment);

    @Mapping(target = "countries", qualifiedByName = "mapCountriesID")
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "shipmentProducts", qualifiedByName = "shipmentToShipmentProductFull")
    @Named("shipmentToShipmentWithFullProductDTO")
    public abstract ShipmentWithFullProductDTO shipmentToShipmentWithFullProductDTO(Shipment shipment);


    @Mapping(target = "countries", qualifiedByName = "unmapCountriesID")
    @Mapping(target = "user", qualifiedByName = "userIdToUser")
    @Mapping(target = "shipmentProducts", qualifiedByName = "unshipmentToShipmentProductId")
    public abstract Shipment shipmentDTOToShipment(ShipmentDTO shipmentDTO);


    @Mapping(target = "countries", qualifiedByName = "unmapCountriesID")
    @Mapping(target = "user", qualifiedByName = "userIdToUser")
    public abstract Shipment shipmentPostDTOToShipment(ShipmentPostDTO shipmentPostDTO);

    @IterableMapping(qualifiedByName = {"shipmentToShipmentDTO"})
    public abstract Collection<ShipmentDTO> shipmentToShipmentDTO(Collection<Shipment> shipmentCollection);

    @IterableMapping(qualifiedByName = {"shipmentToShipmentWithFullProductDTO"})
    public abstract Collection<ShipmentWithFullProductDTO> shipmentToShipmentWithFullProductDTO(Collection<Shipment> shipmentCollection);

    @Named(value = "mapCountriesID")
    String mapCountriesID(Countries countries) {
        if (countries == null) {
            return null;
        }
        return countries.getShortName();
    }

    @Named(value = "unmapCountriesID")
    Countries unmapCountriesID(String countries) {
        if (countries == null) {
            return null;
        }
        return countriesService.findById(countries);
    }

    @Named(value = "userIdToUser")
    public User mapIdsToUser(String id) {
        if (id == null) {
            return null;
        }
        return userService.findById(id);
    }

    @Named(value = "shipmentToShipmentProductId")
    public List<ShipmentProductDTO> mapProductToId(Set<ShipmentProduct> value) {
        if (value == null)
            return null;
        List<ShipmentProduct> orderProductList = value.stream().map(o -> shipmentProductsServiceImpl.findById(o.getId())).toList();
        List<ShipmentProductDTO> orderProductDTOList = new ArrayList<>();
        for (ShipmentProduct orderProduct : orderProductList) {
            orderProductDTOList.add(new ShipmentProductDTO(orderProduct.getProduct().getId(), orderProduct.getQuantity(), orderProduct.getShipment().getId()));
        }
        return orderProductDTOList;
    }

    @Named(value = "shipmentToShipmentProductFull")
    public List<ShipmentProductWithFullProductDTO> mapShipmentProductToShipmentProductWithFullProductDTO(Set<ShipmentProduct> value) {
        if (value == null)
            return null;
        List<ShipmentProduct> orderProductList = value.stream().map(o -> shipmentProductsServiceImpl.findById(o.getId())).toList();
        List<ShipmentProductWithFullProductDTO> orderProductDTOList = new ArrayList<>();
        for (ShipmentProduct orderProduct : orderProductList) {
            orderProductDTOList.add(new ShipmentProductWithFullProductDTO(productService.findById(orderProduct.getProductId()), orderProduct.getQuantity(), orderProduct.getShipment().getId()));
        }
        return orderProductDTOList;
    }

    @Named(value = "unshipmentToShipmentProductId")
    public Set<ShipmentProduct> unmapProductToId(List<ShipmentProductDTO> value) {
        try {
            if (value == null)
                return null;
            List<ShipmentProductDTO> orderProductList = value.stream().toList();
            Set<ShipmentProduct> orderProductDTOList = new HashSet<>();
            for (ShipmentProductDTO orderProduct : orderProductList) {
                ShipmentProduct shipmentProduct = new ShipmentProduct();
                shipmentProduct.setProduct(productService.findById(orderProduct.getProductId()));
                shipmentProduct.setShipment(shipmentService.findById(orderProduct.getShipmentId()));
                shipmentProduct.setQuantity(orderProduct.getQuantity());
                ShipmentProduct.ShipmentProductsId shipmentProduct1 = new ShipmentProduct.ShipmentProductsId();
                shipmentProduct1.setProductsId(orderProduct.getProductId());
                shipmentProduct1.setShipmentsId(orderProduct.getShipmentId());
                shipmentProduct.setId(shipmentProduct1);
                orderProductDTOList.add(shipmentProduct);
            }
            return orderProductDTOList;
        } catch (Exception e) {
            throw new MappingException("MAPPING ERROR");
        }

    }
}
