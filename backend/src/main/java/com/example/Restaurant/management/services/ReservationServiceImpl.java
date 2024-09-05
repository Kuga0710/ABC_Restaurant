package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Query;
import com.example.Restaurant.management.entities.Reservation;
import com.example.Restaurant.management.repositories.ReservationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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

    @Override
    public byte[] generateCsv(List<ReservationDto> reservationDtos) {
        StringBuilder csvBuilder = new StringBuilder();
        // Adding CSV Header
        csvBuilder.append("Id,Name,Email,Date,NoOfChairs,SpecialRequest\n"); // Update based on QueryDto properties

        // Adding CSV Data
        for (ReservationDto reservation : reservationDtos) {
            csvBuilder.append(reservation.getReservationId()).append(",")  // Update based on QueryDto properties
                    .append(reservation.getName()).append(",")
                    .append(reservation.getEmail()).append(",")
                    .append(reservation.getDate()).append(",")
                    .append(reservation.getNumberOfPeople()).append(",")
                    .append(reservation.getSpecialRequests()).append("\n"); // Update based on QueryDto properties
        }

        return csvBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
