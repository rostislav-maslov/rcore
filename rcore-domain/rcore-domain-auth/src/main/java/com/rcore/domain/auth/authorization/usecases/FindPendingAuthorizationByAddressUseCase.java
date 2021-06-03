package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingleInput;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindPendingAuthorizationByAddressUseCase extends UseCase<SingleInput<String>, SingleOutput<Optional<AuthorizationEntity>>> {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public SingleOutput<Optional<AuthorizationEntity>> execute(SingleInput<String> stringSingleInput) {
        return SingleOutput.of(authorizationRepository.findPendingConfirmationByAddress(stringSingleInput.getValue()));
    }
}
