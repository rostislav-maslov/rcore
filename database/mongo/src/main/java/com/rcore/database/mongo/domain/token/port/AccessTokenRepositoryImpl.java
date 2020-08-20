package com.rcore.database.mongo.domain.token.port;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.token.model.AccessTokenDoc;
import com.rcore.database.mongo.domain.token.query.DeactivateAllAccessTokenByRefreshTokenId;
import com.rcore.database.mongo.domain.token.query.ExpireAccessToken;
import com.rcore.database.mongo.domain.token.query.ExpireAllAccessTokenByRefreshTokenId;
import com.rcore.database.mongo.domain.token.query.FindAllWithSearch;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class AccessTokenRepositoryImpl implements AccessTokenRepository {
    
    private final MongoTemplate mongoTemplate;

    @Override
    public AccessTokenEntity save(AccessTokenEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(AccessTokenDoc.class));
    }

    @Override
    public Boolean delete(AccessTokenEntity object) {
        long deletedCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(AccessTokenDoc.class)).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), AccessTokenDoc.class).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Optional<AccessTokenEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, AccessTokenDoc.class));
    }

    @Override
    public SearchResult<AccessTokenEntity> find(SearchRequest request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, AccessTokenDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), AccessTokenDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), AccessTokenDoc.class);
    }

    @Override
    public void expireAllAccessTokenByRefreshTokenId(String refreshTokenId) {
        ExpireAllAccessTokenByRefreshTokenId query = ExpireAllAccessTokenByRefreshTokenId.of(refreshTokenId);
        mongoTemplate.findAndModify(query.getQuery(), query.getUpdate(), query.getModifyOptions(), AccessTokenDoc.class);
    }

    @Override
    public void deactivateAllAccessTokenByRefreshTokenId(String refreshTokenId) {
        DeactivateAllAccessTokenByRefreshTokenId query = DeactivateAllAccessTokenByRefreshTokenId.of(refreshTokenId);
        mongoTemplate.findAndModify(query.getQuery(), query.getUpdate(), query.getModifyOptions(), AccessTokenDoc.class);
    }

    @Override
    public void expireAccessToken(String accessTokenId) {
        ExpireAccessToken query = ExpireAccessToken.of(accessTokenId);
        mongoTemplate.findAndModify(query.getQuery(), query.getUpdate(), query.getModifyOptions(), AccessTokenDoc.class);
    }
}
