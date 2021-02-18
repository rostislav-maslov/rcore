package com.rcore.rest.api.commons.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchApiResponse<T> {
    private Long count;

    private List<T> items;

    public SearchApiResponse(List<T> items) {
        this.items = items;
    }

    public static <T> SearchApiResponse<T> withItems(List<T> items) {
        return new SearchApiResponse<>(items);
    }

    public static <T> SearchApiResponse<T> withItemsAndCount(List<T> items, Long count) {
        return new SearchApiResponse<>(count, items);
    }
}
