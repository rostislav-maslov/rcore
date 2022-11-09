package com.rcore.database.mongo.commons.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.ResolvableType;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class AggregationQuery<Input, Output> {
    private final Class<Input> inputType;
    private final Class<Output> outputType;

    @SuppressWarnings("unchecked")
    public AggregationQuery() {
        ResolvableType resolvableType = ResolvableType.forClass(AggregationQuery.class, getClass());

        this.inputType = (Class<Input>) resolvableType.getGeneric(0).getType();
        this.outputType = (Class<Output>) resolvableType.getGeneric(1).getType();
    }

    /**
     * Операции аггрегации
     *
     * @return
     */
    public abstract List<AggregationOperation> getOperations();

    /**
     * @return
     */
    public Aggregation getAggregation() {
        List<AggregationOperation> operations = new ArrayList<>(getOperations());
        return Aggregation.newAggregation(operations);
    }
}
