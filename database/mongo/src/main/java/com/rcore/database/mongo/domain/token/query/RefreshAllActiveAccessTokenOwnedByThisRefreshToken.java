package com.rcore.database.mongo.domain.token.query;

import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.domain.token.model.AccessTokenDoc;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor(staticName = "of")
public class RefreshAllActiveAccessTokenOwnedByThisRefreshToken extends AbstractModifyQuery<AccessTokenDoc> {
    private final String refreshTokenId;

    @Override
    public Update getUpdate() {
        return Update.update("status", RefreshTokenEntity.Status.REFRESHED);
    }

    @Override
    public FindAndModifyOptions getModifyOptions() {
        return FindAndModifyOptions.options().returnNew(true);
    }

    @Override
    public Criteria getCriteria() {
        return Criteria.where("status").is(RefreshTokenEntity.Status.ACTIVE)
                .and("createFromRefreshTokenId").is(refreshTokenId);
    }
}
