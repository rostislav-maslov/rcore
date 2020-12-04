package com.rcore.rest.api.presenter.config;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.role.port.filters.RoleFilters;
import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.domain.commons.exception.InvalidIdException;
import com.rcore.domain.commons.port.SearchResult;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class UseCasesConfig {

    @Bean
    public RoleRepository roleRepository() {
        return new RoleRepository() {
            @Override
            public Optional<RoleEntity> findByName(String name) {
                return Optional.empty();
            }

            @Override
            public RoleEntity save(RoleEntity entity) {
                return entity;
            }

            @Override
            public Boolean delete(String s) {
                return null;
            }

            @Override
            public Optional<RoleEntity> findById(String s) {
                return Optional.empty();
            }

            @Override
            public SearchResult<RoleEntity> find(RoleFilters filters) {
                return null;
            }

            @Override
            public Long count() {
                return null;
            }
        };
    }

    @Bean
    public RoleIdGenerator roleIdGenerator() {
        return new RoleIdGenerator() {
            @Override
            public String generate() {
                return UUID.randomUUID().toString();
            }

            @Override
            public Object parse(String id) throws InvalidIdException {
                return null;
            }
        };
    }

    @Bean
    public CreateRoleUseCase createRoleUseCase(RoleRepository roleRepository, RoleIdGenerator roleIdGenerator) {
        return new CreateRoleUseCase(roleRepository, roleIdGenerator);
    }

    @Bean
    public UseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutorImpl();
    }
}
