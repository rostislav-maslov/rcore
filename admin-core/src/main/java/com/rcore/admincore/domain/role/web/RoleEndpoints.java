package com.rcore.admincore.domain.role.web;

import com.rcore.adapter.domain.role.RoleAdapter;
import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.admincore.domain.role.application.RoleWebMapper;
import com.rcore.admincore.domain.role.web.api.RoleWeb;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.restapi.web.api.request.SearchApiRequest;
import com.rcore.restapi.web.api.response.SearchApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Role API", description = "API ролей")
@RequiredArgsConstructor
@RestController("roleAdminEndpoints")
public class RoleEndpoints {

    private final RoleAdapter roleAdapter;
    private final RoleWebMapper roleWebMapper;

    @ApiOperation("Получение списка ролей системы")
    @GetMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchApiResponse<RoleWeb>> all(@ModelAttribute SearchApiRequest request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        SearchResult<RoleDTO> result = roleAdapter.view.find(request.toSearchRequest());
        return SuccessApiResponse.of(SearchApiResponse
                .withItemsAndCount(
                       roleWebMapper.mapAll(result.getItems()),
                       result.getCount()
                ));
    }
}
