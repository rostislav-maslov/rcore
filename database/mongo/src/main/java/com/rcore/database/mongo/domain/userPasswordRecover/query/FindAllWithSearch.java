package com.rcore.database.mongo.domain.userPasswordRecover.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAllWithSearch extends AbstractExampleQuery<UserPasswordRecoverEntity> {

    private String roleId;

    public FindAllWithSearch(SearchFilters request) {
        super(request);
    }

    public FindAllWithSearch withRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public Criteria getCriteria() {

        return Criteria.where("email").regex(this.query, "i");
    }
}
