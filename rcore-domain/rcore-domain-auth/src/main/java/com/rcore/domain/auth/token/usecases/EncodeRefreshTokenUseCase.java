package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingleInput;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncodeRefreshTokenUseCase extends UseCase<SingleInput<String>, SingleOutput<String>> {

    private final FindRefreshTokenByIdUseCase findRefreshTokenById;
    private final TokenGenerator<RefreshTokenData> refreshTokenDataTokenGenerator;

    @Override
    public SingleOutput<String> execute(SingleInput<String> input) {
        return SingleOutput.of(findRefreshTokenById.execute(IdInputValues.of(input.getValue()))
                .getEntity()
                .map(a -> refreshTokenDataTokenGenerator.generate(a.toRefreshTokenData()))
                .orElseThrow(() -> new RefreshTokenNotFoundException(input.getValue())));
    }
}
