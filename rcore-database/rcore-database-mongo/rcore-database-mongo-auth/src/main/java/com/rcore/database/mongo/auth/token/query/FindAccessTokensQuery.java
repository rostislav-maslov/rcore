package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.auth.token.port.filter.AccessTokenFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAccessTokensQuery extends AbstractExampleQuery {

    private final AccessTokenFilters filters;

    public FindAccessTokensQuery(AccessTokenFilters filters) {
        super(filters);
        this.filters = filters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                filters.getCredentialId() != null ? Criteria.where("credential.$id").is(filters.getCredentialId()) : new Criteria(),
                filters.getCreateByRefreshTokenId() != null ? Criteria.where("createByRefreshTokenId").is(filters.getCreateByRefreshTokenId()) : new Criteria(),
                filters.getStatus() != null ? Criteria.where("status").is(filters.getStatus()) : new Criteria()
        );
    }
}
