package com.rcore.domain.security.usecase.secured;

import com.rcore.domain.security.port.CredentialVerifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SecuredUseCase {

    private final CredentialVerifier credentialVerifier;

    /**
     * Проверяем наличие доступа к юзкейсу в учетных данных
     */
    protected void checkAccess() {
        credentialVerifier.checkAccess(getUseCaseAccess());
    }

    /**
     * Имя юзкейса явлется его уникальным идентификатором
     * @return
     */
    private String getUseCaseAccess() {
        return this.getClass().getSimpleName();
    }



}
