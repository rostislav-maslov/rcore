package com.rcore.database.mongo.file.port.model;

import com.rcore.domain.file.entity.FileEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@CompoundIndexes({
        @CompoundIndex(def = "{'filePath': 1}")
})
@Document("fileEntity")
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
