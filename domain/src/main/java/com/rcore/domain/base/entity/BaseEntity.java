package com.rcore.domain.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class BaseEntity {
    protected Date createdAt = new Date();
    protected Date updatedAt  = new Date();
    protected String timeZone = "GMT";

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
