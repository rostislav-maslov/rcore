package com.rcore.restapi.security.entities.token.port;

import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenRepository;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.restapi.security.support.UserAuthenticationMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccessTokenStorageImpl implements AccessTokenStorage {

    private final UserAuthenticationMediator userAuthenticationMediator;
    private AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
    private final AccessTokenRepository accessTokenRepository;

    @Override
    public Optional<AccessTokenEntity> current() {
        return Optional.ofNullable(accessTokenMapper.inverseMap(userAuthenticationMediator.getAccessToken()));
    }

    @Override
    public void put(AccessTokenEntity accessTokenEntity) {
        accessTokenRepository.save(accessTokenEntity);
    }

    @Override
    public Optional<AccessTokenEntity> findById(String id) {
       return  accessTokenRepository.findById(id);
    }
}
