package com.rcore.domain.role.usecase.all;

import com.rcore.domain.role.port.RoleRepository;

class RoleBaseUseCase{

    protected final RoleRepository roleRepository;

    public RoleBaseUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
