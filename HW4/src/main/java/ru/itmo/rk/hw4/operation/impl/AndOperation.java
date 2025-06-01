package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.api.BinaryOperation;

import java.util.Set;

public class AndOperation extends BinaryOperation {

    public AndOperation() {
        super("&&", Set.of(Boolean.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return (Boolean) left && (Boolean) right;
    }
}
