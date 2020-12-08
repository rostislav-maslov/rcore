package com.rcore.database.mongo.auth.confirmationCode.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class ExistNotConfirmedCodeQuery implements ExampleQuery {

    private final String authorizationId;

    @Override
    public Criteria getCriteria() {
        return new Criteria("authorizationId").is(authorizationId);
    }
}
