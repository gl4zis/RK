package ru.itmo.rk.hw3.parser;

import ru.itmo.rk.hw3.exception.InvalidRuleException;
import ru.itmo.rk.hw3.exception.InvalidTerminalException;
import ru.itmo.rk.hw3.exception.UnknownNonTerminalException;

import java.util.*;

public class LL1Grammar {
    public static final LL1Grammar VARIANT_9_GRAMMAR = new LL1Grammar("S");

    static {
        VARIANT_9_GRAMMAR.addRule("S", 'a', List.of("A", "B", "C", "C"));
        VARIANT_9_GRAMMAR.addRule("S", 'c', List.of("A", "B", "C", "C"));

        VARIANT_9_GRAMMAR.addRule("A", 'a', List.of("a", "A", "a"));
        VARIANT_9_GRAMMAR.addRule("A", 'c', List.of("c"));

        VARIANT_9_GRAMMAR.addRule("B", 'b', List.of("b", "B'"));

        VARIANT_9_GRAMMAR.addRule("B'", 'a', List.of("a", "B'"));
        VARIANT_9_GRAMMAR.addRule("B'", 'b', List.of("b", "B"));
        VARIANT_9_GRAMMAR.addRule("B'", 'd', List.of("d"));

        VARIANT_9_GRAMMAR.addRule("C", 'c', List.of("c", "C'"));

        VARIANT_9_GRAMMAR.addRule("C'", 'c', List.of("c", "C''"));
        VARIANT_9_GRAMMAR.addRule("C'", 'd', List.of("d"));

        VARIANT_9_GRAMMAR.addRule("C''", 'b', List.of("B", "B"));
        VARIANT_9_GRAMMAR.addRule("C''", 'c', List.of("c", "A"));
    }

    private final String rootSymbol;
    private final Map<String, Set<GrammarRule>> ruleMap = new HashMap<>();

    public LL1Grammar(String rootSymbol) {
        this.rootSymbol = rootSymbol;
        ruleMap.put(rootSymbol, new HashSet<>());
    }

    public List<String> apply(String nonTerminal, char currentChar) {
        Set<GrammarRule> rules = ruleMap.get(nonTerminal);
        if (rules == null) {
            throw new UnknownNonTerminalException(nonTerminal);
        }

        for (GrammarRule rule : rules) {
            try {
                List<String> production = rule.transform(nonTerminal, currentChar);
                return production.reversed();
            } catch (InvalidRuleException ignored) {
                // Just try next rule
            }
        }
        throw new InvalidTerminalException(currentChar, nonTerminal);
    }

    public String getRootSymbol() {
        return rootSymbol;
    }

    public Set<String> getNonTerminals() {
        return ruleMap.keySet();
    }

    private void addRule(String nonTerminal, char startTerminal, List<String> production) {
        GrammarRule rule = new GrammarRule(nonTerminal, startTerminal, production);
        Set<GrammarRule> ruleSet = ruleMap.getOrDefault(nonTerminal, new HashSet<>());
        ruleSet.add(rule);
        ruleMap.put(nonTerminal, ruleSet);
    }
}
