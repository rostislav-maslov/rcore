package com.rcore.domain.role.entity;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.entity.BaseEntity;
import lombok.*;
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
    protected Set<Access> accesses = new HashSet<>();

    /**
     * Доступные типы авторизации для роли
     */
    protected List<AuthType> availableAuthTypes = new ArrayList<>();

    /**
     * Тип авторизации
     */
    public enum AuthType {
        SMS, EMAIL
    }


}
