package com.rcore.database.mongo.commons.port.impl;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.commons.document.BaseDocument;
import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.database.mongo.commons.query.FindByIdQuery;
import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public abstract class AbstractMongoRepository<ID, E extends BaseEntity<ID>, D extends BaseDocument, F extends SearchFilters> implements CRUDRepository<ID, E, F> {
    protected final Class<D> documentClass;
    protected final ExampleDataMapper<E, D> mapper;
    protected final MongoTemplate mongoTemplate;

    public AbstractMongoRepository(Class<D> documentClass, ExampleDataMapper<E, D> mapper, MongoTemplate mongoTemplate) {
        this.documentClass = documentClass;
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public E save(E entity) {
        return mapper.inverseMap(mongoTemplate.save(mapper.map(entity)));
    }

    @Override
    public Boolean delete(ID id) {
        return mongoTemplate.remove(FindByIdQuery.of(id).getQuery(), documentClass).getDeletedCount() > 0;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(mongoTemplate.findById(id, documentClass))
                .map(mapper::inverseMap);
    }

    protected abstract AbstractExampleQuery getSearchQuery(F filters);

    @Override
    public SearchResult<E> find(F filters) {
        Query query = getSearchQuery(filters).getQuery();

        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, documentClass)
                        .stream()
                        .map(mapper::inverseMap)
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.skip(0).limit(0), documentClass)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), documentClass);
    }

    @Override
    public boolean exist(ID id) {
        return mongoTemplate.exists(FindByIdQuery.of(id).getQuery(), documentClass);
    }
}
