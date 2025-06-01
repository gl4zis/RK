package ru.itmo.rk.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.itmo.rk.hw4.exception.IncompatibleTypesException;
import ru.itmo.rk.hw4.exception.TryUpdateConstantException;
import ru.itmo.rk.hw4.exception.VarNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public class CatlinInterpreterTest {
    private final CatlinInterpreter interpreter = new CatlinInterpreter();

    public static Stream<Arguments> goodTestCases() {
        return Stream.of(
                Arguments.of("nod", List.of(21L)),
                Arguments.of("fibonacci", List.of(34L)),
                Arguments.of("round", List.of(3L, 5L, 7L, -1L, -2L))
        );
    }

    public static Stream<Arguments> badTestCases() {
        return Stream.of(
                Arguments.of("round_error", IncompatibleTypesException.class),
                Arguments.of("const_error", TryUpdateConstantException.class),
                Arguments.of("no_declaration_error", VarNotFoundException.class)
        );
    }

    @ParameterizedTest(name = "{index}: Run {0}")
    @MethodSource("goodTestCases")
    public void runGoodCases(String filename, List<Object> expected) throws IOException {
        load(filename);
        Assertions.assertIterableEquals(expected, interpreter.run());
    }

    @ParameterizedTest(name = "{index}: Run {0}")
    @MethodSource("badTestCases")
    public void runBadCases(String filename, Class<? extends Exception> exceptionType) throws IOException {
        load(filename);
        Assertions.assertThrows(exceptionType, interpreter::run);
    }

    private void load(String filename) throws IOException {
        String program = ResourceReader.readFile(filename);
        interpreter.load(program);
    }
}
