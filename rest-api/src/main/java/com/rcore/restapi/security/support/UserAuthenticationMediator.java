package com.rcore.restapi.security.support;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthenticationMediator implements AuthenticationMediator<UserDTO> {

    private final UserRepository userRepository;
    private UserMapper userMapper = new UserMapper(new RoleMapper());

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserDTO getUser() {
        return userRepository.findById(((UserDTO) getAuthentication().getDetails()).getId())
                .map(userMapper::map)
                .get();
    }

    @Override
    public AccessTokenDTO getAccessToken() {
        return (AccessTokenDTO) getAuthentication().getDetails();
    }
}
