package com.rcore.database.mongo.auth.authorization.port;

import com.rcore.database.mongo.auth.authorization.model.AuthorizationDoc;
import com.rcore.database.mongo.auth.authorization.query.FindWithFiltersQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
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
public class MongoAuthorizationRepository implements AuthorizationRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public AuthorizationEntity save(AuthorizationEntity entity) {
        mongoTemplate.save(entity, CollectionNameUtils.getCollectionName(AuthorizationDoc.class));
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), CollectionNameUtils.getCollectionName(AuthorizationDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<AuthorizationEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, AuthorizationDoc.class));
    }

    @Override
    public SearchResult<AuthorizationEntity> find(SearchFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, AuthorizationDoc.class).stream().collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), AuthorizationDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), AuthorizationDoc.class);
    }
}
