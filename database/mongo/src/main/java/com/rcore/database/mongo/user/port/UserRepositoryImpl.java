package com.rcore.database.mongo.user.port;

import com.rcore.database.mongo.user.port.query.*;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<UserEntity> findByEmail(String email) {

        return Optional.ofNullable(mongoTemplate.findOne(FindByEmailQuery.of(email).getQuery(), UserEntity.class));
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(Long phoneNumber) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPhoneNumberQuery.of(phoneNumber).getQuery(), UserEntity.class));
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByLoginQuery.of(login).getQuery(), UserEntity.class));
    }

    @Override
    public Optional<UserEntity> save(UserEntity object) {
        return Optional.ofNullable(mongoTemplate.save(object));
    }

    @Override
    public Boolean delete(UserEntity object) {
        Long deleteCount = mongoTemplate.remove(object).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), UserEntity.class).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, UserEntity.class));
    }

    @Override
    public SearchResult<UserEntity> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().skip(skip).limit(size.intValue()), UserEntity.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserEntity.class);
    }
}
