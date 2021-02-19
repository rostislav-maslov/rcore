package com.rcore.domain.picture.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class PictureEntity extends BaseEntity<String> {

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
     * Флаг - изображение является ПРЕДЗАГРУЖЕННЫМ. По истечению определенного времени его можно удалить, если флаг не изменится на true
     */
    protected Boolean isBuffer = true;

    /**
     * Путь к файлу
     */
    protected String filePath;

    /**
     * Информация о доступных размерах изображения
     */
    protected Map<String, Size> sizes = new HashMap<>();

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Size {
        public static final String SEPARATOR = "x";

        private Integer width = 0;
        private Integer height = 0;
        private String filePath;
    }

}
