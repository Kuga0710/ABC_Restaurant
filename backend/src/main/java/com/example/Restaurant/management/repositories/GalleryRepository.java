package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery,Long> {
    List<Gallery> findByType(String type);
}
