package com.archisacademy.jobportal.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CommonUtils {
    private static final String COMMA = ",";

    private CommonUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String listToCommaSeparatedString(List<String> list) {
        return String.join(COMMA, list);
    }

    public static List<String> commaSeparatedStringToList(String anyThing) {
        return Arrays.stream(anyThing.split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
