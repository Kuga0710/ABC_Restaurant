package com.example.Restaurant.management.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {

    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "MenuId is required")
    private Long menuId;

    @NotNull(message = "OrderId is required")
    private Long orderId;

}
