package ru.itmo.rk.hw4.var;

public record Variable(
        String name,
        boolean constant,
        Object value
) { }
