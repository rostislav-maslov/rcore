package com.rcore.database.mongo.domain.user.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchFilters;
import com.rcore.domain.user.entity.UserEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

public class FindAllWithSearch extends AbstractExampleQuery<UserEntity> {

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

        return new Criteria().andOperator(
                StringUtils.hasText(this.query) ?
                        new Criteria().orOperator(
                                Criteria.where("firstName").regex(this.query, "i"),
                                Criteria.where("lastName").regex(this.query, "i"),
                                Criteria.where("fullName").regex(this.query, "i"),
                                Criteria.where("secondName").regex(this.query, "i"),
                                Criteria.where("email").regex(this.query, "i"),
                                Criteria.where("phoneNumber").regex(this.query, "i")
                        ) : new Criteria(),
                roleId != null ? Criteria.where("roles").elemMatch(Criteria.where("_id").is(roleId)) : new Criteria()
        );
    }
}
