package com.rcore.commons.utils;

import com.rcore.domain.commons.usecase.UseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReflectionUtilsTest {

    @Test
    void getChildNamesFromPackage() {
        List<String> usecases = ReflectionUtils.getChildNamesFromPackage("com.rcore", UseCase.class);
        return;
    }
}