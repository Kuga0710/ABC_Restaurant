package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE (:name IS NULL OR m.name = :name)")
    List<Menu> findByNameOrAll(String name);
}
