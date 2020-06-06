package com.rcore.database.mongo.domain.token.model;

import com.rcore.domain.token.entity.AccessTokenEntity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
public class AccessTokenDoc extends AccessTokenEntity {
}
