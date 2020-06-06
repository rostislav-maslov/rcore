package com.rcore.adapter.domain.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseEntityDTO {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String timeZone;
}
