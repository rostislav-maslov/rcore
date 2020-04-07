package com.rcore.database.mongo.user.port;

import com.rcore.database.mongo.user.port.model.UserDoc;
import com.rcore.database.mongo.user.port.query.*;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository<UserDoc> {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<UserDoc> findByEmail(String email) {

        return Optional.ofNullable(mongoTemplate.findOne(FindByEmailQuery.of(email).getQuery(), UserDoc.class));
    }

    @Override
    public Optional<UserDoc> findByPhoneNumber(Long phoneNumber) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPhoneNumberQuery.of(phoneNumber).getQuery(), UserDoc.class));
    }

    @Override
    public Optional<UserDoc> findByLogin(String login) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByLoginQuery.of(login).getQuery(), UserDoc.class));
    }

    @Override
    public Optional<UserDoc> save(UserDoc object) {
        return Optional.ofNullable(mongoTemplate.save(object));
    }

    @Override
    public Boolean delete(UserDoc object) {
        Long deleteCount = mongoTemplate.remove(object).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), UserDoc.class).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<UserDoc> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, UserDoc.class));
    }

    @Override
    public SearchResult<UserDoc> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().skip(skip).limit(size.intValue()), UserDoc.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserDoc.class);
    }
}
