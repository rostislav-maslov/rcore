package com.rcore.rest.api.presenter.resources.role;

import com.rcore.rest.api.presenter.resources.role.api.request.CreateRoleRequest;
import com.rcore.rest.api.presenter.resources.role.api.response.RoleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/roles")
public interface RoleResource {

    @PostMapping
    CompletableFuture<RoleResponse> create(CreateRoleRequest createRoleRequest);

}
