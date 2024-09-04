package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartGetDto {
    private Long userId;
    private Long cartId;
    private String menuName;
    private double menuPrice;
    private int quantity;
}
