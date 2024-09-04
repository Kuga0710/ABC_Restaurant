package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.OfferDto;
import com.example.Restaurant.management.entities.Offer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OfferService {
    Offer createOffer(OfferDto offerDto, MultipartFile file);

    List<OfferDto> getOffers(String name);

    Offer updateOffer(Long id, OfferDto offerDto);

    boolean deleteOffer(Long id);
}
