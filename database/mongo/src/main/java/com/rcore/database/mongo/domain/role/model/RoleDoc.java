package com.rcore.database.mongo.domain.role.model;

import com.rcore.domain.role.entity.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'name': 1}", name = "name", unique = true)
})
@SuperBuilder
@Data
@NoArgsConstructor
public class RoleDoc extends RoleEntity {

}
