package com.rcore.database.mongo.auth.confirmationCode.model;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
public class ConfirmationCodeDoc extends ConfirmationCodeEntity {
}
