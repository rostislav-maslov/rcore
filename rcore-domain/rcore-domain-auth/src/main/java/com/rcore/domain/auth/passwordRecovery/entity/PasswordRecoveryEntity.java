package com.rcore.domain.auth.passwordRecovery.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Восстановление пароля учетных данных
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class PasswordRecoveryEntity extends BaseEntity<String> {

    /**
     * Идентификатор учетной запси
     */
    protected String credentialId;

    /**
     * Email для восстановления пароля
     */
    protected String email;

    protected Status status = Status.IN_PROGRESS;

    public enum Status {
        IN_PROGRESS, COMPLETED
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }

    public void setCompleted() {
        this.status = Status.COMPLETED;
    }
}
