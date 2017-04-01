package app.processor;

import app.utils.ds.Stack;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class Postfixify
{
    private int parseErrorOffset;
    private Stack stack;

    public Object[] postFixify(String input) throws ParseException
    {
        ArrayList<String> postFix = new ArrayList<>();
        try {
            Object[] parsed = parseExpression(input);
        } catch (ParseException pex) {
            throw pex;
        }

        return postFix.toArray();
    }

    private Object[] parseExpression(String expression) throws ParseException
    {
        HashSet<Character> operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');

        expression = insertMultiplication(expression, operators);
        if (!this.isValid(expression, operators)) {
            throw new ParseException("Invalid expression", this.parseErrorOffset);
        }

        return this.explode(expression, operators);
    }

    private Object[] explode(String expression, HashSet<Character> operators) throws ParseException
    {
        ArrayList<String> symbols = new ArrayList<>();
        HashSet<Character> updatedOperators = new HashSet<>(operators);
        updatedOperators.add('(');
        updatedOperators.add(')');

        StringBuilder digit = new StringBuilder();
        int charCtr = 0;
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                digit.append(c);
            } else if (updatedOperators.contains(c)) {
                symbols.add(digit.toString());
                symbols.add(Character.toString(c));
                digit.setLength(0);
            } else {
                throw new ParseException("Invalid expression.", charCtr);
            }

            charCtr++;
        }

        return symbols.toArray();
    }

    private String insertMultiplication(String expression, HashSet<Character> operators)
    {
        StringBuilder newExpression = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if ((c == '(' && operators.contains(newExpression.charAt(newExpression.length() - 1))) ||
                    (Character.isDigit(c) && newExpression.charAt(newExpression.length() - 1) == ')')) {
                newExpression.append("*");
                newExpression.append(Character.toString(c));
            } else {
                newExpression.append(Character.toString(c));
            }
        }

        return newExpression.toString();
    }

    private boolean isValid(String expression, HashSet<Character> operators)
    {
        return this.validateCharacters(expression, operators) && this.validateOperators(expression, operators);
    }

    private boolean validateOperators(String expression, HashSet<Character> operators)
    {
        if (operators.contains(expression.charAt(0))) {
            this.parseErrorOffset = 0;
            return false;
        } else if (operators.contains(expression.charAt(expression.length() - 1))) {
            this.parseErrorOffset = expression.length() - 1;
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
                        if (!this.validateOperators(subexpression.toString(), operators)) {
                            this.parseErrorOffset += currChar;
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
                    addSubexpression = true;
                } else if (operators.contains(c)) {
                    if (!operators.contains(prevOperator)) {
                        prevOperator = c;
                    } else if (operators.contains(prevOperator)) {
                        this.parseErrorOffset = currChar;
                        return false;
                    }
                } else if (Character.isDigit(c)) {
                    prevOperator = '\0';
                }
            }

            currChar++;
        }

        if (parenthesisCtr != 0) {
            this.parseErrorOffset = currChar;
            return false;
        }

        return true;
    }

    private boolean validateCharacters(String expression, HashSet<Character> operators)
    {
        for (char c : expression.toCharArray()) {
            if (!Character.isDigit(c) && (!operators.contains(c) && c != '(' && c != ')')) {
                return false;
            }
        }

        return true;
    }
}
