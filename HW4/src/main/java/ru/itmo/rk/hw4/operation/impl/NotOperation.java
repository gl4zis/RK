package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.api.UnaryOperation;

import java.util.Set;

public class NotOperation extends UnaryOperation {

    public NotOperation() {
        super("prefix!", Set.of(Boolean.class));
    }

    @Override
    protected Object doApply(Object expr) {
        return !(Boolean) expr;
    }
}
