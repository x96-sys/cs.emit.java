package org.x96.sys.util;

public class S {
    public static String toCamelCase(byte[] b) {
        return toCamelCase(new String(b));
    }

    public static String toScreamingSnakeCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.replaceAll("([a-z])([A-Z])", "$1_$2") // insere _ antes de maiúsculas
                .toUpperCase();
    }

    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                capitalizeNext = true;
            } else {
                result.append(capitalizeNext ? Character.toUpperCase(c) : Character.toLowerCase(c));
                capitalizeNext = false;
            }
        }

        return result.toString();
    }

    public static String ljust(String s, int size, char c) {
        if (s.length() >= size) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < size) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String rjust(String s, int size, char c) {
        if (s.length() >= size) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size - s.length()) {
            sb.append(c);
        }
        sb.append(s);
        return sb.toString();
    }

    public static String center(String s, int size, char c) {
        if (s.length() >= size) {
            return s;
        }
        int totalPadding = size - s.length();
        int padLeft = totalPadding / 2;
        int padRight = totalPadding - padLeft;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padLeft; i++) {
            sb.append(c);
        }
        sb.append(s);
        for (int i = 0; i < padRight; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
