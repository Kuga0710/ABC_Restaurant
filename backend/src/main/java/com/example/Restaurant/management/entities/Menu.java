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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String name;
    private String description;
    private double price;
    private String availabilityStatus;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") // Change to LONGBLOB if needed
    private byte[] image; // Store image data as byte array


    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartItems;

}
