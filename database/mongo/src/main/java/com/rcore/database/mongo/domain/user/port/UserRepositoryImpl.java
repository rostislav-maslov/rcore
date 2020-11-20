package com.rcore.database.mongo.domain.user.port;

import com.rcore.database.mongo.base.CountAggregationOutputDTO;
import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.role.model.RoleDoc;
import com.rcore.database.mongo.domain.user.model.UserDoc;
import com.rcore.database.mongo.domain.user.query.*;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.filters.UserFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<UserEntity> findByEmail(String email) {

        return Optional.ofNullable(mongoTemplate.findOne(FindByEmailQuery.of(email).getQuery(), UserDoc.class));
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(Long phoneNumber) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPhoneNumberQuery.of(phoneNumber).getQuery(), UserDoc.class));
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByLoginQuery.of(login).getQuery(), UserDoc.class));
    }

    @Override
    public UserEntity save(UserEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(UserDoc.class));
    }

    @Override
    public Boolean delete(UserEntity object) {
        Long deleteCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(UserDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(UserDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        UserEntity userEntity = Optional.ofNullable(mongoTemplate.findById(id, UserDoc.class))
                .map(userDoc -> {
                    //Синхронизуем роли из бд
                    userDoc.setRoles(userDoc.getRoles()
                            .stream()
                            .map(roleEntity -> mongoTemplate.findById(roleEntity.getId(), RoleDoc.class))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet()));
                    return userDoc;
                })
                .orElse(null);
        return Optional.of(userEntity);
    }

    @Override
    public SearchResult<UserEntity> find(SearchRequest request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, UserDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), UserDoc.class)
        );
    }

    @Override
    public SearchResult<UserEntity> findWithFilters(UserFilters userFilters) {
        FindWithFiltersAggregation findWithFiltersAggregation = new FindWithFiltersAggregation(userFilters);

        return SearchResult.withItemsAndCount(
                mongoTemplate.aggregate(findWithFiltersAggregation.getAggregation(), findWithFiltersAggregation.getInputType(), findWithFiltersAggregation.getOutputType())
                        .getMappedResults()
                        .stream()
                        .collect(Collectors.toList()),
                Optional.ofNullable(mongoTemplate.aggregate(findWithFiltersAggregation.getCountAggregation(), findWithFiltersAggregation.getInputType(), CountAggregationOutputDTO.class)
                        .getUniqueMappedResult())
                        .map(CountAggregationOutputDTO::getCount)
                        .orElse(0l)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserDoc.class);
    }
}
