package com.rcore.database.mongo.auth.role.port;

import com.rcore.database.mongo.auth.role.mapper.RoleDocMapper;
import com.rcore.database.mongo.auth.role.model.RoleDoc;
import com.rcore.database.mongo.auth.role.query.FindByNameQuery;
import com.rcore.database.mongo.auth.role.query.FindWithFiltersQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.role.port.filters.RoleFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoRoleRepository implements RoleRepository {

    private final static String collectionName = CollectionNameUtils.getCollectionName(RoleDoc.class);
    private final MongoTemplate mongoTemplate;
    private final RoleDocMapper mapper;

    @Override
    public Optional<RoleEntity> findByName(String name) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByNameQuery.of(name).getQuery(), RoleDoc.class))
                .map(mapper::inverseMap);
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        mongoTemplate.save(mapper.map(entity), collectionName);
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        return mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), collectionName).getDeletedCount() > 0;
    }

    @Override
    public Optional<RoleEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, RoleDoc.class))
                .map(mapper::inverseMap);
    }

    @Override
    public SearchResult<RoleEntity> find(RoleFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RoleDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RoleDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RoleDoc.class);
    }
}