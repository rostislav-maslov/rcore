package com.rcore.domain.user.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CRUDRepository<String, UserEntity> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(Long phoneNumber);
    Optional<UserEntity> findByLogin(String login);

    SearchResult<UserEntity> findWithFilters(SearchRequest request, String roleId);
}
