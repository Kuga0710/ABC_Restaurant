package com.example.Restaurant.management.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'customer'")
    private String role = "customer";


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Orders> orders;

////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Reservation> reservations = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Query> queries = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<EmailNotification> emailNotifications = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<Feedback> feedbacks = new HashSet<>();
////
////    @OneToMany(mappedBy = "generatedBy", cascade = CascadeType.ALL)
////    private Set<Report> reports = new HashSet<>();
////
////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
////    private Set<UserGalleryView> userGalleryViews = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Offer> offers = new HashSet<>();
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Payment payment;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartItems;

}
