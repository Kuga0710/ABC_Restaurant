package com.example.Restaurant.management.services;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.entities.Query;
import com.example.Restaurant.management.entities.Review;
import com.example.Restaurant.management.repositories.QueryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    private QueryRepository queryRepository;

    @Override
    public Query createQuery(QueryDto queryDto) {

        Query query=new Query();
        BeanUtils.copyProperties(queryDto,query);
        query.setCreatedAt(Instant.now());
        return queryRepository.save(query);
    }

    @Override
    public List<QueryDto> getAllQuery() {
        List<Query> queries = queryRepository.findAll();
        return queries.stream().map(query -> {
            QueryDto dto = new QueryDto();
            BeanUtils.copyProperties(query, dto);
            return dto;
        }).collect(Collectors.toList());    }
}
