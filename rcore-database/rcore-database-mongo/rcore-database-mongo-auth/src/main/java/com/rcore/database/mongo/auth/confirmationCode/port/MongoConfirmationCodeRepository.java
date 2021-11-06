package com.rcore.database.mongo.auth.confirmationCode.port;

import com.rcore.database.mongo.auth.confirmationCode.model.ConfirmationCodeDoc;
import com.rcore.database.mongo.auth.confirmationCode.query.FindNotConfirmedCodeByAuthorizationQuery;
import com.rcore.database.mongo.auth.confirmationCode.query.FindNotConfirmedByAddressAndSendingTypeAndCodeQuery;
import com.rcore.database.mongo.auth.confirmationCode.query.FindNotSentQuery;
import com.rcore.database.mongo.auth.confirmationCode.query.FindWithFiltersQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
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
public class MongoConfirmationCodeRepository implements ConfirmationCodeRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Boolean existNotConfirmedCode(String authorizationId) {
        return mongoTemplate.exists(FindNotConfirmedCodeByAuthorizationQuery.of(authorizationId).getQuery(), ConfirmationCodeDoc.class);
    }

    @Override
    public Optional<ConfirmationCodeEntity> findWaitingConfirmCode(String authorizationId) {
        return Optional.ofNullable(mongoTemplate.findOne(FindNotConfirmedCodeByAuthorizationQuery.of(authorizationId).getQuery(), ConfirmationCodeDoc.class));
    }

    @Override
    public Optional<ConfirmationCodeEntity> findNotConfirmedByAddressAndSendingTypeAndCode(String address, ConfirmationCodeEntity.Recipient.SendingType sendingType, String code) {
        return Optional.ofNullable(mongoTemplate.findOne(
                FindNotConfirmedByAddressAndSendingTypeAndCodeQuery
                        .of(address, sendingType, code).getQuery(),
                ConfirmationCodeDoc.class));
    }

    @Override
    public List<ConfirmationCodeEntity> findNotSent(Long limit) {
        return mongoTemplate.find(new FindNotSentQuery(limit).getQuery(), ConfirmationCodeDoc.class).stream().collect(Collectors.toList());
    }

    @Override
    public ConfirmationCodeEntity save(ConfirmationCodeEntity entity) {
        mongoTemplate.save(entity, CollectionNameUtils.getCollectionName(ConfirmationCodeDoc.class));
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        Long deleteCount = mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), CollectionNameUtils.getCollectionName(ConfirmationCodeDoc.class)).getDeletedCount();
        return deleteCount > 0 ? true : false;
    }

    @Override
    public Optional<ConfirmationCodeEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, ConfirmationCodeDoc.class));
    }

    @Override
    public SearchResult<ConfirmationCodeEntity> find(SearchFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, ConfirmationCodeDoc.class).stream().collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), ConfirmationCodeDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), ConfirmationCodeDoc.class);
    }
}
