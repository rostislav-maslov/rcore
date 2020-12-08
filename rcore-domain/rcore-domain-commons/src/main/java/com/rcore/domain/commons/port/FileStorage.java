package com.rcore.domain.commons.port;

import java.io.InputStream;
import java.util.Optional;

/**
 * Интерфейс для хранения файлов
 */
public interface FileStorage {

    /**
     * Сохранение файла
     *
     * @return Возвращает filePath
     */
    String store(InputStream content, String fileName, String dataFormat);

    /**
     * Удаление файла
     *
     * @param filePath - путь к файлу
     */
    void remove(String filePath);

    /**
     * Получение InputStream для файла
     *
     * @param filePath - путь к файлу
     * @return
     */
    Optional<InputStream> getFileStreamByPath(String filePath);

}
