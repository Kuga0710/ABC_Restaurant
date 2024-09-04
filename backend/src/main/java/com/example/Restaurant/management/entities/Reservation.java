package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Reservation extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private String name;
    private String email;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;
    private int numberOfPeople;
    private String specialRequests;

//    @ManyToOne
//    @JoinColumn(name = "userID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "restaurantID")
//    private Restaurant restaurant;
//
//    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
//    private Set<Service> services = new HashSet<>();
}
