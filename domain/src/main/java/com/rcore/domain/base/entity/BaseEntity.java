package com.rcore.domain.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {
    protected Date createdAt = new Date();
    protected Date updatedAt  = new Date();
    protected String timeZone = "GMT";
}
