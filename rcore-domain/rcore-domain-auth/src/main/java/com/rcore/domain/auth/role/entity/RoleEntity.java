package com.rcore.domain.auth.role.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleEntity extends BaseEntity<String> {

    /**
     * Наименование роли
     */
    protected String name;

    /**
     * Доступные юзкейсы для роли
     */
    protected List<String> availableUseCases = new ArrayList<>();

    /**
     * Флаг - имеет ли данная роль безграничный доступ
     */
    protected Boolean hasBoundlessAccess = false;

    /**
     * Доступные типы авторизации для роли
     */
    protected List<AuthType> availableAuthTypes = new ArrayList<>();

    /**
     * Тип авторизации
     */
    public enum AuthType {
        TWO_FACTOR, PASSWORD
    }

    public void addUseCaseAccess(UseCase useCase) {
        this.availableUseCases.add(useCase.getClass().getName());
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
        return this.availableUseCases.contains(accesses);
    }


}
