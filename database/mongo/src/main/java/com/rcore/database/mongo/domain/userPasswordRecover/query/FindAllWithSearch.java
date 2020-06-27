package com.rcore.database.mongo.domain.userPasswordRecover.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

public class FindAllWithSearch extends AbstractExampleQuery<UserPasswordRecoverEntity> {

    private String roleId;

    public FindAllWithSearch(SearchRequest request) {
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
