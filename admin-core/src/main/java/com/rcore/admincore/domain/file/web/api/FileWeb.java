package com.rcore.admincore.domain.file.web.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("Файл")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FileWeb {

    @ApiModelProperty("Идентификатор")
    private String id;

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Дата создания")
    private LocalDateTime createdAt;
}
