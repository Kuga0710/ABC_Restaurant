package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QueryDto {

    private Long queryId;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDate createdAt;
}
