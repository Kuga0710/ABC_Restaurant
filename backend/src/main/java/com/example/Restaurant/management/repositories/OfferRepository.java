package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("SELECT o FROM Offer o WHERE :name IS NULL OR o.name = :name")
    List<Offer> findByNameOrAll(@Param("name") String name);

}
