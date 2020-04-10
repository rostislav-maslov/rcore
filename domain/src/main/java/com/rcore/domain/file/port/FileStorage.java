package com.rcore.domain.file.port;

import java.io.InputStream;
import java.util.Optional;

public interface FileStorage {

    /**
     * Сохранение файла
     *
     *
     * TODO возможно, стоит добавить contentType
     * @return Возвращает filePath
     */
    String store(InputStream content, String fileName, String contentType);

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
    Optional<InputStream> getInputStream(String filePath);
}
