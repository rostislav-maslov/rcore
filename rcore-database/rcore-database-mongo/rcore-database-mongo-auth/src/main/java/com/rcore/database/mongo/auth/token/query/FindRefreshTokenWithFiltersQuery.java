package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.commons.port.dto.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindRefreshTokenWithFiltersQuery extends AbstractExampleQuery {

    public FindRefreshTokenWithFiltersQuery(SearchFilters searchFilters) {
        super(searchFilters);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
