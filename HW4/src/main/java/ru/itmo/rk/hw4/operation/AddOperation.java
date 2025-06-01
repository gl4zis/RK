package ru.itmo.rk.hw4.operation;

import java.util.Set;

public class AddOperation extends Operation {

    public AddOperation() {
        super("+", Set.of(String.class, Long.class, Double.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        if (left instanceof String leftStr && right instanceof String rightStr) {
            return leftStr + rightStr;
        } else {
            double l = OperationUtils.toDouble(left);
            double r = OperationUtils.toDouble(right);
            return OperationUtils.promote(l + r);
        }
    }
}
