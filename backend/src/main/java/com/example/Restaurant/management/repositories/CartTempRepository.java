package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.CartTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartTempRepository extends JpaRepository<CartTemp,Long> {
    List<CartTemp> findByUserId(Long userId);

    List<CartTemp> findByOrders_OrdersId(Long ordersId);
}
