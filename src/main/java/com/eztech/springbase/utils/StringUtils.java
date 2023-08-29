package com.eztech.springbase.utils;

public class StringUtils {
    public static String toCamelCase(String s) {
        String[] parts = s.split("_");
        StringBuilder camelCase = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCase.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1).toLowerCase());
        }
        return camelCase.toString();
    }

    public static String truncate(String str, int maxLength) {
        if (str.length() > maxLength) {
            String s = str.substring(0, maxLength);
            return s + "...";
        }
        return str;
    }
}
