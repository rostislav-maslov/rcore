package com.rcore.domain.file.port;

import com.rcore.domain.file.entity.FileEntity;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface FileStorage {

    /**
     * Сохранение файла
     *
     * @param file - File
     * TODO возможно, стоит добавить contentType
     * @return Возвращает filePath
     */
    String store(File file);

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
