package com.rcore.domain.auth.confirmationCode.port.impl;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeBroadcaster;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.port.EmailConfirmationCodeSender;
import com.rcore.domain.auth.confirmationCode.port.SmsConfirmationCodeSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConfirmationCodeBroadcasterImpl implements ConfirmationCodeBroadcaster {

    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final SmsConfirmationCodeSender smsConfirmationCodeSender;
    private final EmailConfirmationCodeSender emailConfirmationCodeSender;

    @Override
    public void broadcast(Long limit) {
        confirmationCodeRepository.findNotSent(limit)
                .forEach(confirmationCodeEntity -> {
                    try {
                        confirmationCodeEntity.setInProgress();
                        confirmationCodeEntity = confirmationCodeRepository.save(confirmationCodeEntity);

                        if (confirmationCodeEntity.getRecipient().getSendingType().equals(ConfirmationCodeEntity.Recipient.SendingType.SMS))
                            smsConfirmationCodeSender.send(confirmationCodeEntity);
                        else
                            emailConfirmationCodeSender.send(confirmationCodeEntity);

                        confirmationCodeEntity.setSent();
                        confirmationCodeRepository.save(confirmationCodeEntity);
                    } catch (Exception e) {
                        log.error("Broadcast confirmed code error: ID - {id} TYPE - {type}", confirmationCodeEntity.getId(), confirmationCodeEntity.getRecipient().getSendingType());
                        confirmationCodeEntity.setError();
                        confirmationCodeRepository.save(confirmationCodeEntity);
                    }
                });
    }
}
