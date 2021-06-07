package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingleInput;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecodeRefreshTokenUseCase extends UseCase<SingleInput<String>, SingleOutput<RefreshTokenEntity>> {

    private final FindRefreshTokenByIdUseCase findRefreshTokenById;
    private final TokenParser<RefreshTokenData> refreshTokenDataTokenParser;

    @Override
    public SingleOutput<RefreshTokenEntity> execute(SingleInput<String> input) {
        var tokenData = refreshTokenDataTokenParser.parseWithValidating(input.getValue());
        return SingleOutput.of(findRefreshTokenById.execute(IdInputValues.of(tokenData.getId()))
                .getEntity()
                .orElseThrow(() -> new RefreshTokenNotFoundException(tokenData.getId())));
    }
}
