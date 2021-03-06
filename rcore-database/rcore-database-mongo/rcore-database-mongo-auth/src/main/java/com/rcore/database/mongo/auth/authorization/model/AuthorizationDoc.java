package com.rcore.database.mongo.auth.authorization.model;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class AuthorizationDoc extends AuthorizationEntity {

}
