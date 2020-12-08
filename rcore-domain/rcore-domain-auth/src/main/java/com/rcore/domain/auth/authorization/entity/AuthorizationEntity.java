package com.rcore.domain.auth.authorization.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Сущность авторизации
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class AuthorizationEntity extends BaseEntity {
    /**
     * Идентификатор
     */
    protected String id;

    /**
     * Тип авторизации
     */
    protected Type type;

    /**
     * Идентификатор учетных данных
     */
    protected String credentialId;

    /**
     * Данные, используемые во время авторизации
     */
    protected AuthorizationData authorizationData;

    /**
     * Статус авторизации
     */
    protected Status status = Status.NEW;

    /**
     * Причина отклонения авторизации
     */
    protected RejectionCause rejectionCause;

    /**
     * Идентификатор полученного accessToken
     */
    protected String accessTokenId;

    /**
     * Идентификатор полученного refreshToken
     */
    protected String refreshTokenId;

    public enum Type {
        PASSWORD,
        SMS_2FA,
        EMAIL_2FA
    }

    public enum Status {
        NEW, SUCCESS, REJECTED, PENDING_CONFIRMATION
    }

    public enum RejectionCause {
        BAD_CREDENTIAL, INVALID_CONFIRMATION_CODE
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AuthorizationData {
        private String username;
        private String email;
        private String phone;
        private String code;

        public static AuthorizationData passwordAuthenticationData(String username) {
            return AuthorizationData.builder()
                    .username(username)
                    .build();
        }

        public static AuthorizationData sms2FAuthenticationData(String phone) {
            return AuthorizationData.builder()
                    .phone(phone)
                    .build();
        }

        public static AuthorizationData email2FAuthenticationData(String username) {
            return AuthorizationData.builder()
                    .phone(username)
                    .build();
        }
    }

    /**
     * Methods
     */

    public boolean isPendingConfirm() {
        return this.status == Status.PENDING_CONFIRMATION;
    }

    public void setSuccess(String accessTokenId, String refreshTokenId) {
        this.status = Status.SUCCESS;
        this.accessTokenId = accessTokenId;
        this.refreshTokenId = refreshTokenId;
    }
}
