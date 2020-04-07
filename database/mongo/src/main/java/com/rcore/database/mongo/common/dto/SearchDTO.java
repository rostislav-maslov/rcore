package com.rcore.database.mongo.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@Data
public class SearchDTO {
    private String query = "";
    private Integer limit = 20;
    private Integer offset = 0;
    private String sortName = "id";
    protected Sort.Direction sortDirection = Sort.Direction.DESC;

    public SearchDTO(String query) {
        this.query = query;
    }

    public SearchDTO(String query, Integer limit) {
        this.query = query;
        this.limit = limit;
    }
}
