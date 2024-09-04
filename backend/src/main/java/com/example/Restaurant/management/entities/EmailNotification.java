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
//public class EmailNotification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long emailNotificationID;
//
//    private String recipientEmail;
//    private String subject;
//
//    @ManyToOne
//    @JoinColumn(name = "offerID")
//    private Offer offer;
//
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//}
