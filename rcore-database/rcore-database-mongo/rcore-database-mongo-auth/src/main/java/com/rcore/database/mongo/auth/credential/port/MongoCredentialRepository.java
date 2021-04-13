package com.rcore.database.mongo.auth.credential.port;

import com.rcore.database.mongo.auth.credential.mapper.CredentialDocMapper;
import com.rcore.database.mongo.auth.credential.model.CredentialDoc;
import com.rcore.database.mongo.auth.credential.query.FindByEmailQuery;
import com.rcore.database.mongo.auth.credential.query.FindByPhoneQuery;
import com.rcore.database.mongo.auth.credential.query.FindByUsernameQuery;
import com.rcore.database.mongo.auth.credential.query.FindWithFiltersQuery;
import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MongoCredentialRepository implements CredentialRepository {

    private static final String collectionName = CollectionNameUtils.getCollectionName(CredentialDoc.class);
    private final MongoTemplate mongoTemplate;
    private final CredentialDocMapper credentialDocMapper;

    @Override
    public Optional<CredentialEntity> findByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByUsernameQuery.of(username).getQuery(), CredentialDoc.class))
                .map(credentialDocMapper::inverseMap);
    }

    @Override
    public Optional<CredentialEntity> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByEmailQuery.of(email).getQuery(), CredentialDoc.class))
                .map(credentialDocMapper::inverseMap);
    }

    @Override
    public Optional<CredentialEntity> findByPhone(String phone) {
        return Optional.ofNullable(mongoTemplate.findOne(FindByPhoneQuery.of(phone).getQuery(), CredentialDoc.class))
                .map(credentialDocMapper::inverseMap);
    }

    @Override
    public CredentialEntity save(CredentialEntity entity) {
        mongoTemplate.save(credentialDocMapper.map(entity), collectionName);
        return entity;
    }

    @Override
    public Boolean delete(String s) {
        return mongoTemplate.remove(Query.query(Criteria.where("_id").is(s)), collectionName).getDeletedCount() > 0;
    }

    @Override
    public Optional<CredentialEntity> findById(String s) {
        return Optional.ofNullable(mongoTemplate.findById(s, CredentialDoc.class))
                .map(credentialDocMapper::inverseMap);
    }

    @Override
    public SearchResult<CredentialEntity> find(SearchFilters filters) {
        Query query = new FindWithFiltersQuery(filters).getQuery();
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(query, CredentialDoc.class).stream().map(credentialDocMapper::inverseMap).collect(Collectors.toList()),
                mongoTemplate.count(query.limit(0).skip(0), CredentialDoc.class)
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), CredentialDoc.class);
    }
}
