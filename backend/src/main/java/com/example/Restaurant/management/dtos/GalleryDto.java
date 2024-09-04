package com.example.Restaurant.management.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryDto {

    private Long imageID;
    private byte[] image;
    private String type;

}
