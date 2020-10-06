package com.rcore.domain.picture.usecase.secured.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeUsedPictureCommand {
    private String id;
    private Boolean isUsed;
}
