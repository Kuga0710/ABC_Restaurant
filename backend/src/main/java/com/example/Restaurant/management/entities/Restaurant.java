//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//public class Restaurant {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long restaurantID;
//
//    private String name;
//    private String location;
//    private String contactNumber;
//    private String email;
//    private String openingHours;
//    private String description;
//
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Order> orders = new HashSet<>();
//
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Reservation> reservations = new HashSet<>();
//
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Query> queries = new HashSet<>();
//
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Gallery> galleries = new HashSet<>();
//
//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Feedback> feedbacks = new HashSet<>();
//}
