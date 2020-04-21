package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleViewAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.util.Optional;

public class RoleViewUseCase extends RoleAdminBaseUseCase {

    public RoleViewUseCase(UserEntity actor, RoleRepository roleRepository) throws AuthorizationException {
        super(actor, roleRepository, new AdminRoleViewAccess());
    }

    public Optional<RoleEntity> findById(String id) {
        return roleRepository.findById(id);
    }

    public Optional<RoleEntity> search(String id) {
        return roleRepository.findById(id);
    }

    public SearchResult<RoleEntity> find(Long size, Long skip) {
        return roleRepository.find(size, skip);
    }

}