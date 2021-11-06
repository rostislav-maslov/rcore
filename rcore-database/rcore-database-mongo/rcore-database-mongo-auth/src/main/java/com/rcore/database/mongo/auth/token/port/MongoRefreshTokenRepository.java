package com.rcore.database.mongo.auth.token.port;

import com.rcore.database.mongo.auth.token.mapper.RefreshTokenDocMapper;
import com.rcore.database.mongo.auth.token.model.RefreshTokenDoc;
import com.rcore.database.mongo.auth.token.query.ExpireRefreshTokenQuery;
import com.rcore.database.mongo.auth.token.query.FindAllActiveByUserIdQuery;
import com.rcore.database.mongo.auth.token.query.FindRefreshTokensQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.filter.RefreshTokenFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoRefreshTokenRepository implements RefreshTokenRepository {

    private final static String collectionName = CollectionNameUtils.getCollectionName(RefreshTokenDoc.class);
    private final MongoTemplate mongoTemplate;
    private final RefreshTokenDocMapper mapper;

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        ExpireRefreshTokenQuery expireRefreshTokenQuery = ExpireRefreshTokenQuery.of(refreshTokenEntity.getId());
        mongoTemplate.updateFirst(expireRefreshTokenQuery.getQuery(), expireRefreshTokenQuery.getUpdate(), RefreshTokenDoc.class);
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return mongoTemplate.find(FindAllActiveByUserIdQuery.of(userId).getQuery(), RefreshTokenDoc.class)
                .stream()
                .map(mapper::inverseMap)
                .collect(Collectors.toList());
    }

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity entity) {
        mongoTemplate.save(mapper.map(entity), collectionName);
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        return mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), collectionName).getDeletedCount() > 0;
    }

    @Override
    public Optional<RefreshTokenEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, RefreshTokenDoc.class))
                .map(mapper::inverseMap);
    }

    @Override
    public SearchResult<RefreshTokenEntity> find(RefreshTokenFilters filters) {
        Query query = new FindRefreshTokensQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RefreshTokenDoc.class).stream().map(mapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RefreshTokenDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RefreshTokenDoc.class);
    }
}