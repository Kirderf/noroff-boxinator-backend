package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.OrderProduct;
import com.experis.no.boxinator.models.Orders;
import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.models.dto.order.OrderDTO;
import com.experis.no.boxinator.models.dto.order.OrderPostDTO;
import com.experis.no.boxinator.models.dto.orderProduct.OrderProductDTO;
import com.experis.no.boxinator.services.OrdersProduct.OrdersProductsServiceImpl;
import com.experis.no.boxinator.services.product.ProductService;
import com.experis.no.boxinator.services.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    protected ProductService productService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected OrdersProductsServiceImpl ordersProductsServiceImpl;


    @Mapping(target = "ordersProducts", qualifiedByName = "orderToOrderProductId")
    @Mapping(target= "user", source = "user.id")
    public abstract OrderDTO orderToOrderDTO(Orders order);
    public abstract Collection<OrderDTO> orderToOrderDTO(Collection<Orders> ordersCollection);
    @Mapping(target = "user", qualifiedByName = "userIdToUser")
    public abstract Orders ordersPostDTOToOrders(OrderPostDTO orderPostDTO);
    @Named(value = "userIdToUser")
    public User mapIdToUser(Integer id){
        if(id==null){
            return null;
        }
        return userService.findById(id);
    }

    @Named(value = "orderToOrderProductId")
    public List<OrderProductDTO> mapProductToId(Set<OrderProduct> value) {
        if(value == null)
            return null;
        List<OrderProduct> orderProductList =  value.stream().map(o -> ordersProductsServiceImpl.findById(o.getId())).toList();
        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        for (OrderProduct orderProduct : orderProductList) {
            orderProductDTOList.add(new OrderProductDTO(orderProduct.getProductId(), orderProduct.getQuantity()));
        }
        return orderProductDTOList;
    }
}
