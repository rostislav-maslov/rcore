package com.rcore.domain.security.port;

import com.rcore.domain.security.exceptions.AccessDeniedException;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.model.CredentialDetails;

/**
 * Интерфейс, содержищий методы проверки доступов для авторизованных учетных данных
 */
public interface CredentialVerifier {

    /**
     * Проверка доступа для пользоватля, совершающего запрос к бизнес логике
     * @throws AccessDeniedException - доступ запрещен
     */
    default void checkAccess(String access) throws AccessDeniedException, CredentialNotFoundException {
        CredentialDetails credentialDetails = getAuthorizedCredential();
        if (!credentialDetails.hasAccess(access))
            throw new AccessDeniedException();
    }

    /**
     * Получение учетных данных текущей сессии
     * @return - учетные данные
     * @throws CredentialNotFoundException - в данной сессии нет авторизованных учетных данных
     */
    CredentialDetails getAuthorizedCredential() throws CredentialNotFoundException;
}
