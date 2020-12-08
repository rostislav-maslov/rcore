package com.rcore.database.mongo.commons.utils;

public class CollectionNameUtils {

    //Заменяет первый символ в названии класса на lowercase
    public static String getCollectionName(Class clazz){
        return clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
    }

}
