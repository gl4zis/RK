package ru.itmo.rk.hw4.operation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OperationUtils {

    public static double toDouble(Object obj) {
        if (obj instanceof Long l) return l.doubleValue();
        if (obj instanceof Double) return (Double) obj;
        throw new RuntimeException("Cannot convert to double: " + obj);
    }

    public static Object promote(Double value) {
        if (value % 1 == 0) {
            return value.longValue();
        }
        return value;
    }
}
