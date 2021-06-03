package com.rcore.domain.auth.confirmationCode.port;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

import java.util.List;
import java.util.Optional;

public interface ConfirmationCodeRepository extends CRUDRepository<String, ConfirmationCodeEntity, SearchFilters> {
    Optional<ConfirmationCodeEntity> findWaitingConfirmCode(String authorizationId);

    Boolean existNotConfirmedCode(String authorizationId);

    Optional<ConfirmationCodeEntity> findNotConfirmedByAddressAndSendingTypeAndCode(String address, ConfirmationCodeEntity.Recipient.SendingType sendingType, String code);

    List<ConfirmationCodeEntity> findNotSent(Long limit);
}
