package ru.itmo.rk.hw3.exception;

public class UnknownInputException extends RuntimeException {
    public UnknownInputException(char symbol, int index) {
        super("Unknown symbol '" + symbol + "' on position " + index + " in input string");
    }
}
