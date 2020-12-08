package com.rcore.database.mongo.auth.authorization.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.commons.port.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindWithFiltersQuery extends AbstractExampleQuery {

    public FindWithFiltersQuery(SearchFilters request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
