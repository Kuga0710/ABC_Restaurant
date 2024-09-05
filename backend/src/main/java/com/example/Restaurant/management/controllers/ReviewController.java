package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Review;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.ReviewService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Review>> createReview(@Valid @RequestBody ReviewDto reviewDto) {
        Review createdReview = reviewService.createReview(reviewDto);
        if (createdReview != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
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
    public ResponseEntity<ResponseWrapper<List<ReviewDto>>> getAllReviews() {
        List<ReviewDto> reviewDtos = reviewService.getAllReviews();
        if (reviewDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    reviewDtos
            ));
        }
    }

    @GetMapping("/csv")
    public ResponseEntity<byte[]> downloadQueryCsv() {
        List<ReviewDto> reviewDtos =reviewService.getAllReviews();

        if (reviewDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            byte[] csvData = reviewService.generateCsv(reviewDtos);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reviews.csv");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return ResponseEntity.ok().headers(headers).body(csvData);
        }
    }
}

