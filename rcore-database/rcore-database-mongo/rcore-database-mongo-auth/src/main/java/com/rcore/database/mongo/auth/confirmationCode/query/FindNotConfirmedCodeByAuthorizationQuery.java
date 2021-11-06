package com.rcore.database.mongo.auth.confirmationCode.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindNotConfirmedCodeByAuthorizationQuery implements ExampleQuery {

    private final String authorizationId;

    @Override
    public Criteria getCriteria() {
        return new Criteria("authorizationId").is(authorizationId)
                .and("confirmationStatus").is(ConfirmationCodeEntity.ConfirmationStatus.NOT_CONFIRMED);
    }
}
