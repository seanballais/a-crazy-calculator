package app.utils;

import java.util.HashSet;

public class Utility
{
    public static boolean isNumeric(String number)
    {
        return number.matches("^(\\d+)$");
    }

    public static String insertMultiplication(String expression, HashSet<Character> operators)
    {
        StringBuilder newExpression = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (newExpression.length() == 0) {
                newExpression.append(Character.toString(c));
            } else {
                if ((c == '(' && Character.isDigit(newExpression.charAt(newExpression.length() - 1))) ||
                   (Character.isDigit(c) && newExpression.charAt(newExpression.length() - 1) == ')')) {
                    newExpression.append("*");
                    newExpression.append(Character.toString(c));
                } else {
                    newExpression.append(Character.toString(c));
                }
            }
        }

        return newExpression.toString();
    }

    public static String stripWhitespace(String expression)
    {
        StringBuilder result = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '\t' || c == '\n') {
                continue;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
