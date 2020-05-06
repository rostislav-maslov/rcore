package com.rcore.admincore.config;

import com.rcore.domain.base.roles.BaseRoles;
import com.rcore.domain.base.roles.impl.SuperUserRoleCreator;
import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BasicRoleGenerator implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;
    private final SuperUserRoleCreator superUserRoleCreator;

    public BasicRoleGenerator(RoleRepository roleRepository, RoleIdGenerator roleIdGenerator) {
        this.roleRepository = roleRepository;
        this.roleIdGenerator = roleIdGenerator;
        this.superUserRoleCreator = new SuperUserRoleCreator(this.roleRepository, this.roleIdGenerator);
    }

    @Override
    public void run(String... args) throws Exception {

        roleRepository.findByName(BaseRoles.SUPER_USER)
                .orElseGet(superUserRoleCreator::create);

    }
}
