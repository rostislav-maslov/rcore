package com.rcore.database.mongo.domain.role.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.role.entity.RoleEntity;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAllWithSearch extends AbstractExampleQuery<RoleEntity> {

    public FindAllWithSearch(SearchRequest request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
