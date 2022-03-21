package com.rcore.database.mongo.commons.query;

import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.CountOperation;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class AggregationQuery<Input, Output> {
    private Class<Input> inputType;
    private Class<Output> outputType;

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
