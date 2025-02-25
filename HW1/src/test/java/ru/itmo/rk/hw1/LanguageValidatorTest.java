package ru.itmo.rk.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LanguageValidatorTest {

    private static Stream<Arguments> getTestCases() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("a", false),
                Arguments.of("aa", false),
                Arguments.of("bacc", true),
                Arguments.of("bccca", false),
                Arguments.of("bccc", false),
                Arguments.of("ab", true),
                Arguments.of("bcbcbcba", true)
        );
    }

    private static Stream<Arguments> getIllegalTestCases() {
        return Stream.of(
                Arguments.of("aaabbb", null),
                Arguments.of("abc", null),
                Arguments.of("abcd", IllegalArgumentException.class),
                Arguments.of(null, NullPointerException.class),
                Arguments.of("abc-abc", IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    public void testSentenceValidation(String sentence, boolean expectedResolution) {
        Assertions.assertEquals(expectedResolution, LanguageValidator.validate(sentence).isValid());
    }

    @ParameterizedTest
    @MethodSource("getIllegalTestCases")
    public void testValidationErrors(String sentence, Class<? extends Exception> exception) {
        if (exception == null) {
            Assertions.assertDoesNotThrow(() -> LanguageValidator.validate(sentence));
        } else {
            Assertions.assertThrows(exception, () -> LanguageValidator.validate(sentence));
        }
    }
}
