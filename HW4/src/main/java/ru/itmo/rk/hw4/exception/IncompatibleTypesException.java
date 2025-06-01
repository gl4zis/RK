package ru.itmo.rk.hw4.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class IncompatibleTypesException extends RuntimeException {
    public IncompatibleTypesException(String operation, Class<?>... types) {
        super("Cannot do operation '" + operation +
                "' with types: " + typesString(types));
    }

    private static String typesString(Class<?>... types) {
        return Arrays.stream(types)
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
    }
}
