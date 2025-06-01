package ru.itmo.rk.hw4.operation;

import java.util.Objects;
import java.util.Set;

public class EqOperation extends Operation {

    public EqOperation() {
        super("==", Set.of(Long.class, Double.class, String.class, Boolean.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return Objects.equals(left, right);
    }
}
