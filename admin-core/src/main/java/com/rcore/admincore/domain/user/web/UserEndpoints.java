package com.rcore.admincore.domain.user.web;

import com.rcore.adapter.domain.user.UserAdapter;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.admincore.domain.user.application.UserWebMapper;
import com.rcore.admincore.domain.user.web.api.CreateUserDTO;
import com.rcore.admincore.domain.user.web.api.SearchWithFilters;
import com.rcore.admincore.domain.user.web.api.UserWeb;
import com.rcore.commons.utils.DomainUtils;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.base.roles.BaseRoles;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.restapi.exceptions.NotFoundApiException;
import com.rcore.restapi.web.api.request.SearchApiRequest;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SearchApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Secured(BaseRoles.SUPER_USER)
@Api(tags = "Users API", description = "API пользователей")
@RequiredArgsConstructor
@RestController
public class UserEndpoints {

    private final String DOMAIN = DomainUtils.getDomain(UserEndpoints.class);

    private final UserAdapter userAdapter;
    private final UserWebMapper userWebMapper;

    @ApiOperation("Метод получения списка пользователей")
    @GetMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchApiResponse<UserWeb>> all(@ModelAttribute SearchWithFilters request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        SearchResult<UserDTO> result = userAdapter.view
                .findWithFilters(request.toSearchRequest(), request.getRoleId());

        return SuccessApiResponse.of(
                SearchApiResponse.withItemsAndCount(
                        userWebMapper.mapAll(result.getItems()),
                        result.getCount()
                ));
    }

    @ApiOperation(value = "Получение информации о пользователе по ID")
    @GetMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserWeb> get(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, TokenExpiredException {
        return SuccessApiResponse.of(userAdapter.view
                .findById(id)
                .map(userWebMapper::map)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", DOMAIN, "NOT_FOUND")));
    }

    @ApiOperation("Создание пользователя по Email")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserWeb> create(@RequestBody CreateUserDTO request) throws AuthenticationException, AuthorizationException, UserAlreadyExistException, TokenExpiredException {
        UserDTO user = userAdapter.secure
                .createUserByEmail(request.getEmail(), request.getPassword(), request.getRoleIds());

        return SuccessApiResponse.of(userWebMapper.map(user));
    }

    @ApiOperation("Редактирование пользователяв")
    @PatchMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserWeb> edit(@RequestBody UserDTO request) throws AuthenticationException, UserNotFoundException, AuthorizationException, UserAlreadyExistException, TokenExpiredException {
        UserDTO user = userAdapter.secure
                .updateUser(request);

        return SuccessApiResponse.of(userWebMapper.map(user));
    }

    @ApiOperation("Удаление пользователя")
    @DeleteMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse delete(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, UserAlreadyExistException, TokenExpiredException {
        UserDTO user = userAdapter.view
                .findById(id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", DOMAIN, "NOT_FOUND"));

        userAdapter.getSecure()
                .deleteUser(user);

        return OkApiResponse.of();
    }

    @ApiOperation("Блокировка пользователя")
    @PatchMapping(value = Routes.BLOCK, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse block(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, TokenExpiredException {
        UserDTO user = userAdapter.view
                .findById(id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", DOMAIN, "NOT_FOUND"));

        userAdapter.secure
                .blockUser(user);

        return OkApiResponse.of();
    }

    @ApiOperation("Активация пользователя")
    @PatchMapping(value = Routes.ACTIVATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse activate(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, TokenExpiredException {
        UserDTO user = userAdapter.view
                .findById(id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", DOMAIN, "NOT_FOUND"));

        userAdapter.secure
                .activateUser(user);

        return OkApiResponse.of();
    }




}
