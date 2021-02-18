package com.rcore.domain.commons.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Use Case Actor
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class UseCaseActor {

    /**
     * Actor ID
     */
    private String id;
}
