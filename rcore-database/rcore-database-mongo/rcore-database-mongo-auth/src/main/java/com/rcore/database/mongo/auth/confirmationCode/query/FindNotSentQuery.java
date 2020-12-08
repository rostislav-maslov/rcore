package com.rcore.database.mongo.auth.confirmationCode.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindNotSentQuery extends AbstractExampleQuery {

    public FindNotSentQuery(Long limit) {
        super(SearchFilters.builder()
                .limit(limit)
                .offset(0l)
                .sortName("id")
                .build());

    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("sendingStatus").is(ConfirmationCodeEntity.SendingStatus.WAITING)
        );
    }
}
