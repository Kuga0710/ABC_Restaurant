package com.example.Restaurant.management.repositories;

import com.example.Restaurant.management.entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query,Long> {
}
