package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDto {

    private Long offerId;
    private String name;
    private String description;
    private double price;
    private String availabilityStatus;
    private byte[] image;
}
