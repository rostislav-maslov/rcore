package com.rcore.database.mongo.role.port;

import com.rcore.database.mongo.role.model.RoleDoc;
import com.rcore.database.mongo.role.query.FindByNameQuery;
import com.rcore.database.mongo.role.query.FindWithFiltersQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.port.filters.RoleFilters;
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

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<RoleEntity> findByName(String name) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByNameQuery.of(name).getQuery(), RoleDoc.class));
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        mongoTemplate.save(entity, CollectionNameUtils.getCollectionName(RoleDoc.class));
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), CollectionNameUtils.getCollectionName(RoleDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<RoleEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, RoleDoc.class));
    }

    @Override
    public SearchResult<RoleEntity> find(RoleFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RoleDoc.class).stream().collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RoleDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RoleDoc.class);
    }
}