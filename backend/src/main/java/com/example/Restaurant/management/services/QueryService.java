package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.entities.Query;

import java.util.List;

public interface QueryService {
    Query createQuery(QueryDto queryDto);
    List<QueryDto> getAllQuery();
}
