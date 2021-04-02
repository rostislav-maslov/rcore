package com.rcore.database.mongo.domain.token.port;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.token.model.RefreshTokenDoc;
import com.rcore.database.mongo.domain.token.query.ExpireRefreshTokenQuery;
import com.rcore.database.mongo.domain.token.query.FindAllActiveByUserId;
import com.rcore.database.mongo.domain.token.query.FindAllWithSearch;
import com.rcore.database.mongo.domain.token.query.FindRefreshTokensWithFilters;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final MongoTemplate mongoTemplate;

    public RefreshTokenRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        MongoCollection<Document> collection = this.mongoTemplate.getCollection(CollectionNameUtils.getCollectionName(RefreshTokenDoc.class));
        //Устанавливаем время жизни документов - 30 дней после просрочки
        collection.createIndex(Indexes.ascending("expireAt"),
                new IndexOptions().expireAfter(30L, TimeUnit.DAYS));
    }

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        AbstractModifyQuery modifyQuery = ExpireRefreshTokenQuery.of(refreshTokenEntity.getId());
        mongoTemplate.findAndModify(modifyQuery.getQuery(), modifyQuery.getUpdate(), modifyQuery.getModifyOptions(), RefreshTokenDoc.class);
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return mongoTemplate.find(FindAllActiveByUserId.of(userId).getQuery(), RefreshTokenDoc.class)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity object) {
        return mongoTemplate.save(object, CollectionNameUtils.getCollectionName(RefreshTokenDoc.class));
    }

    @Override
    public Boolean delete(RefreshTokenEntity object) {
        long deletedCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(RefreshTokenDoc.class)).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), RefreshTokenDoc.class).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, RefreshTokenDoc.class));
    }

    @Override
    public SearchResult<RefreshTokenEntity> find(SearchRequest request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RefreshTokenDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RefreshTokenDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RefreshTokenDoc.class);
    }

    @Override
    public SearchResult<RefreshTokenEntity> findWithFilters(RefreshTokenFilters filters) {
        Query query = new FindRefreshTokensWithFilters(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, RefreshTokenDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), RefreshTokenDoc.class)
        );
    }
}
