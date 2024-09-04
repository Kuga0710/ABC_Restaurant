package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUserId(Long id);
}
