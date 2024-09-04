package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.GalleryDto;
import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Gallery;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.GalleryService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/gallery")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Gallery>> createGallery(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        Gallery createdGallery = galleryService.createGallery(file, type);

        if (createdGallery != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
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

    // GET: Retrieve all images by type
    @GetMapping("/type")
    public ResponseEntity<ResponseWrapper<List<Gallery>>> getGalleryByType(@RequestParam(required = false) String type) {
        List<Gallery> galleries = galleryService.getGalleryByType(type);

        if (galleries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(
                RestApiResponseStatusCodes.SUCCESS.getCode(),
                ValidationMessages.RETRIEVED,
                galleries
        ));
    }


    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapper<Gallery>> updateGallery(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        Gallery updatedGallery = galleryService.updateGallery(id, file, type);

        if (updatedGallery != null) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    updatedGallery
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
    public ResponseEntity<ResponseWrapper<List<GalleryDto>>> getAllGallery() {
        List<GalleryDto> galleryDtos = galleryService.getAllGallery();
        if (galleryDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    galleryDtos
            ));
        }
    }
}

