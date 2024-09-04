package com.example.Restaurant.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;


    private String name;
    private String review;
    private int rating;
}
