package ru.itmo.rk.hw4.operation.api;

import java.util.Set;

public abstract class UnaryOperation extends AbstractOperation {

    public UnaryOperation(String token, Set<Class<?>> allowedTypes) {
        super(token, allowedTypes);
    }

    @Override
    protected Object doApply(Object... input) {
        if (input.length != 1) {
            throw new IllegalArgumentException("Unary operation requires 1 argument");
        }
        return doApply(input[0]);
    }

    protected abstract Object doApply(Object expr);
}
