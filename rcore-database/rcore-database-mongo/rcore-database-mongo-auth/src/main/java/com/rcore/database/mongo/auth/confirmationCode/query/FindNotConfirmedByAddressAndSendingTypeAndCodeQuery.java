package com.rcore.database.mongo.auth.confirmationCode.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindNotConfirmedByAddressAndSendingTypeAndCodeQuery implements ExampleQuery {

    private final String address;
    private final ConfirmationCodeEntity.Recipient.SendingType sendingType;
    private final String code;

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("recipient.address").is(address),
                Criteria.where("recipient.sendingType").is(sendingType),
                Criteria.where("code").is(code)
        );
    }
}
