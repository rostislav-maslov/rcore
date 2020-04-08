package com.rcore.adapter.domen.user;

import com.rcore.adapter.domen.user.mapper.UserMapper;
import com.rcore.adapter.domen.user.model.UserDTO;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.usecase.admin.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdminController {

    private UserMapper mapper = new UserMapper();

    private final ActivateUseCase activateUseCase;
    private final BlockUseCase blockUseCase;
    private final ChangeRoleUseCase changeRoleUseCase;
    private final CreateUseCase createUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final InitAdminUseCase initAdminUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ViewUserUseCase viewUserUseCase;

    public UserDTO activateUser(UserDTO userDTO) {
        return mapper.map(
                activateUseCase.activate(mapper.inverseMap(userDTO))
        );
    }

    public UserDTO blockUser(UserDTO userDTO) {
        return mapper.map(
                blockUseCase.block(mapper.inverseMap(userDTO))
        );
    }

    public UserDTO createUserByEmail(String email, String password) throws UserAlreadyExistException {
        return mapper.map(createUseCase.createByEmail(email, password));
    }

    public Boolean deleteUser(UserDTO userDTO) throws UserAlreadyExistException {
        return deleteUserUseCase.delete(mapper.inverseMap(userDTO));
    }

    public Boolean initAdminUser(String email, String password) {
        return initAdminUseCase.init(email, password);
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException {
        return mapper.map(
                updateUserUseCase.update(mapper.inverseMap(userDTO))
        );
    }

    public Optional<UserDTO> findById(String id){
        return viewUserUseCase.findById(id).map(mapper::map);
    }

    public SearchResult<UserDTO> find(Long size, Long skip) {
        SearchResult<UserEntity> result = viewUserUseCase.find(size, skip);
        return SearchResult.withItemsAndCount(
                mapper.mapAll(result.getItems()),
                result.getCount()
        );
    }
}
