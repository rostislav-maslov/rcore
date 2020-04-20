package com.rcore.admincore.domain.user.web.api;

import com.rcore.domain.user.entity.SocialAccount;
import com.rcore.domain.user.entity.UserStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("Пользователь")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserWeb {

    @ApiModelProperty("Идентификатор")
    private String id;

    @ApiModelProperty("Статус")
    private UserStatus status;

    @ApiModelProperty("Имя")
    private String firstName;

    @ApiModelProperty("Фамилия")
    private String lastName;

    @ApiModelProperty("Отчество")
    private String secondName;

    @ApiModelProperty("Полное имя")
    private String fullName;

    @ApiModelProperty("Email")
    private String email;

    @ApiModelProperty("Номер телефонв")
    private Long phoneNumber;

    @ApiModelProperty("Соц. сети")
    private List<SocialAccount> socialAccounts;

    @ApiModelProperty("Кол-во провальных авторизации")
    private Integer fails = 0;

    @ApiModelProperty("Дата последней провальной авторизации")
    private LocalDateTime lastFailDate;

    @ApiModelProperty("Дата создания")
    private LocalDateTime createdAt;

    @ApiModelProperty("Дата последнего обновления")
    private LocalDateTime updatedAt;
}
