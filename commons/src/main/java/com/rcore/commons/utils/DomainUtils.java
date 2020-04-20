package com.rcore.commons.utils;

public class DomainUtils {

    public static String getDomain(Class clazz) {
        String[] clazzAsArray = clazz.getSimpleName().split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        String domain = "";
        for (int i = 0; i < clazzAsArray.length - 2; i++)
            if (i == 0)
                domain += clazzAsArray[i];
            else
                domain += "_" + clazzAsArray[i];

        return domain;
    }

}
