package com.rcore.database.mongo.token.port;


import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.token.port.model.RefreshTokenDoc;
import com.rcore.database.mongo.token.port.query.ExpireRefreshTokenQuery;
import com.rcore.database.mongo.token.port.query.FindAllActiveByUserId;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.port.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository<RefreshTokenDoc> {

    private final MongoTemplate mongoTemplate;

    @Override
    public void expireRefreshToken(RefreshTokenDoc refreshTokenEntity) {
        AbstractModifyQuery modifyQuery = ExpireRefreshTokenQuery.of(refreshTokenEntity.getId());
        mongoTemplate.findAndModify(modifyQuery.getQuery(), modifyQuery.getUpdate(), modifyQuery.getModifyOptions(), RefreshTokenDoc.class);
    }

    @Override
    public List<RefreshTokenDoc> findAllActiveByUserId(String userId) {
        return mongoTemplate.find(FindAllActiveByUserId.of(userId).getQuery(), RefreshTokenDoc.class);
    }

    @Override
    public Optional<RefreshTokenDoc> save(RefreshTokenDoc object) {
        return Optional.ofNullable(mongoTemplate.save(object));
    }

    @Override
    public Boolean delete(RefreshTokenDoc object) {
        long deletedCount = mongoTemplate.remove(object).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), RefreshTokenDoc.class).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Optional<RefreshTokenDoc> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, RefreshTokenDoc.class));
    }

    @Override
    public SearchResult<RefreshTokenDoc> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().limit(size.intValue()).skip(skip), RefreshTokenDoc.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RefreshTokenDoc.class);
    }
}
