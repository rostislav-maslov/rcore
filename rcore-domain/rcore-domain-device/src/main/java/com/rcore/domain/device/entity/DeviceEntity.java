package com.rcore.domain.device.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

/**
 * Устройство пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceEntity extends BaseEntity<String> {

    /**
     * Уникальный токен устройства
     */
    protected String token;

    /**
     * Идентификатор учетной записи
     */
    protected String credentialId;

    /**
     * Информация об устройстве
     */
    protected String info;

    /**
     * Версия сборки приложения
     */
    protected String buildVersion;

    /**
     * Идентификатор операционной системы
     */
    protected String operatingSystemId;

    /**
     * Тип
     */
    protected Type type;

    /**
     * Дополнительная информация об устройстве
     */
    protected HashMap<String, Object> data = new HashMap<>();

    public enum Type {
        WEB, APPLICATION
    }
}
