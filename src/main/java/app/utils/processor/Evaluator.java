package app.utils.processor;

import app.utils.ds.Stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.text.ParseException;

public class Evaluator
{
    private Stack stack;
    private String expression;
    private String[] contentHistory; // Turn this into a Queue instead.

    public Evaluator(String expression)
    {
        this.stack = new Stack(15);
        this.expression = expression;
    }

    public int evaluate()
    {
        return 0;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public String[] getContentHistory()
    {
        return this.contentHistory;
    }

    private Object[] postFixify()
    {
        ArrayList<String> postFixString = new ArrayList<>()
    }

    private Object[] parseExpression(String expression) throws ParseException
    {
        expression = insertMultiplication(expression);
        if (!this.validate(expression)) {
            throw new ParseException("Invalid expression.", 0);
        }


    }

    private String insertMultiplication(String expression)
    {
        String newExpression = "";
        char[] symbols = { '+', '-', '*', '/' };
        for (char c : expression.toCharArray()) {
            if ((c == '(' && this.isIn(newExpression.charAt(newExpression.length() - 1), symbols)) ||
               (Character.isDigit(c) && newExpression.charAt(newExpression.length() - 1) == ')')) {
                newExpression += "*" + Character.toString(c);
            } else {
                newExpression += Character.toString(c);
            }
        }

        return newExpression;
    }

    private boolean validate(String expression)
    {
        HashSet<Character> operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');

        return this.validateCharacters(expression, operators) && this.validateOperators(expression, operators);
    }

    private boolean validateOperators(String expression, HashSet<Character> operators)
    {
        if (operators.contains(expression.charAt(0)) ||
           operators.contains(expression.charAt(expression.length() - 1))) {
            return false;
        }

        int parenthesisCtr = 0;
        char prevOperator = '\0';
        StringBuilder subexpression = new StringBuilder();
        boolean addSubexpression = false;

        for (char c : expression.toCharArray()) {
            if (addSubexpression) {
                if (c == '(') {
                    parenthesisCtr++;
                } else if (c == ')') {
                    parenthesisCtr--;
                    if (parenthesisCtr == 0) {
                        // We found the end of the expression inside the parentheses.
                        if (!this.validateOperators(subexpression.toString(), operators)) {
                            return false;
                        }
                        subexpression.setLength(0);
                        prevOperator = '\0';
                        addSubexpression = false;
                    } else {
                        return false;
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
                        return false;
                    }
                } else if (Character.isDigit(c)) {
                    prevOperator = '\0';
                }
            }
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
