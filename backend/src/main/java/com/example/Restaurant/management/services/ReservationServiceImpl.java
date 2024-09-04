package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.entities.Query;
import com.example.Restaurant.management.entities.Reservation;
import com.example.Restaurant.management.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(ReservationDto reservationDto) {

        Reservation reservation=new Reservation();
        BeanUtils.copyProperties(reservationDto,reservation);
        reservation.setCreatedAt(Instant.now());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservation() {
        List<Reservation> reservations= reservationRepository.findAll();
        return reservations.stream().map(reservation -> {
            ReservationDto dto=new ReservationDto();
            BeanUtils.copyProperties(reservation, dto);
            return dto;
        }).collect(Collectors.toList());

    }
}
