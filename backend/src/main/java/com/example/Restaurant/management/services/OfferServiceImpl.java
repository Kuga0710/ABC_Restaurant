package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.OfferDto;
import com.example.Restaurant.management.entities.Offer;
import com.example.Restaurant.management.repositories.OfferRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Offer createOffer(OfferDto offerDto, MultipartFile file) {
        Offer offer = new Offer();
        BeanUtils.copyProperties(offerDto, offer);

        if (file != null && !file.isEmpty()) {
            try {
                byte[] imageBytes = file.getBytes();
                System.out.println("Image size: " + imageBytes.length);

                offer.setImage(imageBytes);

                if (offer.getImage() == null || offer.getImage().length == 0) {
                    throw new RuntimeException("Failed to set image bytes");
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing file", e);
            }
        } else {
            System.out.println("File is null or empty");
        }

        return offerRepository.save(offer);
    }



    @Override
    public List<OfferDto> getOffers(String name) {
        List<Offer> offers = offerRepository.findByNameOrAll(name);
        return offers.stream().map(offer -> {
            OfferDto offerDto = new OfferDto();
            BeanUtils.copyProperties(offer, offerDto);
            return offerDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Offer updateOffer(Long id, OfferDto offerDto) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Offer ID"));

        BeanUtils.copyProperties(offerDto, offer);
        return offerRepository.save(offer);
    }

    @Override
    public boolean deleteOffer(Long id) {
        if (offerRepository.existsById(id)) {
            offerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
