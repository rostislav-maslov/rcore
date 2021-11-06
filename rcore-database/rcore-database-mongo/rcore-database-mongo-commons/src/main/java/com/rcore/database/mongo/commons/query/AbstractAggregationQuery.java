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
public abstract class AbstractAggregationQuery<Input, Output> {
    private Class<Input> inputType;
    private Class<Output> outputType;

    private SearchFilters searchFilters;

    /**
     * Операции аггрегации
     *
     * @return
     */
    public abstract List<AggregationOperation> getOperations();

    /**
     * Аггрегация с сортировкой, лимитом и оффсетом
     *
     * @return
     */
    public Aggregation getAggregation() {
        List<AggregationOperation> operations = new ArrayList<>(getOperations());
        operations.add(Aggregation.skip(searchFilters.getOffset()));
        operations.add(Aggregation.limit(searchFilters.getLimit()));
        operations.add(Aggregation.sort(Sort.by(Sort.Direction.fromString(searchFilters.getSortDirection().name()), searchFilters.getSortName())));
        return Aggregation.newAggregation(operations);
    }

    /**
     * Аггрегация для подсчета общего кол-ва результатов
     *
     * @return
     */
    public Aggregation getCountAggregation() {
        List<AggregationOperation> operations = new ArrayList<>(getOperations());
        operations.add(new CountOperation.CountOperationBuilder().as("count"));
        return Aggregation.newAggregation(operations);
    }
}
