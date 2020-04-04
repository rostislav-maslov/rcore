package com.ub.core.base.services.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Абстрактный класс для облегчения поиска
 */
@Component
public class ClientSearchService {

    @Autowired protected MongoTemplate mongoTemplate;

    public Long count(Criteria criteria, Class entityClass) {
        Query query = new Query(criteria);
        return mongoTemplate.count(query, entityClass);
    }

    public <T> List<T> find(Criteria criteria, Sort sort, Integer currenPage, Integer limit, Class<T> entityClass) {
        Pageable pageable = null;
        if (sort != null && currenPage != null && limit != null) {
            pageable = new PageRequest(currenPage, limit, sort);
        }
        if(sort == null && currenPage != null && limit != null ){
            pageable = new PageRequest(currenPage, limit);
        }


        Query query = new Query(criteria);
        query = query.with(pageable);

        List<T> result = mongoTemplate.find(query, entityClass);
        return result;
    }

    public <T> List<T> find(Criteria criteria, Integer currenPage, Integer limit, Class<T> entityClass) {
        return find(criteria, null, currenPage, limit, entityClass);
    }

}
