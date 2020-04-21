package com.rcore.database.mongo.domain.user.query;

import com.rcore.database.mongo.common.query.ExampleQuery;
import com.rcore.database.mongo.domain.user.model.UserDoc;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class FindByEmailQuery implements ExampleQuery<UserDoc> {

    private final String email;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("email").is(email);
    }
}
