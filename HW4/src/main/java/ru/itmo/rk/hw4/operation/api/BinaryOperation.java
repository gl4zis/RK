package ru.itmo.rk.hw4.operation.api;

import java.util.Set;

public abstract class BinaryOperation extends AbstractOperation {

    public BinaryOperation(String token, Set<Class<?>> allowedTypes) {
        super(token, allowedTypes);
    }

    @Override
    protected Object doApply(Object... input) {
        if (input.length != 2) {
            throw new IllegalArgumentException("Binary operation requires 2 arguments");
        }
        return doApply(input[0], input[1]);
    }

    protected abstract Object doApply(Object left, Object right);
}
