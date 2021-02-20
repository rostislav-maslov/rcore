package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;

@RequiredArgsConstructor(staticName = "of")
public class FindAllActiveByUserIdQuery extends AbstractExampleQuery {

    private final String userId;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("userId").is(userId)
                .and("status").is(RefreshTokenEntity.Status.ACTIVE)
                .and("expireAt").lte(LocalDateTime.now());
    }
}
