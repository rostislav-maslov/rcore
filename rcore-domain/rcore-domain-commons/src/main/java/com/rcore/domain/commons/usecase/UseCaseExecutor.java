package com.rcore.domain.commons.usecase;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Выполнение useCase.
 */
public interface UseCaseExecutor {

    /**
     *
     * @param useCase - use case
     * @param input - входящие данные
     * @param outputMapper - мапер, преобхазующий исходящие данные use case в модель результата
     * @param <ResultModel> - конечные данные. Например, данные, аозвращаемые по RestAPI
     * @param <Input> - входящие данные use case
     * @param <Output> - исходящие данные use case
     * @return
     */
    <ResultModel, Input extends UseCase.InputValues, Output extends UseCase.OutputValues> CompletableFuture<ResultModel> execute(
            UseCase<Input, Output> useCase,
            Input input,
            Function<Output, ResultModel> outputMapper
    );

}