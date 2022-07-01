package com.rcore.domain.aspects;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@RequiredArgsConstructor
@Aspect
public class UseCaseExecutingAspect {

    private final UseCaseExecutor useCaseExecutor;

    @SneakyThrows
    @Around("within(@com.rcore.domain.commons.usecase.annotation.ExecutableUseCase *) && " +
            "execution(public com.rcore.domain.commons.usecase.UseCase.OutputValues+ *.execute(com.rcore.domain.commons.usecase.UseCase.InputValues+))")
    public Object executeUseCase(ProceedingJoinPoint jp) {
        return useCaseExecutor.execute(
                (com.rcore.domain.commons.usecase.UseCase<? super com.rcore.domain.commons.usecase.UseCase.InputValues, ? extends com.rcore.domain.commons.usecase.UseCase.OutputValues>) jp.getTarget(),
                (com.rcore.domain.commons.usecase.UseCase.InputValues) jp.getArgs()[0]
        );
    }

}
