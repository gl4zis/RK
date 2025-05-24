package ru.itmo.rk.hw3.exception;

public class InvalidInputLengthException extends RuntimeException {
    public InvalidInputLengthException() {
        super("Invalid input length");
    }
}
