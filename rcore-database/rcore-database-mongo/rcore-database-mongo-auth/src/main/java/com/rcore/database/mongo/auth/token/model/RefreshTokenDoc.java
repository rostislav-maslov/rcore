package com.rcore.database.mongo.auth.token.model;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
public class RefreshTokenDoc extends RefreshTokenEntity {
}
