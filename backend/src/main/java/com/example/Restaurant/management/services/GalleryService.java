package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.GalleryDto;
import com.example.Restaurant.management.entities.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {
    Gallery createGallery(MultipartFile file, String type);
    List<Gallery> getGalleryByType(String type);
    Gallery updateGallery(Long id, MultipartFile file, String type);
    List<GalleryDto> getAllGallery();
}
