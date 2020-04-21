package com.rcore.admincore.domain.user.exceptions;

import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.commons.utils.DomainUtils;
import com.rcore.restapi.exceptions.BaseApiException;

public class UserAlreadyExistApiException extends BaseApiException {

    public UserAlreadyExistApiException() {
        super("Пользователь уже существует", DomainUtils.getDomain(UserDTO.class), "IS_EXIST");
    }
}
