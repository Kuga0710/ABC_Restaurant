//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Payment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long paymentID;
//
//    private double amount;
//    private String paymentMethod;
//
//    @OneToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//    @OneToOne
//    @JoinColumn(name = "orderID")
//    private Order order;
//}
