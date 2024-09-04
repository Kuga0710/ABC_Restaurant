package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Query extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryId;

    private String name;
    private String email;
    private String subject;
    private String message;

//    @ManyToOne
//    @JoinColumn(name = "userID", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurantID", nullable = false)
//    private Restaurant restaurant;
}
