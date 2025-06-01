package ru.itmo.rk.hw4.operation;

import java.util.Set;

public class LeEqOperation extends Operation {

    public LeEqOperation() {
        super("<=", Set.of(Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return OperationUtils.toDouble(left) <= OperationUtils.toDouble(right);
    }
}
