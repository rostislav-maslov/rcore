package com.rcore.database.mongo.auth.credential.model;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document
@Data
public class CredentialDoc extends BaseDocument {
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<Role> roles;
    private CredentialEntity.Status status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Role {
        @DBRef
        private RoleEntity role;
        private boolean isBlocked;
    }

}
