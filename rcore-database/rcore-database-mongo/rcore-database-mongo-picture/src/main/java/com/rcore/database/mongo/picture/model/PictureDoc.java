package com.rcore.database.mongo.picture.model;

import com.rcore.domain.picture.entity.PictureEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@SuperBuilder
@NoArgsConstructor
public class PictureDoc extends PictureEntity {
    public PictureDoc toEntity() {
        return this;
    }

    public static PictureDoc toDoc(PictureEntity entity) {
        return PictureDoc.builder()
                .id(entity.getId())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .isPrivate(entity.getIsPrivate())
                .sizes(entity.getSizes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
