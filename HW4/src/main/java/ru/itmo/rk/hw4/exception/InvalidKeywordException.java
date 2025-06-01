package ru.itmo.rk.hw4.exception;

public class InvalidKeywordException extends RuntimeException {

    public InvalidKeywordException(String keyword) {
        super("Found invalid keyword '" + keyword + "'");
    }
}
