package ru.itmo.rk.hw4.operation;

import java.util.Set;

public class AndOperation extends Operation {

    public AndOperation() {
        super("&&", Set.of(Boolean.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return (Boolean) left && (Boolean) right;
    }
}
