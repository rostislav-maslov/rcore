package com.rcore.restapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("Результат")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchApiResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
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
