package ru.itmo.rk.hw3;


import ru.itmo.rk.hw3.parser.LL1Grammar;
import ru.itmo.rk.hw3.parser.LL1Parser;

public class Main {
    public static void main(String[] args) {
        String inputString = "acabdcdcd";  // Пример входной строки
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, inputString);
        parser.parse();
    }
}
