package com.example.Restaurant.management.controllers;


import com.example.Restaurant.management.dtos.OdersGetDto;
import com.example.Restaurant.management.dtos.OrdersDto;
import com.example.Restaurant.management.dtos.ReservationDto;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.OrdersService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<OdersGetDto>>> getAllOrders() {
        List<OdersGetDto> ordersGetDtos = ordersService.getAllOrders();
        if (ordersGetDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    ordersGetDtos
            ));
        }
    }

    @GetMapping("/csv")
    public ResponseEntity<byte[]> downloadOrdersCsv() {
        List<OdersGetDto> ordersGetDtos =ordersService.getAllOrders();

        if (ordersGetDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            byte[] csvData = ordersService.generateCsv(ordersGetDtos);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.csv");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return ResponseEntity.ok().headers(headers).body(csvData);
        }
    }
}
