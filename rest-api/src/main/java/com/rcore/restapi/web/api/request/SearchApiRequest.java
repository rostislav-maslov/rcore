package com.rcore.restapi.web.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rcore.domain.base.port.SearchRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Поиск с пагинацией")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class SearchApiRequest {

    @ApiModelProperty("Поисковая строка")
    private String query = "";

    @ApiModelProperty("Кол-во элементов для отображения")
    private Long limit = 20l;

    @ApiModelProperty("Кол-во пропускаемых элементов")
    private Long offset = 0l;

    @ApiModelProperty("Имя поле, по которому нужно отсортировать")
    private String sortName = "id";

    @ApiModelProperty("Направление сортировки")
    protected String sortDirection = "DESC";

    public SearchApiRequest(String query) {
        this.query = query;
    }

    public SearchApiRequest(String query, Long limit) {
        this.query = query;
        this.limit = limit;
    }

    public SearchRequest toSearchRequest() {
        return SearchRequest.builder()
                .limit(this.limit)
                .offset(this.offset)
                .query(this.query)
                .sortDirection(this.sortDirection)
                .sortName(this.sortName)
                .build();
    }
}
