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

import java.nio.charset.StandardCharsets;
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

    @Override
    public byte[] generateCsv(List<QueryDto> queryDtos) {
        StringBuilder csvBuilder = new StringBuilder();
        // Adding CSV Header
        csvBuilder.append("Id,UserName,Email,subject,message\n"); // Update based on QueryDto properties

        // Adding CSV Data
        for (QueryDto query : queryDtos) {
            csvBuilder.append(query.getQueryId()).append(",")  // Update based on QueryDto properties
                    .append(query.getName()).append(",")
                    .append(query.getEmail()).append(",")
                    .append(query.getSubject()).append(",")
                    .append(query.getMessage()).append("\n"); // Update based on QueryDto properties
        }

        return csvBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

}
