package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(ReservationDto reservationDto);
    List<ReservationDto> getAllReservation();
}
