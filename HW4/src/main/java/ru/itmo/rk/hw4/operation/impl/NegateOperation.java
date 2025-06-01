package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.OperationUtils;
import ru.itmo.rk.hw4.operation.api.UnaryOperation;

import java.util.Set;

public class NegateOperation extends UnaryOperation {

    public NegateOperation() {
        super("prefix-", Set.of(Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object expr) {
        return OperationUtils.promote(- OperationUtils.toDouble(expr));
    }
}
