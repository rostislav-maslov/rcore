package com.rcore.domain.auth.confirmationCode.config;

import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.usecases.CreateConfirmationCodeUseCase;
import com.rcore.domain.auth.confirmationCode.usecases.FindConfirmationCodeByIdUseCase;
import com.rcore.domain.auth.confirmationCode.usecases.FindConfirmationCodesWithFiltersUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfirmationCodeConfig {

    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final ConfirmationCodeIdGenerator confirmationCodeIdGenerator;
    private final ConfirmationCodeGenerator confirmationCodeGenerator;

    public CreateConfirmationCodeUseCase createConfirmationCodeUseCase() {
        return new CreateConfirmationCodeUseCase(confirmationCodeRepository, confirmationCodeIdGenerator, confirmationCodeGenerator);
    }

    public FindConfirmationCodeByIdUseCase findConfirmationCodeByIdUseCase() {
        return new FindConfirmationCodeByIdUseCase(confirmationCodeRepository);
    }

    public FindConfirmationCodesWithFiltersUseCase findConfirmationCodesWithFiltersUseCase() {
        return new FindConfirmationCodesWithFiltersUseCase(confirmationCodeRepository);
    }

}
