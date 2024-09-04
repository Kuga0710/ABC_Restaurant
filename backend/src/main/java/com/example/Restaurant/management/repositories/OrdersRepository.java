package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query("SELECT o FROM Orders o LEFT JOIN o.cart c LEFT JOIN c.user u")
    List<Orders> findAllOrdersWithDetails();
}
