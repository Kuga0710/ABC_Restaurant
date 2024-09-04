package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdersGetDto {

    private Long ordersId;
    private String paymentType;
    private String orderType;
    private Integer mobileNumber;
    private String address;
    private double totalPrice;
    private String Status;
    private String menuNames;
    private String userName;
}
