package com.rcore.database.mongo.domain.token.model;

import com.rcore.domain.token.entity.AccessTokenEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@SuperBuilder
@NoArgsConstructor
public class AccessTokenDoc extends AccessTokenEntity {
}
