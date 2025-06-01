package ru.itmo.rk.hw4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.rk.hw4.exception.InvalidKeywordException;

@Getter
@RequiredArgsConstructor
public enum DeclarationPrefix {
    VAR("var", false),
    VAL("val", true);

    private final String text;
    private final boolean constant;

    public static DeclarationPrefix of(String text) {
        for (DeclarationPrefix prefix : values()) {
            if (prefix.text.equals(text)) {
                return prefix;
            }
        }
        throw new InvalidKeywordException(text);
    }
}
