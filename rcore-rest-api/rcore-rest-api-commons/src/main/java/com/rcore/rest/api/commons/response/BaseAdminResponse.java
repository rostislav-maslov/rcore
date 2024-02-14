package com.rcore.rest.api.commons.response;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseAdminResponse {
    private String id;

    private Instant createdAt;
    private Instant updatedAt;

//    /**
//     * Заполнить ответ стандартными значениями
//     * <p>
//     * Пример
//     * <pre>
//     * {@code
//     * fill(CityResponse.builder(),cityEntity)
//     * .name(cityEntity.getName())
//     * .build()
//     * }
//     * </pre>
//     */
//    public static <T extends BaseAdminResponse.BaseAdminResponseBuilder<?, ?>> T fill(T builder, BaseEntity<?> entity) {
//        //noinspection unchecked
//        return (T) builder
//                .id(entity.getId().toString())
//                .createdAt(entity.getCreatedAt())
//                .updatedAt(entity.getUpdatedAt());
//    }
}
