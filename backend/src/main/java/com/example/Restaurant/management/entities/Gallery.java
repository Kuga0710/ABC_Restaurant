package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    private String type; // e.g., Restaurant, Food

//    @ManyToOne
//    @JoinColumn(name = "restaurantID")
//    private Restaurant restaurant;
//
//    @ManyToOne
//    @JoinColumn(name = "userGalleryViewID")
//    private UserGalleryView userGalleryView;
}
