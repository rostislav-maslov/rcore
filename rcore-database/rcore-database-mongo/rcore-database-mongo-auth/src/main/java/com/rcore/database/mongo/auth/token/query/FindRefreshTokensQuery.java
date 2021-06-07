package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.auth.token.port.filter.AccessTokenFilters;
import com.rcore.domain.auth.token.port.filter.RefreshTokenFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindRefreshTokensQuery extends AbstractExampleQuery {

    private final RefreshTokenFilters filters;

    public FindRefreshTokensQuery(RefreshTokenFilters filters) {
        super(filters);
        this.filters = filters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                filters.getCredentialId() != null ? Criteria.where("credential.$id").is(filters.getCredentialId()) : new Criteria(),
                filters.getStatus() != null ? Criteria.where("status").is(filters.getStatus()) : new Criteria()
        );
    }
}
