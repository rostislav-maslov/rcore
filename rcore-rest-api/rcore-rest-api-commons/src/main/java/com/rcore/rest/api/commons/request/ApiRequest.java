package com.rcore.rest.api.commons.request;

import com.rcore.domain.commons.usecase.UseCase;

public interface ApiRequest<I extends UseCase.InputValues> {

    I toInputValues();

}
