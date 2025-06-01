package ru.itmo.rk.hw4.operation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.rk.hw4.exception.IncompatibleTypesException;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public abstract class Operation {
    private final String token;
    private final Set<Class<?>> allowedTypes;

    public Object apply(Object left, Object right) throws IncompatibleTypesException {
        checkTypes(left, right);
        return doApply(left, right);
    }

    protected void checkTypes(Object left, Object right) throws IncompatibleTypesException {
        if (!allowedTypes.contains(left.getClass()) || !allowedTypes.contains(right.getClass())) {
            throw new IncompatibleTypesException(getToken(), left.getClass(), right.getClass());
        }
    }

    protected abstract Object doApply(Object left, Object right);
}
