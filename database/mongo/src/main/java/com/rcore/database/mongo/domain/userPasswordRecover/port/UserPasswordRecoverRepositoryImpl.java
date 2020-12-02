package com.rcore.database.mongo.domain.userPasswordRecover.port;

import com.rcore.database.mongo.common.utils.CollectionNameUtils;
import com.rcore.database.mongo.domain.user.query.FindAllWithSearch;
import com.rcore.database.mongo.domain.userPasswordRecover.model.UserPasswordRecoverDoc;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserPasswordRecoverRepositoryImpl implements UserPasswordRecoverRepository {

    private final MongoTemplate mongoTemplate;

    /**
     * Ищем активный UserPassword:
     * - по емейлу
     * - retryLeft > 0
     * - isRecovered == false
     * - expiredAt > new Date
     *
     * @param userId
     * @return
     */
    @Override
    public Optional<UserPasswordRecoverEntity> findActiveByEmail(String userId) {
        return  Optional.ofNullable(
                mongoTemplate.findOne(new Query(
                Criteria.where("isRecovered").is(false)
                        .and("expiredAt").gt(new Date())
                        .and("retryLeft").gt(0)
                        .and("userId").is(userId)

        ), UserPasswordRecoverDoc.class));
    }

    @Override
    public UserPasswordRecoverEntity save(UserPasswordRecoverEntity object) {
        return mongoTemplate.save(UserPasswordRecoverDoc.toDoc(object), CollectionNameUtils.getCollectionName(UserPasswordRecoverDoc.class));
    }

    @Override
    public Boolean delete(UserPasswordRecoverEntity object) {
        Long deleteCount = mongoTemplate.remove(object, CollectionNameUtils.getCollectionName(UserPasswordRecoverDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), CollectionNameUtils.getCollectionName(UserPasswordRecoverDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<UserPasswordRecoverEntity> findById(String id) {
        UserPasswordRecoverEntity userEntity = Optional.ofNullable(mongoTemplate.findById(id, UserPasswordRecoverDoc.class))
                .orElse(null);
        return Optional.of(userEntity);
    }

    @Override
    public SearchResult<UserPasswordRecoverEntity> find(SearchFilters request) {
        Query query = new FindAllWithSearch(request).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, UserPasswordRecoverDoc.class)
                        .stream()
                        .collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), UserPasswordRecoverDoc.class)
        );
    }


    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), UserPasswordRecoverDoc.class);
    }
}
