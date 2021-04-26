package com.rcore.database.mongo.domain.token.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.domain.token.model.AccessTokenDoc;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

public class DeactivateAllAccessTokenByRefreshTokenId extends AbstractExampleQuery<AccessTokenEntity> {
    private final String refreshTokenId;

    public DeactivateAllAccessTokenByRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("createFromRefreshTokenId").is(refreshTokenId),
                Criteria.where("status").ne(RefreshTokenEntity.Status.EXPIRED),
                Criteria.where("status").ne(RefreshTokenEntity.Status.REFRESHED),
                Criteria.where("status").ne(RefreshTokenEntity.Status.INACTIVE)
        );
    }
}
