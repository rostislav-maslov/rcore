package com.rcore.restapi.exceptions;

import java.util.List;

public class MaxUploadSizeApiException extends BaseApiException {

    public MaxUploadSizeApiException() {
        super("Ошибка при загрузке", "Размер изображения не должен превышать 5МБ", "USER", "PICTURE_IS_TOO_LARGE");
    }
}
