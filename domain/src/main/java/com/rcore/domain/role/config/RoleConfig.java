package com.rcore.domain.role.config;

import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.usecase.admin.RoleCreateUseCase;
import com.rcore.domain.role.usecase.admin.RoleDeleteUseCase;
import com.rcore.domain.role.usecase.admin.RoleUpdateUseCase;
import com.rcore.domain.role.usecase.admin.RoleViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class RoleConfig {

    public static class Admin {
        protected final RoleRepository roleRepository;
        protected final RoleIdGenerator idGenerator;

        public Admin(RoleRepository roleRepository, RoleIdGenerator idGenerator) {
            this.roleRepository = roleRepository;
            this.idGenerator = idGenerator;
        }

        public RoleCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new RoleCreateUseCase(actor, this.roleRepository, idGenerator);
        }

        public RoleDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new RoleDeleteUseCase(actor, this.roleRepository);
        }

        public RoleUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new RoleUpdateUseCase(actor, this.roleRepository);
        }

        public RoleViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new RoleViewUseCase(actor, this.roleRepository);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final RoleRepository roleRepository;
    private final RoleIdGenerator idGenerator;

    public final Admin admin;
    public final All all;

    public RoleConfig(
            RoleRepository roleRepository,
            RoleIdGenerator idGenerator
    ) {
        this.roleRepository = roleRepository;
        this.idGenerator = idGenerator;

        this.admin = new Admin(this.roleRepository, this.idGenerator);
        this.all = new All();
    }

}
