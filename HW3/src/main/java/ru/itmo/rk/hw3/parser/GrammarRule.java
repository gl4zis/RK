package ru.itmo.rk.hw3.parser;

import ru.itmo.rk.hw3.exception.InvalidRuleException;

import java.util.List;

public class GrammarRule {
    private final String nonTerminal;
    private final char startTerminal;
    private final List<String> production;

    public GrammarRule(
            String nonTerminal,
            char startTerminal,
            List<String> production
    ) {
        this.nonTerminal = nonTerminal;
        this.startTerminal = startTerminal;
        this.production = production;
    }

    public List<String> transform(String nonTerminal, char currentChar) {
        if (this.nonTerminal.equals(nonTerminal) && startTerminal == currentChar) {
            return production;
        } else {
            throw new InvalidRuleException(nonTerminal, currentChar, this);
        }
    }

    @Override
    public String toString() {
        return nonTerminal + ":" + startTerminal + " → " + String.join("", production);
    }
}
