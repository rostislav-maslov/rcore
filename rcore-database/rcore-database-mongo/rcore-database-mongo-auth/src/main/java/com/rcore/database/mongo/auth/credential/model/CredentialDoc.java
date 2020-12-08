package com.rcore.database.mongo.auth.credential.model;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
public class CredentialDoc extends CredentialEntity {
}
