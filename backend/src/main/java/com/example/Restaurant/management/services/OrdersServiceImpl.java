package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.OdersGetDto;
import com.example.Restaurant.management.entities.Orders;
import com.example.Restaurant.management.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<OdersGetDto> getAllOrders() {
        List<Orders> orders = ordersRepository.findAllOrdersWithDetails();

        return orders.stream().map(order -> {
            OdersGetDto dto=new OdersGetDto();
            dto.setOrdersId(order.getOrdersId());
            dto.setPaymentType(order.getPaymentType());
            dto.setOrderType(order.getOrderType());
            dto.setMobileNumber(order.getMobileNumber());
            dto.setAddress(order.getAddress());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());

            // Get menu names and user name
            String menuNames = order.getCart().stream()
                    .map(cart -> cart.getMenu().getName()) // Assuming `getName()` exists in Menu entity
                    .collect(Collectors.joining(", "));

            String userName = order.getCart().isEmpty() ? "" : order.getCart().get(0).getUser().getUsername(); // Assuming `getName()` exists in User entity

            dto.setMenuNames(menuNames);
            dto.setUserName(userName);

            return dto;
        }).collect(Collectors.toList());
    }
}
