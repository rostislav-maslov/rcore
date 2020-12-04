package com.rcore.domain.auth.confirmationCode.usecase.all;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.exceptions.ExistNotConfirmedCodeException;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import com.rcore.domain.auth.confirmationCode.usecase.all.commands.CreateConfirmationCodeCommand;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Создание подтверждающего кода. После создания код будет разсылаться получателю
 */
@RequiredArgsConstructor
public class CreateConfirmationCodeUseCase {

    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final ConfirmationCodeIdGenerator confirmationCodeIdGenerator;
    private final ConfirmationCodeGenerator confirmationCodeGenerator;

    public ConfirmationCodeEntity create(CreateConfirmationCodeCommand createConfirmationCodeCommand) throws ExistNotConfirmedCodeException {

        //Если уже есть неподтвержденный код - возвращаем соответствующую ошибку
        if (confirmationCodeRepository.existNotConfirmedCode(createConfirmationCodeCommand.getAuthorizationId()))
            throw new ExistNotConfirmedCodeException();

        ConfirmationCodeEntity confirmationCodeEntity = new ConfirmationCodeEntity();
        confirmationCodeEntity.setId(confirmationCodeIdGenerator.generate());
        confirmationCodeEntity.setAuthorizationId(createConfirmationCodeCommand.getAuthorizationId());
        confirmationCodeEntity.setSendingStatus(ConfirmationCodeEntity.SendingStatus.WAITING);
        //Генерируем код
        confirmationCodeEntity.setCode(confirmationCodeGenerator.generate());
        confirmationCodeEntity.setRecipient(createConfirmationCodeCommand.getRecipient());

        //Устанавливаем дату истечения
        confirmationCodeEntity.installExpiredAt(createConfirmationCodeCommand.getTtl());
        confirmationCodeEntity = confirmationCodeRepository.save(confirmationCodeEntity);
        return confirmationCodeEntity;
    }

}
