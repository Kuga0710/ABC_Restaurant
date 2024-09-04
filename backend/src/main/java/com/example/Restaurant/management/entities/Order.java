//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "`order`")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long orderId;
//
////    private boolean confirmed;
//
////    private Double totalAmount;
////    private String paymentType;
////    private String deliveryType;
////    private String orderStatus;
//
////    @ManyToOne
////    @JoinColumn(name = "user_id", nullable = false)
////    private User user;
//
////    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
////    private Set<OrderItem> orderItems;
//
////    @ManyToOne
////    @JoinColumn(name = "restaurantID", nullable = false)
////    private Restaurant restaurant;
//
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<Cart> cart;
//}
