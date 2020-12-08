package com.rcore.domain.commons.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchResult<T> {
    private List<T> items = new ArrayList<>();
    private Long count = 0l;

    public static <T> SearchResult<T> withItemsAndCount(List<T> items, Long count) {
        return new SearchResult<>(items, count);
    }
}

