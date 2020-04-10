package com.rcore.domain.picture.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PictureEntity extends BaseEntity {
    protected String id;
    protected String fileName;
    protected String filePath;
    protected Boolean isPrivate = false;

    protected Map<String, Size> sizes = new HashMap<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Size {
        public static final String SEPARATOR = "x";

        private Integer width = 0;
        private Integer height = 0;
        private String filePath;

        public String getStringSize() {
            return width + SEPARATOR + height;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Size size = (Size) o;
            if (!height.equals(size.height)) return false;
            if (!width.equals(size.width)) return false;

            return true;
        }
    }

    public void addSize(Size size) {
        if (sizes.size() >= 10) {
            return;
        }
        sizes.put(size.getStringSize(), size);
    }

}
