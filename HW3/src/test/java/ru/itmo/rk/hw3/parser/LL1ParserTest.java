package ru.itmo.rk.hw3.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LL1ParserTest {

    @Test
    public void testValidInput1() {
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, "acabdcdcd");
        assertTrue(parser.parseSafe());
    }

    @Test
    public void testValidInput2() {
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, "cbabbdcccAccbdbbbd");
        assertTrue(parser.parseSafe());
    }

    @Test
    public void testInvalidInput1() {
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, "abcc");
        assertFalse(parser.parseSafe());
    }

    @Test
    public void testInvalidInput2() {
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, "acbcd");
        assertFalse(parser.parseSafe());
    }

    @Test
    public void testEmptyInput() {
        LL1Parser parser = new LL1Parser(LL1Grammar.VARIANT_9_GRAMMAR, "");
        assertFalse(parser.parseSafe());
    }
}

