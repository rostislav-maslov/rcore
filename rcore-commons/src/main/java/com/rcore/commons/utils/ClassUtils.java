package com.rcore.commons.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ClassUtils {

    public static Optional<Method> findSetMethod(Object object, String fieldName) {
        String field = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return Arrays.asList(object.getClass().getDeclaredMethods())
                .stream()
                .filter(method -> method.getName().startsWith("set" + field))
                .findFirst();
    }

}
