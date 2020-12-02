package com.rcore.domain.auth.role.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RoleEntity extends BaseEntity {
    /**
     * Идентификатор
     */
    protected String id;

    /**
     * Наименование роли
     */
    protected String name;

    /**
     * Локализованное название
     */
    protected String locale;

    /**
     * Доступы данной роли
     */
    protected List<String> accesses = new ArrayList<>();

    /**
     * Флаг - имеет ли данная роль безграничный доступ
     */
    protected Boolean hasBoundlessAccess = false;

    /**
     * Доступные типы авторизации для роли
     */
    protected List<AuthType> availableAuthTypes = Arrays.asList(AuthType.SMS, AuthType.EMAIL);

    /**
     * Тип авторизации
     */
    public enum AuthType {
        SMS, EMAIL
    }

    /**
     * Проверка доступов для роли
     * @param accesses
     * @return
     */
    public boolean checkAccesses(List<String> accesses) {
        //Если роль имеет полный доступ - не проверяем accesses
        if (hasBoundlessAccess)
            return true;

        //Проверяем содержание доступов в this.accesses
        return this.accesses.contains(accesses);
    }


}
