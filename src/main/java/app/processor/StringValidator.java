package app.processor;

import java.util.HashSet;

public class StringValidator
{
    private static int parseErrorOffset;

    public static boolean isValid(String expression, HashSet<Character> operators)
    {
        return validateCharacters(expression, operators) && validateOperators(expression, operators);
    }

    public static int getParseErrorOffset()
    {
        return parseErrorOffset;
    }

    public static void resetParseOffset()
    {
        parseErrorOffset = 0;
    }

    private static boolean validateOperators(String expression, HashSet<Character> operators)
    {
        if (operators.contains(expression.charAt(0))) {
            parseErrorOffset = 0;
            return false;
        } else if (operators.contains(expression.charAt(expression.length() - 1))) {
            parseErrorOffset = expression.length() - 1;
            return false;
        }

        int parenthesisCtr = 0;
        char prevOperator = '\0';
        StringBuilder subexpression = new StringBuilder();
        boolean addSubexpression = false;

        int currChar = 0;
        for (char c : expression.toCharArray()) {
            if (addSubexpression) {
                if (c == '(') {
                    parenthesisCtr++;
                } else if (c == ')') {
                    parenthesisCtr--;
                    if (parenthesisCtr == 0) {
                        // We found the end of the expression inside the parentheses.
                        if (!validateOperators(subexpression.toString(), operators)) {
                            parseErrorOffset += currChar;
                            return false;
                        }
                        subexpression.setLength(0);
                        prevOperator = '\0';
                        addSubexpression = false;
                    }
                }

                subexpression.append(c);
            } else {
                if (c == '(') {
                    parenthesisCtr++;
                    addSubexpression = true;
                } else if (operators.contains(c)) {
                    if (!operators.contains(prevOperator)) {
                        prevOperator = c;
                    } else if (operators.contains(prevOperator)) {
                        parseErrorOffset = currChar;
                        return false;
                    }
                } else if (Character.isDigit(c)) {
                    prevOperator = '\0';
                } else if (c == ')') {
                    parseErrorOffset = currChar;
                    return false;
                }
            }

            currChar++;
        }

        if (parenthesisCtr != 0) {
            parseErrorOffset = currChar;
            return false;
        }

        return true;
    }

    private static boolean validateCharacters(String expression, HashSet<Character> operators)
    {
        for (char c : expression.toCharArray()) {
            if (!Character.isDigit(c) && (!operators.contains(c) && c != '(' && c != ')')) {
                return false;
            }
        }

        return true;
    }
}
