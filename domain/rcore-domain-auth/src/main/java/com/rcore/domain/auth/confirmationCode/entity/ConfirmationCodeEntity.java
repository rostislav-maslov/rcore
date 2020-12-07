package com.rcore.domain.auth.confirmationCode.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ConfirmationCodeEntity extends BaseEntity {
    protected String id;

    /**
     * Проверочный код
     */
    protected String code;

    /**
     * Получатель
     */
    protected Recipient recipient;

    /**
     * Идентификатор авторизации, в ходе которой был сгенерирован код
     */
    protected String authorizationId;

    /**
     * Статус отправки кода получателю
     */
    protected SendingStatus sendingStatus = SendingStatus.WAITING;

    /**
     * Статус подтверждения кода
     */
    protected ConfirmationStatus confirmationStatus = ConfirmationStatus.NOT_CONFIRMED;

    /**
     * Время подтверждения
     */
    protected LocalDateTime confirmationDate;

    /**
     * Время отправки получателю
     */
    protected LocalDateTime sendingDate;

    /**
     * Время истечения срока жизни
     */
    protected LocalDateTime expiredAt;

    /**
     * Вермя жизни, секунды
     */
    protected Long ttl;

    public enum SendingStatus {
        WAITING, IN_PROGRESS, SENT, ERROR
    }

    public enum ConfirmationStatus {
        NOT_CONFIRMED, CONFIRMED
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @Data
    @Builder
    public static class Recipient {

        /**
         * Идентификатор учетной записи
         */
        private String credentialId;

        /**
         * Адрес получения кода - телефон либо email
         */
        private String address;

        /**
         * Тип получения - sms либо email
         */
        private SendingType sendingType;

        public Recipient(String address, SendingType sendingType) {
            this.address = address;
            this.sendingType = sendingType;
        }

        public static Recipient of(String address, SendingType sendingType) {
            return new Recipient(address, sendingType);
        }

        public enum SendingType {
            SMS, EMAIL
        }
    }

    /**
     * Methods
     */

    public void setConfirmed() {
        this.confirmationStatus = ConfirmationStatus.CONFIRMED;
        this.confirmationDate = LocalDateTime.now();
    }

    public void setInProgress() {
        this.sendingStatus = SendingStatus.IN_PROGRESS;
    }

    public void setSent() {
        this.sendingStatus = SendingStatus.SENT;
        this.sendingDate = LocalDateTime.now();
    }

    public void setError() {
        this.sendingStatus = SendingStatus.ERROR;
        this.sendingDate = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredAt);
    }

    public void installExpiredAt(Long ttl) {
        this.ttl = ttl;
        this.expiredAt = LocalDateTime.now()
                .plusSeconds(ttl);
    }


}
