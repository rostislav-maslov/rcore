package com.rcore.commons.utils;

public class DomainUtils {

    public static String getDomain(Class clazz) {
        String[] clazzAsArray = clazz.getSimpleName().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        String domain = "";
        for (int i = 0; i < clazzAsArray.length - 1; i++)
            if (i == 0)
                domain += clazzAsArray[i].toUpperCase();
            else
                domain += "_" + clazzAsArray[i].toUpperCase();

        return domain;
    }

}
