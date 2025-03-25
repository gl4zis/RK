package ru.itmo.rk.hw2;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageValidator {
    public static Resolution validate(@NonNull String sentence) {
        List<LanguageAutomation.State> trace = new ArrayList<>();
        var automation = new LanguageAutomation();
        trace.add(automation.getState());
        for (char c : sentence.toCharArray()) {
            automation.next(c);
            if (automation.getState() == null) {
                break;
            }
            trace.add(automation.getState());
        }
        return new Resolution(trace, automation.getState() != null && automation.getState().isValid());
    }

    public record Resolution(List<LanguageAutomation.State> trace, boolean isValid) {
        private static final String VALID_PRINT = "OK";
        private static final String INVALID_PRINT = "INVALID";

        public void prettyPrint() {
            var stringTrace = trace.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(" -> "));
            System.out.println(stringTrace);
            System.out.println(isValid ? VALID_PRINT : INVALID_PRINT);
        }
    }
}
