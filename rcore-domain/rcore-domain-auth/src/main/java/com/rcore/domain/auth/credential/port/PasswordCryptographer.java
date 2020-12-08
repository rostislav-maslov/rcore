package com.rcore.domain.auth.credential.port;

/**
 * Шифровальщик пароля
 */
public interface PasswordCryptographer {
    /**
     * Шифрование пароля
     * @param cleanPassword - незашифрованный пароль
     * @param credentialId - идентификатор учетной записи
     * @return
     */
    String encrypt(String cleanPassword, String credentialId);

    /**
     * Проверка пароля
     * @param cleanPassword - незашифрованный пароль
     * @param encryptedPassword - зашифрованный пароль
     * @param credentialId - идентификатор учетной записи
     * @return
     */
    Boolean validate(String cleanPassword, String encryptedPassword, String credentialId);
}
