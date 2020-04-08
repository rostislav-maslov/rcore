package com.rcore.restapi.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Integer limit = 20;

    @ApiModelProperty("Кол-во пропускаемых элементов")
    private Integer offset = 0;

    @ApiModelProperty("Имя поле, по которому нужно отсортировать")
    private String sortName = "id";

    @ApiModelProperty("Направление сортировки")
    protected String sortDirection = "DESC";

    public SearchApiRequest(String query) {
        this.query = query;
    }

    public SearchApiRequest(String query, Integer limit) {
        this.query = query;
        this.limit = limit;
    }
}