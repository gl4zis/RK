package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.OperationUtils;
import ru.itmo.rk.hw4.operation.api.BinaryOperation;

import java.util.Set;

public class MulOperation extends BinaryOperation {

    public MulOperation() {
        super("*", Set.of(Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        double l = OperationUtils.toDouble(left);
        double r = OperationUtils.toDouble(right);
        return OperationUtils.promote(l * r);
    }
}
