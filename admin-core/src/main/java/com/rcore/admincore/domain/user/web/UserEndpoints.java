package com.rcore.admincore.domain.user.web;

import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.user.UserAdapter;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.admincore.domain.user.web.api.CreateUserDTO;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
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
import org.springframework.web.bind.annotation.*;

@Api(tags = "Users API", description = "API пользователей")
@RequiredArgsConstructor
@RestController
public class UserEndpoints {

    private final UserAdapter userAdapter;
    private final TokenAdapter tokenAdapter;

    @ApiOperation("Метод получения списка пользователей")
    @GetMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchApiResponse<UserDTO>> all(@ModelAttribute SearchApiRequest request) throws AuthenticationException, AuthorizationException {
        SearchResult<UserDTO> result = userAdapter.getAdmin()
                .find(tokenAdapter.currentUser(), request.getLimit(), request.getOffset());

        return SuccessApiResponse.of(
                SearchApiResponse.withItemsAndCount(
                        result.getItems(),
                        result.getCount()
                ));
    }

    @ApiOperation(value = "Получение информации о пользователе по ID")
    @GetMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserDTO> get(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException {
        return SuccessApiResponse.of(userAdapter.getAdmin()
                .findById(tokenAdapter.currentUser(), id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", "USER", "NOT_FOUND")));
    }

    @ApiOperation("Создание пользователя по Email")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserDTO> create(@RequestBody CreateUserDTO request) throws AuthenticationException, AuthorizationException, UserAlreadyExistException {
        return SuccessApiResponse.of(userAdapter.getAdmin()
                .createUserByEmail(tokenAdapter.currentUser(), request.getEmail(), request.getPassword()));
    }

    @ApiOperation("Редактирование пользователяв")
    @PatchMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<UserDTO> edit(@RequestBody UserDTO request) throws AuthenticationException, UserNotFoundException, AuthorizationException, UserAlreadyExistException {
        return SuccessApiResponse.of(userAdapter.getAdmin()
                .updateUser(tokenAdapter.currentUser(), request));
    }

    @ApiOperation("Удаление пользователя")
    @DeleteMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse delete(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, UserAlreadyExistException {
        UserDTO actor = tokenAdapter.currentUser();

        UserDTO user = userAdapter.getAdmin()
                .findById(actor, id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", "USER", "NOT_FOUND"));

        userAdapter.getAdmin()
                .deleteUser(actor, user);

        return OkApiResponse.of();
    }

    @ApiOperation("Блокировка пользователя")
    @PatchMapping(value = Routes.BLOCK, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse block(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException, UserAlreadyExistException {
        UserDTO actor = tokenAdapter.currentUser();

        UserDTO user = userAdapter.getAdmin()
                .findById(actor, id)
                .orElseThrow(() -> new NotFoundApiException("Передан неверный идентификатор пользователя", "USER", "NOT_FOUND"));

        userAdapter.getAdmin()
                .deleteUser(actor, user);

        return OkApiResponse.of();
    }




}
