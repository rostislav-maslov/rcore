package com.rcore.database.mongo.auth.credential.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByUsernameQuery implements ExampleQuery {

    private final String username;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("username").is(username);
    }
}
