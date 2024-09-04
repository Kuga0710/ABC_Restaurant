package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.OfferDto;
import com.example.Restaurant.management.entities.Offer;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.OfferService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Offer>> createOffer(@RequestParam("file") MultipartFile file, @RequestParam("offer") String offerDtoString) throws JsonProcessingException {
        OfferDto offerDto = new ObjectMapper().readValue(offerDtoString, OfferDto.class);

        Offer createdOffer = offerService.createOffer(offerDto, file);

        if (createdOffer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    createdOffer
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
    public ResponseEntity<ResponseWrapper<List<OfferDto>>> getOffers(@RequestParam(required = false) String name) {
        List<OfferDto> offers = offerService.getOffers(name);
        if (offers.isEmpty() && name != null && !name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(
                RestApiResponseStatusCodes.SUCCESS.getCode(),
                ValidationMessages.RETRIEVED,
                offers
        ));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapper<Offer>> updateOffer(@PathVariable("id") Long id, @RequestBody OfferDto offerDto) {
        Offer updatedOffer = offerService.updateOffer(id, offerDto);
        if (updatedOffer != null) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    updatedOffer
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteOffer(@PathVariable Long id) {
        boolean deleted = offerService.deleteOffer(id);
        if (deleted) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.DELETE_SUCCESS,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.DELETE_FAILED,
                    null
            ));
        }
    }
}
