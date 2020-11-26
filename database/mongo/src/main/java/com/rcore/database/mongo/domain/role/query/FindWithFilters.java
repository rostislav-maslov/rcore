package com.rcore.database.mongo.domain.role.query;

import com.rcore.commons.utils.StringUtils;
import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.filters.RoleFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindWithFilters extends AbstractExampleQuery<RoleEntity> {

    private final RoleFilters roleFilters;

    public FindWithFilters(RoleFilters roleFilters) {
        super(roleFilters);
        this.roleFilters = roleFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                roleFilters.getAuthorizationTypes() != null && !roleFilters.getAuthorizationTypes().isEmpty()
                        ? Criteria.where("availableAuthTypes").in(roleFilters.getAuthorizationTypes())
                        : new Criteria(),
                StringUtils.hasText(roleFilters.getQuery()) ? Criteria.where("name").regex(roleFilters.getQuery(), "i") : new Criteria()
        );
    }
}
