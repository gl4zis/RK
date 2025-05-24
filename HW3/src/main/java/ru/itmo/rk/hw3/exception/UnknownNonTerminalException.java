package ru.itmo.rk.hw3.exception;

public class UnknownNonTerminalException extends RuntimeException {
    public UnknownNonTerminalException(String nonTerminal) {
        super("Unknown non-terminal " + nonTerminal);
    }
}
