package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {

    private Long menuId;
    private String name;
    private String description;
    private double price;
    private String availabilityStatus;
    private byte[] image; // Store image data as byte array

}
