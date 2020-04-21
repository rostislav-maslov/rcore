package com.rcore.database.mongo.domain.role.query;

import com.rcore.database.mongo.common.query.ExampleQuery;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByName implements ExampleQuery<RoleEntity> {

    private final String name;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("name").is(name);
    }
}
