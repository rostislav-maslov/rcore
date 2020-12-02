package com.rcore.database.mongo.domain.role.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.role.entity.RoleEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

public class FindAllWithSearch extends AbstractExampleQuery<RoleEntity> {

    public FindAllWithSearch(SearchFilters request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                StringUtils.hasText(this.query) ? new Criteria().andOperator(
                        Criteria.where("name").regex(this.query, "i")
                ) : new Criteria()
        );
    }
}
