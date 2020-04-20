package com.rcore.admincore.domain.picture.web.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("Изображение")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PictureWeb {

    @ApiModelProperty("Идентификатор")
    private String id;

    @ApiModelProperty("Наименование")
    private String name;

    @ApiModelProperty("Дата создания")
    private LocalDateTime createdAt;

}
