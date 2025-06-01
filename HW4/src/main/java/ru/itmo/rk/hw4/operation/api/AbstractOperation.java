package ru.itmo.rk.hw4.operation.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.rk.hw4.exception.IncompatibleTypesException;

import java.util.Arrays;
import java.util.Set;

@RequiredArgsConstructor
public abstract class AbstractOperation implements Operation {
    @Getter
    private final String token;
    private final Set<Class<?>> allowedTypes;

    @Override
    public Object apply(Object... input) {
        checkTypes(input);
        return doApply(input);
    }

    private void checkTypes(Object... input) {
        for (Object obj : input) {
            if (!allowedTypes.contains(obj.getClass())) {
                var types = Arrays.stream(input).map(Object::getClass).toArray(Class[]::new);
                throw new IncompatibleTypesException(token, types);
            }
        }
    }

    protected abstract Object doApply(Object... input);
}
