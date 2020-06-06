package com.rcore.domain.role.config;

import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.usecase.admin.RoleCreateUseCase;
import com.rcore.domain.role.usecase.admin.RoleDeleteUseCase;
import com.rcore.domain.role.usecase.admin.RoleUpdateUseCase;
import com.rcore.domain.role.usecase.admin.RoleViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import lombok.RequiredArgsConstructor;

public class RoleConfig {

    @RequiredArgsConstructor
    public static class Admin {
        protected final RoleRepository roleRepository;
        protected final RoleIdGenerator idGenerator;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public RoleCreateUseCase createUseCase() throws AuthorizationException {
            return new RoleCreateUseCase(this.roleRepository, idGenerator, authorizationByTokenUseCase);
        }

        public RoleDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new RoleDeleteUseCase(this.roleRepository, authorizationByTokenUseCase);
        }

        public RoleUpdateUseCase updateUseCase() throws AuthorizationException {
            return new RoleUpdateUseCase(this.roleRepository, authorizationByTokenUseCase);
        }

        public RoleViewUseCase viewUseCase() throws AuthorizationException {
            return new RoleViewUseCase(this.roleRepository, authorizationByTokenUseCase);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final RoleRepository roleRepository;
    private final RoleIdGenerator idGenerator;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public RoleConfig(
            RoleRepository roleRepository,
            RoleIdGenerator idGenerator,
            AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.roleRepository = roleRepository;
        this.idGenerator = idGenerator;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.roleRepository, this.idGenerator, authorizationByTokenUseCase);
        this.all = new All();
    }

}
