package com.rcore.domain.user.usecase.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateUserFields {
    private String firstName;
    private String secondName;
    private String lastName;
    private Long phoneNumber;
}
