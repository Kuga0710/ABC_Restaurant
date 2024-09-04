package com.example.Restaurant.management.services;
import com.example.Restaurant.management.dtos.OdersGetDto;

import java.util.List;

public interface OrdersService {

    List<OdersGetDto> getAllOrders();
}
