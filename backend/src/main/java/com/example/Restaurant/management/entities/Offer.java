//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//public class Offer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long offerID;
//
//    private String title;
//    private String description;
//    private double discountPercentage;
//
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
//    private Set<EmailNotification> emailNotifications = new HashSet<>();
//}
