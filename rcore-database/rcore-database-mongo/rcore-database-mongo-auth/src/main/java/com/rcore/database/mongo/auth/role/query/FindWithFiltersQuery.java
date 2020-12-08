package com.rcore.database.mongo.auth.role.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.auth.role.port.filters.RoleFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindWithFiltersQuery extends AbstractExampleQuery {

    private final RoleFilters roleFilters;

    public FindWithFiltersQuery(RoleFilters roleFilters) {
        super(roleFilters);
        this.roleFilters = roleFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("availableAuthTypes").in(roleFilters.getAuthorizationTypes())
        );
    }
}
