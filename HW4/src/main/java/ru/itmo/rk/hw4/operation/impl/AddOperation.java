package ru.itmo.rk.hw4.operation.impl;

import ru.itmo.rk.hw4.operation.OperationUtils;
import ru.itmo.rk.hw4.operation.api.BinaryOperation;

import java.util.Set;

public class AddOperation extends BinaryOperation {

    public AddOperation() {
        super("+", Set.of(String.class, Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        if (left instanceof String || right instanceof String) {
            return String.valueOf(left) + right;
        } else {
            double l = OperationUtils.toDouble(left);
            double r = OperationUtils.toDouble(right);
            return OperationUtils.promote(l + r);
        }
    }
}
