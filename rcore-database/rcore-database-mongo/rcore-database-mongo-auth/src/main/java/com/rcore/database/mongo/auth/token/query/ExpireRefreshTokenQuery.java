package com.rcore.database.mongo.auth.token.query;

import com.rcore.database.mongo.commons.query.AbstractModifyQuery;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor(staticName = "of")
public class ExpireRefreshTokenQuery extends AbstractModifyQuery {

    private final String id;

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
        return Criteria.where("id").is(id);
    }
}
