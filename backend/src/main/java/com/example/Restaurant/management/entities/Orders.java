package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordersId;

    private boolean confirmed;
    private double totalPrice;

    private String Status;
    private String paymentType;
    private String orderType;
    private Integer mobileNumber;
    private String address;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Cart> cart;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

}
