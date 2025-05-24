package ru.itmo.rk.hw3.parser;

import ru.itmo.rk.hw3.exception.InvalidInputLengthException;
import ru.itmo.rk.hw3.exception.UnknownInputException;

import java.util.ArrayList;
import java.util.List;

public class LL1Parser {
    private static final char EOF = '$';

    private final String input;
    private int index = 0;
    private final List<String> stack = new ArrayList<>();

    private final LL1Grammar grammar;

    public LL1Parser(LL1Grammar grammar, String inputString) {
        this.grammar = grammar;
        this.input = inputString + EOF;

        stack.add(String.valueOf(EOF));
        stack.add(grammar.getRootSymbol());
    }

    public void parse() {
        while (!stack.isEmpty()) {
            String top = stack.getLast();
            char currentChar = input.charAt(index);

            if (top.equals(String.valueOf(currentChar))) {
                stack.removeLast();
                index++;
            } else if (grammar.getNonTerminals().contains(top)) {
                List<String> production = grammar.apply(top, currentChar);
                stack.removeLast();
                stack.addAll(production);
            } else {
                throw new UnknownInputException(currentChar, index);
            }
        }

        if (input.length() != index) {
            throw new InvalidInputLengthException();
        }
    }

    public boolean parseSafe() {
        try {
            parse();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
