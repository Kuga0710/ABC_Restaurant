//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//
//@Entity
//@Getter
//@Setter
//public class Feedback {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long feedbackID;
//
//    private String feedbackText;
//    private int rating;
//
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurantID")
//    private Restaurant restaurant;
//}
