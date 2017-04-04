package app.processor;

import app.utils.Utility;
import app.utils.ds.Stack;
import app.utils.patterns.Observer;

import java.text.ParseException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Evaluator
{
    private Stack stack;
    private String expression;
    private ArrayList<Observer> observers;
    private Postfixify postfixify;
    private String postFixExpression;
    private HashSet<Character> operators;

    public Evaluator()
    {
        this.postfixify = new Postfixify(this);
        this.stack = postfixify.getStack();
        this.observers = new ArrayList<>();
        this.operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
    }

    public void setExpression(String expression)
    {
        StringValidator.resetParseOffset();
        expression = Utility.insertMultiplication(Utility.stripWhitespace(expression), operators);
        if (StringValidator.isValid(expression, operators)) {
            this.expression = expression;
        } else {
            this.alertError("Invalid expression.");
        }
    }

    public String compute()
    {
        if (this.expression.equals("")) {
            this.alertError("Expression not specified.");
        }

        String[] postFix = new String[1];
        try {
            postFix = postfixify.postFixify(this.expression);
        } catch (ParseException pex) {
            this.alertError(pex.getMessage());
        }

        this.postFixExpression = this.unifyArray(postFix);

        // Evaluate expression
        this.stack = new Stack(this.getNumNumbers(postFix));
        for (String symbol : postFix) {
            if (Utility.isNumeric(symbol)) {
                this.stack.push(symbol);
                this.notify(this.stack.getDSContents());
            } else if (operators.contains(symbol.charAt(0))) {
                // There will be at least two operands in the stack.
                double secondOperand = Double.parseDouble(this.stack.pop());
                double firstOperand = Double.parseDouble(this.stack.pop());
                if (symbol.equals("+")) {
                    this.stack.push(Double.toString(firstOperand + secondOperand));
                } else if (symbol.equals("-")) {
                    this.stack.push(Double.toString(firstOperand - secondOperand));
                } else if (symbol.equals("*")) {
                    this.stack.push(Double.toString(firstOperand * secondOperand));
                } else if (symbol.equals("/")) {
                    this.stack.push(Double.toString(firstOperand / secondOperand));
                }
            }
        }

        String result = this.stack.pop();
        String[] explodedNumber = result.split("\\.");
        if (this.hasNonZeros(explodedNumber[1])) {
            return result;
        } else {
            return explodedNumber[0];
        }
    }

    public String getPostFixExpression()
    {
        return this.postFixExpression;
    }

    public void registerObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public void notify(HashMap<String, Object[]> stackContents)
    {
        for (Observer observer : this.observers) {
            observer.update(stackContents);
        }
    }

    private boolean hasNonZeros(String digits)
    {
        for (char digit : digits.toCharArray()) {
            if (Character.getNumericValue(digit) > 0) {
                return true;
            }
        }

        return false;
    }

    private void alertError(String message)
    {
        for (Observer observer : this.observers) {
            observer.alertParseError(message);
        }
    }

    private int getNumNumbers(String[] symbols)
    {
        int num = 0;
        for (String symbol : symbols) {
            if (Utility.isNumeric(symbol)) {
                num++;
            }
        }

        return num;
    }

    private String unifyArray(String[] symbols)
    {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < symbols.length; i++) {
            output.append(symbols[i]);

            if (i < symbols.length - 1) {
                output.append(' ');
            }
        }

        return output.toString();
    }
}
