package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private String name;
    private String description;
    private double price;
    private String availabilityStatus;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

//    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Cart> cartItems;

}
