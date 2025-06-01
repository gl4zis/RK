package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.OperationUtils;
import ru.itmo.rk.hw4.operation.api.BinaryOperation;

import java.util.Set;

public class GtEqOperation extends BinaryOperation {

    public GtEqOperation() {
        super(">=", Set.of(Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return OperationUtils.toDouble(left) >= OperationUtils.toDouble(right);
    }
}
