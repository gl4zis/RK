package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.api.BinaryOperation;

import java.util.Objects;
import java.util.Set;

public class NotEqOperation extends BinaryOperation {

    public NotEqOperation() {
        super("!=", Set.of(Long.class, Double.class, String.class, Boolean.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return !Objects.equals(left, right);
    }
}
