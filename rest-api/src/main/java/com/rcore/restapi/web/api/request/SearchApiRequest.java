package com.rcore.restapi.web.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rcore.domain.base.port.DirectionEnum;
import com.rcore.domain.base.port.SearchRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel("Поиск с пагинацией")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    protected DirectionEnum sortDirection = DirectionEnum.DESC;

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
