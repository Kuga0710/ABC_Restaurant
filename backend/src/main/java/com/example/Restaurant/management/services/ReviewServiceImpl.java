package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Review;
import com.example.Restaurant.management.repositories.ReviewRepository;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(review -> {
            ReviewDto dto = new ReviewDto();
            BeanUtils.copyProperties(review, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
