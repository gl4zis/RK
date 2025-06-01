package ru.itmo.rk.hw4.operation;

import java.util.Set;

public class OrOperation extends Operation {

    public OrOperation() {
        super("||", Set.of(Boolean.class));
    }

    @Override
    protected Object doApply(Object left, Object right) {
        return (Boolean) left || (Boolean) right;
    }
}
