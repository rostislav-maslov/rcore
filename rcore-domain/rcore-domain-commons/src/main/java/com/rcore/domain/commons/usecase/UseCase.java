package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.exception.ValidatingDomainException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * UseCase - это определенный тип. Он содержит в себе информацию о модели входящих и исходящих данных. Он имеет один метод - выполнить useCase
 * @param <Input>
 * @param <Output>
 */
public abstract class UseCase<Input extends UseCase.InputValues, Output extends UseCase.OutputValues> {

    protected static Validator validator;

    public UseCase() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    /**
     * Выполнение useCase
     * @param input - входящие данные
     * @return - исходящие данные
     */
    public abstract Output execute(Input input);

    /**
     * Описание входящих данных
     */
    public  interface InputValues {

        default void validate() {
            var s = validator.validate(this);
            if (!s.isEmpty())
                throw new ValidatingDomainException(s);
        }

    }

    /**
     * Описание исходящих данных
     */
    public interface  OutputValues {

    }

}

