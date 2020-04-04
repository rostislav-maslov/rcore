package com.rcore.domain.base.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Дефолтный mapper, реализовавыя который можно мапить одну модель в другую
 *
 * @param <InputObject> - входящий объект
 * @param <OutputObject> - исходящий объект
 */
public interface ExampleDataMapper<InputObject, OutputObject> {

    OutputObject map(InputObject inputObject);

    default List<OutputObject> mapAll(List<InputObject> inputObjects){
        return inputObjects.stream().map(this::map).collect(Collectors.toList());
    }

}
