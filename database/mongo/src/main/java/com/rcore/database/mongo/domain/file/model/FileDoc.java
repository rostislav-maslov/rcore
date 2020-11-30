package com.rcore.database.mongo.domain.file.model;

import com.rcore.domain.file.entity.FileEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@CompoundIndexes({
        @CompoundIndex(def = "{'filePath': 1}")
})
@Document
@NoArgsConstructor
@SuperBuilder
public class FileDoc extends FileEntity {

    public FileEntity toEntity() {
        return this;
    }

    public static FileDoc toDoc(FileEntity entity) {
        return FileDoc.builder()
                .id(entity.getId())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .isPrivate(entity.getIsPrivate())
                .createdAt(entity.getCreatedAt())
                .timeZone(entity.getTimeZone())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
