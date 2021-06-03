package com.rcore.database.mongo.auth.authorization.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor
public class FindPendingConfirmationByAddressQuery implements ExampleQuery {

    private final String address;

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("status").is(AuthorizationEntity.Status.PENDING_CONFIRMATION),
                new Criteria().orOperator(
                        Criteria.where("authorizationData.phone").is(address),
                        Criteria.where("authorizationData.email").is(address)
                )
        );
    }
}
