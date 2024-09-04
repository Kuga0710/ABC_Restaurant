package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.dtos.CartGetDto;
import com.example.Restaurant.management.dtos.OrdersDto;
import com.example.Restaurant.management.entities.Cart;

import java.util.List;

public interface CartService {
    Cart createCart(CartDto cartDto);
    boolean deleteCartById(Long id);
    CartGetDto increaseCartQuantity(Long cartId);
    CartGetDto decreaseCartQuantity(Long cartId);
  //  void confirmOrder();
    List<CartGetDto> getAllCartTempByUserId(Long userId);

    void confirmOrder(OrdersDto ordersDto);
}
