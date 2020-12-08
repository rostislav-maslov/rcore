package com.rcore.domain.file.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class FileEntity extends BaseEntity {

    protected String id;

    /**
     * Имя файла
     */
    protected String fileName;

    /**
     * Формат данных
     */
    protected String dataFormat;

    /**
     * Флаг - приватный файл
     */
    protected Boolean isPrivate = false;

    /**
     * Путь к файлу
     */
    protected String filePath;

}
