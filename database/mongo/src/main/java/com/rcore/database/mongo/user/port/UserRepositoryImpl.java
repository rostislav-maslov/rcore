package com.rcore.database.mongo.user.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements CRUDRepository<ObjectId, UserEntity> {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<UserEntity> save(UserEntity object) {
        return Optional.ofNullable(mongoTemplate.save(object));
    }

    @Override
    public Boolean delete(UserEntity object) {
        long deletedCount = mongoTemplate.remove(object).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(ObjectId id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id))).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Optional<UserEntity> findById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findById(id, UserEntity.class));
    }

    @Override
    public SearchResult<UserEntity> find(Long size, Long skip) {
        Query query = new Query(new Criteria())
                .skip(skip)
                .limit(size.intValue());
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, UserEntity.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserEntity.class);
    }
}
