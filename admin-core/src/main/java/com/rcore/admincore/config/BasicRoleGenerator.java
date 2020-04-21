package com.rcore.admincore.config;

import com.rcore.domain.base.roles.BaseRoles;
import com.rcore.domain.base.roles.SuperUserRole;
import com.rcore.domain.role.port.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BasicRoleGenerator implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        roleRepository.findByName(BaseRoles.SUPER_USER)
                .orElseGet(() -> roleRepository.save(new SuperUserRole()));

    }
}
