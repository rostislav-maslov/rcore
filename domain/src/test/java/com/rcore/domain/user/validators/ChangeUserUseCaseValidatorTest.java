package com.rcore.domain.user.validators;

import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForUpdateException;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;

public class ChangeUserUseCaseValidatorTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
    private ChangeUserUseCaseValidator changeUserUseCaseValidator = new ChangeUserUseCaseValidator(roleRepository, userRepository);

    @Before
    public void setUp() throws Exception {
        initDefaultMocks();
    }

    private void initDefaultMocks() {
        Mockito.when(roleRepository.findById(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString() == ChangeUserUseCaseValidatorTestInfrastructure.adminRole.getId())
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.adminRole);
                    else
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.courierRole);
                });

        Mockito.when(roleRepository.findByName(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(ChangeUserUseCaseValidatorTestInfrastructure.adminRole.getName()))
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.adminRole);
                    else
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.courierRole);
                });


        Mockito.when(userRepository.findByPhoneNumber(anyLong()))
                .then(a -> {
                    if (Long.parseLong(a.getArgument(0).toString()) == ChangeUserUseCaseValidatorTestInfrastructure.courierUser.getPhoneNumber())
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.courierUser);
                    else return Optional.empty();
                });

        Mockito.when(userRepository.findByEmail(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(ChangeUserUseCaseValidatorTestInfrastructure.adminUser.getEmail()))
                        return Optional.of(ChangeUserUseCaseValidatorTestInfrastructure.adminUser);
                    else return Optional.empty();
                });
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого firstName
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testValidateForNewUserWithInvalidFirstNameException() {
        CreateUserCommand createUserCommand = CreateUserCommand.builder().build();
        Assert.assertThrows(InvalidFirstNameException.class, () -> changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand));
        return;
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого lastName
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testValidateForNewUserWithInvalidLastNameException() {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис")
                .build();
        Assert.assertThrows(InvalidLastNameException.class, () -> changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand));
        return;
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого roles
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testValidateForNewUserWithInvalidRoleException() {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис")
                .lastName("Конфетович")
                .build();
        Assert.assertThrows(RoleIsRequiredException.class, () -> changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand));
        return;
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого phoneNumber.
     * Передаем роль курьера, требующую передачу номера телефона
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testValidateForNewUserWithInvalidPhoneException() {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис")
                .lastName("Конфетович")
                .roles(Arrays.asList(CreateUserCommand.Role.builder()
                        .id(ChangeUserUseCaseValidatorTestInfrastructure.courierRole.getId())
                        .build()))
                .build();
        Assert.assertThrows(PhoneIsRequiredException.class, () -> changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand));
        return;
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого email.
     * Передаем роль администратора, требующую передачу email
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testValidateForNewUserWithInvalidEmailException() {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис")
                .lastName("Конфетович")
                .roles(Arrays.asList(CreateUserCommand.Role.builder()
                        .id(ChangeUserUseCaseValidatorTestInfrastructure.adminRole.getId())
                        .build()))
                .build();
        Assert.assertThrows(InvalidEmailException.class, () -> changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand));
        return;
    }


    /**
     * Валидация при создании нового пользователя с передачей пустого email.
     * Передаем роль администратора, требующую передачу email
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testSuccessfulValidateForNewAdminUser() throws PhoneIsRequiredException, UserWithEmailAlreadyExistException, InvalidEmailException, UserAlreadyExistException, InvalidLastNameException, InvalidRoleException, UserWithPhoneAlreadyExistException, InvalidAccountStatusException, InvalidFirstNameException, RoleIsRequiredException, InvalidPhoneFormatForUpdateException {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис")
                .lastName("Конфетович")
                .roles(Arrays.asList(CreateUserCommand.Role.builder()
                        .id(ChangeUserUseCaseValidatorTestInfrastructure.adminRole.getId())
                        .build()))
                .email("email@email.ru")
                .status(UserStatus.ACTIVE)
                .profileImageId(UUID.randomUUID().toString())
                .build();
        changeUserUseCaseValidator.validate(new UserEntity(), createUserCommand);
        return;
    }

    /**
     * Валидация при создании нового пользователя с передачей пустого email.
     * Передаем роль администратора, требующую передачу email
     * Ожидаем соответствующую ошибку
     */
    @Test
    public void testSuccessfulValidateForCreatedAdminUser() throws PhoneIsRequiredException, UserWithEmailAlreadyExistException, InvalidEmailException, UserAlreadyExistException, InvalidLastNameException, InvalidRoleException, UserWithPhoneAlreadyExistException, InvalidAccountStatusException, InvalidFirstNameException, RoleIsRequiredException, InvalidPhoneFormatForUpdateException {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .firstName("Борис 1")
                .lastName("Конфетович 1")
                .profileImageId(UUID.randomUUID().toString())
                .build();
        changeUserUseCaseValidator.validate(ChangeUserUseCaseValidatorTestInfrastructure.adminUser, createUserCommand);
        return;
    }
}