package ru.itmo.rk.hw4.operation;

import ru.itmo.rk.hw4.exception.InvalidKeywordException;

import java.util.HashMap;
import java.util.Map;

public class OperationStrategy {
    private final Map<String, Operation> operations = new HashMap<>();

    public OperationStrategy() {
        add(new AddOperation());
        add(new SubOperation());
        add(new MulOperation());
        add(new DivOperation());

        add(new LeOperation());
        add(new GtOperation());
        add(new LeEqOperation());
        add(new GtEqOperation());

        add(new EqOperation());
        add(new NotEqOperation());

        add(new OrOperation());
        add(new AndOperation());
    }

    public Operation choose(String token) {
        Operation operation = operations.get(token);
        if (operation == null) {
            throw new InvalidKeywordException(token);
        }
        return operation;
    }

    private void add(Operation operation) {
        operations.put(operation.getToken(), operation);
    }
}
