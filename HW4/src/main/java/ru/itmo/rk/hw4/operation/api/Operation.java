package ru.itmo.rk.hw4.operation.api;

public interface Operation {
    String getToken();
    Object apply(Object... input);
}
