package com.example.Restaurant.management.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private Long reviewId;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Review cannot be null")
    private String review;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private int rating;
}
