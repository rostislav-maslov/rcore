package com.rcore.database.mongo.file.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.commons.port.dto.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindWithFiltersFileQuery extends AbstractExampleQuery {

    public FindWithFiltersFileQuery(SearchFilters request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
