package com.rcore.domain.base.port;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SearchRequest {
    private String query;
    private Long limit;
    private Long offset;
    private String sortName;
    private DirectionEnum sortDirection = DirectionEnum.DESC;
}
