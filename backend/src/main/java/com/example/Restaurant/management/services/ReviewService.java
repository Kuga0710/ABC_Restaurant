package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
}
