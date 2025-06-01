package ru.itmo.rk.hw4.exception;

public class TryUpdateConstantException extends RuntimeException {
    public TryUpdateConstantException(String var) {
        super("Try to update constant variable '" + var + "'");
    }
}
