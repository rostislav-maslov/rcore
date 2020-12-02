package com.rcore.database.mongo.domain.token.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAllWithSearch extends AbstractExampleQuery<RefreshTokenEntity> {

    public FindAllWithSearch(SearchFilters request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
