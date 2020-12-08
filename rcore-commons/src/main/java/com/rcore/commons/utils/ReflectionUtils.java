package com.rcore.commons.utils;

import org.reflections.Reflections;

import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static List<String> getChildNamesFromPackage(String packageName, Class<?> clazz) {
        return new Reflections(packageName)
                .getSubTypesOf(clazz)
                .stream()
                .map(a -> a.getClass().getCanonicalName())
                .collect(Collectors.toList());
    }
}
