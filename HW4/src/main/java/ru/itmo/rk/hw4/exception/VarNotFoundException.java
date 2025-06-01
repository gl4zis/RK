package ru.itmo.rk.hw4.exception;

public class VarNotFoundException extends RuntimeException {
    public VarNotFoundException(String var) {
        super("Var with name '" + var + "' not found");
    }
}
