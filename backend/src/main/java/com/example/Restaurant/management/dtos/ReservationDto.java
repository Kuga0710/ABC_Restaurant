package com.example.Restaurant.management.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {

    private Long reservationId;
    private String name;
    private String email;
    private LocalDate date;
    private int numberOfPeople;
    private String specialRequests;
}
