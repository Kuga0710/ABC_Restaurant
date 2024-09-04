package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.entities.Query;
import com.example.Restaurant.management.entities.Reservation;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.ReservationService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservation")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Reservation>> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation createdbooking=reservationService.createReservation(reservationDto);
        if (createdbooking != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<ReservationDto>>> getAllReviews() {
        List<ReservationDto> reservationDtos = reservationService.getAllReservation();
        if (reservationDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    reservationDtos
            ));
        }
    }
}
