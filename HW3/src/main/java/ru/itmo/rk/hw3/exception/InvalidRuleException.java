package ru.itmo.rk.hw3.exception;

import ru.itmo.rk.hw3.parser.GrammarRule;

public class InvalidRuleException extends RuntimeException {
    public InvalidRuleException(String nonTerminal, char currentChar, GrammarRule rule) {
        super("Try to use rule '" + rule + "' in case " + nonTerminal + ":" + currentChar);
    }
}
