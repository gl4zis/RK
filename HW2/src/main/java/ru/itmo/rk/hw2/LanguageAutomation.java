package ru.itmo.rk.hw2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LanguageAutomation {
    private State state = State.S;

    public void next(char c) {
        switch (c) {
            case 'a':
                state = state.a;
                break;
            case 'b':
                state = state.b;
                break;
            case 'c':
                state = state.c;
                break;
            default:
                throw new IllegalArgumentException("Illegal character: " + c);
        }
    }

    @Getter
    @RequiredArgsConstructor
    enum State {
        S(false),
        A(true);

        private final boolean isValid;
        private State a;
        private State b;
        private State c;

        static {
            S.a = S; S.b = A; S.c = S;
            A.a = S; A.b = A; A.c = S;
        }
    }
}