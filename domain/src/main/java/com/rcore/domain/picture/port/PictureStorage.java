package com.rcore.domain.picture.port;

import com.rcore.domain.picture.entity.PictureEntity;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface PictureStorage {

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
