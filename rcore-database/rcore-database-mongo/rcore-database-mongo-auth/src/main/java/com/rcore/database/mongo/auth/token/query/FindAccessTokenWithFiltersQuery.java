package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.commons.port.dto.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAccessTokenWithFiltersQuery extends AbstractExampleQuery {

    public FindAccessTokenWithFiltersQuery(SearchFilters searchFilters) {
        super(searchFilters);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
