package com.rcore.adapter.domain.picture.dto;

import com.rcore.domain.picture.entity.PictureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PictureDTO {
    protected String id;
    protected String fileName;
    protected String filePath;
    protected Boolean isPrivate;
    protected Map<String, PictureEntity.Size> sizes;
    protected Date createdAt;
    protected Date updatedAt;
    protected String timeZone;

    public Optional<PictureEntity.Size> getSize(Integer width) {
        if (sizes == null)
            return Optional.empty();

        return sizes.values()
                .stream()
                .filter(size -> size.getWidth() == width)
                .findFirst();
    }
}
