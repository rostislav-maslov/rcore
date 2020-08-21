package com.rcore.restapi.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@ApiModel("Модель ошибки")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExceptionDTO {

    @ApiModelProperty("Данные для отображения")
    private PresentationData presentationData;

    @ApiModelProperty("Домен")
    private String domain;

    @ApiModelProperty("Детализация")
    private String details;

    @ApiModel("Данные для отображения")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class PresentationData {

        @ApiModelProperty("Заголовок ошибки")
        private String title;

        @ApiModelProperty("Описание ошибки")
        private String message;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PresentationData)) return false;
            PresentationData that = (PresentationData) o;
            return Objects.equals(title, that.title) &&
                    Objects.equals(message, that.message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExceptionDTO)) return false;
        ExceptionDTO that = (ExceptionDTO) o;
        return Objects.equals(presentationData, that.presentationData) &&
                Objects.equals(domain, that.domain) &&
                Objects.equals(details, that.details);
    }
}
