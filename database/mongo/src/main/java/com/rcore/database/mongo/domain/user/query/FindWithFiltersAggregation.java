package com.rcore.database.mongo.domain.user.query;

import com.rcore.database.mongo.common.query.AbstractAggregationQuery;
import com.rcore.database.mongo.domain.user.model.UserDoc;
import com.rcore.domain.user.port.filters.UserFilters;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class FindWithFiltersAggregation extends AbstractAggregationQuery<UserDoc, UserDoc> {

    private final UserFilters userFilters;

    public FindWithFiltersAggregation(UserFilters userFilters) {
        super(UserDoc.class, UserDoc.class, userFilters);
        this.userFilters = userFilters;
    }

    @Override
    public List<AggregationOperation> getOperations() {
        return Arrays.asList(
                LookupOperation.newLookup()
                        .from("roleDoc")
                        .localField("roles.name")
                        .foreignField("name")
                        .as("roleObjects"),
                Aggregation.match(
                        new Criteria().andOperator(
                                StringUtils.hasText(userFilters.getQuery()) ? new Criteria()
                                        .orOperator(
                                                Criteria.where("firstName").regex(userFilters.getQuery(), "i"),
                                                Criteria.where("lastName").regex(userFilters.getQuery(), "i"),
                                                Criteria.where("secondName").regex(userFilters.getQuery(), "i"),
                                                Criteria.where("fullName").regex(userFilters.getQuery(), "i"),
                                                Criteria.where("email").regex(userFilters.getQuery(), "i")
                                        )
                                        : new Criteria(),
                                StringUtils.hasText(userFilters.getRoleId()) ? Criteria.where("roleObjects").elemMatch(Criteria.where("_id").is(userFilters.getRoleId())) : new Criteria(),
                                userFilters.getAuthTypes() != null && !userFilters.getAuthTypes().isEmpty() ? Criteria.where("roleObjects").elemMatch(Criteria.where("availableAuthTypes").in(userFilters.getAuthTypes())) : new Criteria(),
                                userFilters.getStatus() != null ? Criteria.where("status").is(userFilters.getStatus()) : new Criteria()
                        )));
    }
}
