package com.rcore.domain.userPasswordRecover.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserPasswordRecoverEntity extends BaseEntity {
    protected String id;
    /**
     * Количество попыток
     */
    protected Integer counter = 0;
    /**
     * Доступное количество попыток осталось
     */
    protected Integer retryLimit = 10;
    /**
     * Количество попыток осталось
     */
    protected Integer retryLeft = 10;
    protected Boolean isRecovered = false;
    protected String userId = null;
    protected String code = null;
    protected Date expiredAt = null;
    protected Long expiredTime = 1000l * 60 * 60;

    public void tryIncrement(){
        counter = counter + 1;
        retryLimit = retryLeft - 1;
    }
}
