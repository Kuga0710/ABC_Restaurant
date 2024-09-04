package com.example.Restaurant.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartTempId;

    private Integer quantity = 1;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;
}
