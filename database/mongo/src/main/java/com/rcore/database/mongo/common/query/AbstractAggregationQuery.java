package com.rcore.database.mongo.common.query;

import com.rcore.database.mongo.base.CountAggregationOutputDTO;
import com.rcore.domain.base.port.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class AbstractAggregationQuery<Input, Output> {
    private Class<Input> inputType;
    private Class<Output> outputType;

    private SearchRequest searchRequest;

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
        operations.add(Aggregation.skip(searchRequest.getOffset()));
        operations.add(Aggregation.limit(searchRequest.getLimit()));
        operations.add(Aggregation.sort(Sort.by(Sort.Direction.fromString(searchRequest.getSortDirection()), searchRequest.getSortName())));
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
