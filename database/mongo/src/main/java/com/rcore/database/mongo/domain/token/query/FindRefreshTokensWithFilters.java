package com.rcore.database.mongo.domain.token.query;

import com.rcore.commons.utils.StringUtils;
import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.filters.RefreshTokenFilters;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Objects;

public class FindRefreshTokensWithFilters extends AbstractExampleQuery<RefreshTokenEntity> {
    private final RefreshTokenFilters filters;

    public FindRefreshTokensWithFilters(RefreshTokenFilters filters) {
        super(filters);
        this.filters = filters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                StringUtils.hasText(filters.getUserId())
                        ? Criteria.where("userId").is(filters.getUserId())
                        : new Criteria(),
                Objects.nonNull(filters.getStatus())
                        ? Criteria.where("status").is(filters.getStatus())
                        : new Criteria()
        );
    }
}