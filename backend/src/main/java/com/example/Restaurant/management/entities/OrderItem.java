//package com.example.Restaurant.management.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class OrderItem{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long orderItemId;
//
//    private Integer quantity;
//    private Double price;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "menu_id", nullable = false)
//    private Menu menu;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    private Cart cart;
//
//
//}
