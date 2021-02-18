package com.rcore.domain.commons.usecase.impl;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;

import java.util.function.Function;

public class UseCaseExecutorImpl implements UseCaseExecutor {

    @Override
    public <ResultModel, Input extends UseCase.InputValues, Output extends UseCase.OutputValues> ResultModel execute(UseCase<Input, Output> useCase, Input input, Function<Output, ResultModel> outputMapper) {
        return outputMapper.apply(useCase.execute(input));
    }

    @Override
    public <Input extends UseCase.InputValues, Output extends UseCase.OutputValues> void execute(UseCase<Input, Output> useCase, Input input) {
        useCase.execute(input);
    }
}