package com.rcore.domain.user.validators;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.port.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;

public class ChangeUserUseCaseValidatorTestInfrastructure {

    public static final RoleEntity adminRole = RoleEntity.builder()
            .id(UUID.randomUUID().toString())
            .locale("Администратор")
            .name("ADMIN")
            .createdAt(LocalDateTime.now())
            .availableAuthTypes(Arrays.asList(RoleEntity.AuthType.EMAIL))
            .build();

    public static final RoleEntity courierRole = RoleEntity.builder()
            .id(UUID.randomUUID().toString())
            .locale("Курьер")
            .name("COURIER")
            .createdAt(LocalDateTime.now())
            .availableAuthTypes(Arrays.asList(RoleEntity.AuthType.SMS))
            .build();

    public static final UserEntity adminUser = UserEntity.builder()
            .countryId(UUID.randomUUID().toString())
            .email("admin@mail.ru")
            .createdAt(LocalDateTime.now())
            .fails(0)
            .firstName("Боб")
            .lastName("Кренделевъ")
            .secondName("Петрович")
            .profileImageId(UUID.randomUUID().toString())
            .roles(Set.of(adminRole))
            .status(UserStatus.ACTIVE)
            .build();

    public static final UserEntity courierUser = UserEntity.builder()
            .countryId(UUID.randomUUID().toString())
            .phoneNumber(79023334455l)
            .createdAt(LocalDateTime.now())
            .fails(0)
            .firstName("Боб")
            .lastName("Кренделевъ")
            .secondName("Петрович")
            .profileImageId(UUID.randomUUID().toString())
            .roles(Set.of(courierRole))
            .status(UserStatus.ACTIVE)
            .build();
}