package com.rcore.commons.utils;

import com.rcore.commons.jsonschema.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonSchemaUtils {

    /**
     * Проверка соответсвия json схеме
     * @param schema
     * @param json
     * @return массив недостоющих полей
     */
    public static List<String> validate(Map<String, Object> schema, Map<String, Object> json) {
        if (schema.get(Property.required.name()) !=null && schema.get(Property.required.name()) instanceof List)
            return checkRequiredFields((List<String>) schema.get(Property.required.name()), json);
        //TODO добавить проверку корректости переданных типов
        return new ArrayList<>();
    }

    /**
     * Проверка наличия обязательных значений.
     * @param requiredFields
     * @param json
     * @return массив недостоющих полей
     */
    private static List<String> checkRequiredFields(List<String> requiredFields, Map<String, Object> json) {
        List<String> notFoundedFields = new ArrayList<>();
        for (String requiredField: requiredFields) {
            Object value = json.get(requiredField);
            if (value == null)
                notFoundedFields.add(requiredField);
        }
        return notFoundedFields;
    }

}
