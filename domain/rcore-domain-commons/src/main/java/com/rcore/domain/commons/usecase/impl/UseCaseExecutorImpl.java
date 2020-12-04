package com.rcore.domain.commons.usecase.impl;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class UseCaseExecutorImpl implements UseCaseExecutor {

    @Override
    public <ResultModel, Input extends UseCase.InputValues, Output extends UseCase.OutputValues> CompletableFuture<ResultModel> execute(UseCase<Input, Output> useCase, Input input, Function<Output, ResultModel> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}
