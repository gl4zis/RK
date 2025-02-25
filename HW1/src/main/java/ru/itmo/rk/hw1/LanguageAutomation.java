package ru.itmo.rk.hw1;

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
        A(false),
        B(false),
        C(false),
        D(true),
        E(false);

        private final boolean isValid;
        private State a;
        private State b;
        private State c;

        static {
            S.a = B; S.b = A; S.c = A;
            A.a = D; A.b = C; A.c = C;
            B.a = E; B.b = D; B.c = D;
            C.a = B; C.b = A; C.c = A;
            D.a = E; D.b = B; D.c = B;
            E.a = E; E.b = E; E.c = E;
        }
    }
}