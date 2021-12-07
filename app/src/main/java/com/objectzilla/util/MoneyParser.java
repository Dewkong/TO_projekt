package com.objectzilla.util;

import java.math.BigDecimal;

public class MoneyParser {

    private MoneyParser() {
    }

    public static BigDecimal parseMoneyString(String string) {
        return new BigDecimal(normalizeSeparator(removeSpaces(string)));
    }

    private static String removeSpaces(String moneyString) {
        return moneyString.replace(" ", "");
    }

    private static String normalizeSeparator(String moneyString) {
        return moneyString.replace(',', '.');
    }
}
