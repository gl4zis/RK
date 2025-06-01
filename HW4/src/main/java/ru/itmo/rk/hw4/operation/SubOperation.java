package ru.itmo.rk.hw4.operation;

import java.util.Set;

public class SubOperation extends Operation {

    public SubOperation() {
        super("-", Set.of(Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        double l = OperationUtils.toDouble(left);
        double r = OperationUtils.toDouble(right);
        return OperationUtils.promote(l - r);
    }
}
