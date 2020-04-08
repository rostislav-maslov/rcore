package com.rcore.commons.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ExampleDataMapper<Input, Output> {

    Output map(Input input);

    default List<Output> mapAll(List<Input> list) {
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    Input inverseMap(Output output);

    default List<Input> inverseMapAll(List<Output> list) {
        return list.stream().map(this::inverseMap).collect(Collectors.toList());
    }

}
