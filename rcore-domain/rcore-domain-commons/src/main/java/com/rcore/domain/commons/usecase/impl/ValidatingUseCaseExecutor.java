package com.rcore.domain.commons.usecase.impl;

import com.rcore.domain.commons.exception.ValidatingDomainException;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.function.Function;

public class ValidatingUseCaseExecutor implements UseCaseExecutor {

    private final Validator validator;

    public ValidatingUseCaseExecutor() {
        var factory = Validation.buildDefaultValidatorFactory();
         validator = factory.getValidator();
         factory.close();
    }

    public ValidatingUseCaseExecutor(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <ResultModel, Input extends UseCase.InputValues, Output extends UseCase.OutputValues> ResultModel execute(UseCase<Input, Output> useCase, Input input, Function<Output, ResultModel> outputMapper) {
        validate(input);
        return outputMapper.apply(useCase.execute(input));
    }

    @Override
    public <Input extends UseCase.InputValues, Output extends UseCase.OutputValues> Output execute(UseCase<Input, Output> useCase, Input input) {
        validate(input);
        return useCase.execute(input);
    }

    private <I> void validate(I i) {
        var s = validator.validate(i);
        if (!s.isEmpty())
            throw new ValidatingDomainException(s);
    }
}
