package com.rcore.database.mongo.domain.role.port;

import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.role.model.RoleDoc;
import com.rcore.database.mongo.domain.role.query.FindAllWithSearch;
import com.rcore.database.mongo.domain.role.query.FindByName;
import com.rcore.database.mongo.domain.role.query.FindWithFilters;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.port.filters.RoleFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<RoleEntity> findByName(String name) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByName.of(name).getQuery(), RoleDoc.class));
    }

    @Override
    public SearchResult<RoleEntity> findWithFilters(RoleFilters roleFilters) {
        Query query = new FindWithFilters(roleFilters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RoleDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RoleDoc.class));
    }

    @Override
    public RoleEntity save(RoleEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(RoleDoc.class));
    }

    @Override
    public Boolean delete(RoleEntity object) {
        long deletedCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(RoleDoc.class)).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(RoleDoc.class)).getDeletedCount();
        return deletedCount > 0;
    }

    @Override
    public Optional<RoleEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, RoleDoc.class));
    }

    @Override
    public SearchResult<RoleEntity> find(SearchFilters request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new FindAllWithSearch(request).getQuery(), RoleDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RoleDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RoleDoc.class);
    }
}
