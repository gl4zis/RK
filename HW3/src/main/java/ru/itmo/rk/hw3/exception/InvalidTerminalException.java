package ru.itmo.rk.hw3.exception;

public class InvalidTerminalException extends RuntimeException {
    public InvalidTerminalException(char terminal, String nonTerminal) {
        super("Invalid terminal '" + terminal + "' for non-terminal " + nonTerminal);
    }
}
