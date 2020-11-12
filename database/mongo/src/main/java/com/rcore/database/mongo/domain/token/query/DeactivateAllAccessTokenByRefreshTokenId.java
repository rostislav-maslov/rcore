package com.rcore.database.mongo.domain.token.query;

import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.domain.token.model.AccessTokenDoc;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor(staticName = "of")
public class DeactivateAllAccessTokenByRefreshTokenId extends AbstractModifyQuery<AccessTokenDoc> {

    private final String refreshTokenId;

    @Override
    public Update getUpdate() {
        return Update.update("status", RefreshTokenEntity.Status.INACTIVE);
    }

    @Override
    public FindAndModifyOptions getModifyOptions() {
        return FindAndModifyOptions.options().returnNew(true);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("createFromRefreshTokenId").is(refreshTokenId),
                Criteria.where("status").ne(RefreshTokenEntity.Status.EXPIRED)
        );
    }
}
