package com.rcore.domain.file.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.File;
import java.io.InputStream;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class FileEntity extends BaseEntity {
    protected String id;

    /**
     * Имя файла
     */
    protected String fileName;

    /**
     * Флаг приватного файла
     */
    protected Boolean isPrivate = false;

    /**
     * Путь к файлу
     * В контексте mongo - идентификатор в gridFs
     */
    protected String filePath;

}
