package com.rcore.commons.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ExampleDataMapper<Input, Output> extends DataMapper<Input, Output> {

    Input inverseMap(Output output);

    default List<Input> inverseMapAll(List<Output> list) {
        return list.stream().map(this::inverseMap).collect(Collectors.toList());
    }

}
