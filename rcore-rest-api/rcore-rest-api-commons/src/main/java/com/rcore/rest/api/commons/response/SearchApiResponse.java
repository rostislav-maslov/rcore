package com.rcore.rest.api.commons.response;

import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static <T, R> SearchApiResponse<R> with(SearchResult<T> searchResult, Function<T, R> map) {
        return withItemsAndCount(searchResult.getItems().stream().map(map).collect(Collectors.toList()), searchResult.getCount());
    }
}
