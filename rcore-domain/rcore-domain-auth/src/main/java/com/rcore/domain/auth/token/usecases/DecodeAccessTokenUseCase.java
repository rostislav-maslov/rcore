package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingleInput;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecodeAccessTokenUseCase extends UseCase<SingleInput<String>, SingleOutput<AccessTokenEntity>> {

    private final FindAccessTokenByIdUseCase findAccessTokenById;
    private final TokenParser<AccessTokenData> accessTokenDataTokenParser;

    @Override
    public SingleOutput<AccessTokenEntity> execute(SingleInput<String> input) {
        var tokenData = accessTokenDataTokenParser.parseWithValidating(input.getValue());
        return SingleOutput.of(findAccessTokenById.execute(IdInputValues.of(tokenData.getId()))
                .getEntity()
                .orElseThrow(() -> new AccessTokenNotFoundException(tokenData.getId())));
    }
}
