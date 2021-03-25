package com.rcore.commons.utils;

/**
 * Шифровальщик пароля
 */
public interface PasswordCryptographer {
    /**
     * Шифрование пароля
     * @param cleanPassword - незашифрованный пароль
     * @param entityId - идентификатор сущности
     * @return
     */
    String encrypt(String cleanPassword, String entityId);

    /**
     * Проверка пароля
     * @param cleanPassword - незашифрованный пароль
     * @param encryptedPassword - зашифрованный пароль
     * @param entityId - идентификатор сущности
     * @return
     */
    Boolean validate(String cleanPassword, String encryptedPassword, String entityId);
}
