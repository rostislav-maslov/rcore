package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingleInput;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncodeAccessTokenUseCase extends UseCase<SingleInput<String>, SingleOutput<String>> {

    private final FindAccessTokenByIdUseCase findAccessTokenById;
    private final TokenGenerator<AccessTokenData> accessTokenDataTokenGenerator;

    @Override
    public SingleOutput<String> execute(SingleInput<String> input) {
        return SingleOutput.of(findAccessTokenById.execute(IdInputValues.of(input.getValue()))
                .getEntity()
                .map(a -> accessTokenDataTokenGenerator.generate(a.toAccessTokenData()))
                .orElseThrow(() -> new AccessTokenNotFoundException(input.getValue())));
    }
}
