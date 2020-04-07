package com.rcore.database.mongo.token.port.query;

import com.google.api.client.util.DateTime;
import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;

@RequiredArgsConstructor(staticName = "of")
public class FindAllActiveByUserId extends AbstractExampleQuery<RefreshTokenEntity> {

    private final String userId;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("userId").is(userId)
                .and("status").is(RefreshTokenEntity.Status.ACTIVE)
                .and("expireAt").lte(LocalDateTime.now());
    }
}
