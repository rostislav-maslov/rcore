package com.rcore.database.mongo.domain.token.query;

import com.rcore.commons.utils.StringUtils;
import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.filters.AccessTokenFilters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Objects;

public class FindAccessTokensWithFilters extends AbstractExampleQuery<AccessTokenEntity> {
    private final AccessTokenFilters filters;

    public FindAccessTokensWithFilters(AccessTokenFilters filters) {
        super(filters);
        this.filters = filters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                StringUtils.hasText(query)
                        ? Criteria.where("id").regex(this.query, "i")
                        : new Criteria(),
                StringUtils.hasText(filters.getUserId())
                        ? Criteria.where("userId").is(filters.getUserId())
                        : new Criteria(),
                StringUtils.hasText(filters.getCreateFromRefreshTokenId())
                        ? Criteria.where("createFromRefreshTokenId").is(filters.getCreateFromRefreshTokenId())
                        : new Criteria(),
                Objects.nonNull(filters.getStatus())
                        ? Criteria.where("status").is(filters.getStatus())
                        : new Criteria()
        );
    }
}
