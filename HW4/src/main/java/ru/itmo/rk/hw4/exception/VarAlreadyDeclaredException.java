package ru.itmo.rk.hw4.exception;

public class VarAlreadyDeclaredException extends RuntimeException {
    public VarAlreadyDeclaredException(String var) {
        super("Variable with name '" + var + "' is already declared");
    }
}
