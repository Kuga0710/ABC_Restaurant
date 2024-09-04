package com.example.Restaurant.management.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDto {

    private String paymentType;
    private String orderType;
    private Integer mobileNumber;
    private String address;
}
