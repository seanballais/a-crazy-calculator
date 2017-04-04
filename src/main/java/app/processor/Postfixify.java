package app.processor;

import app.utils.Utility;
import app.utils.ds.Stack;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Postfixify
{
    private Evaluator evaluator;
    private Stack stack;

    public Postfixify(Evaluator evaluator)
    {
        this.evaluator = evaluator;
    }

    public String[] postFixify(String input) throws ParseException
    {
        ArrayList<String> postFix = new ArrayList<>();
        Object[] parsed;
        try {
            parsed = this.parseExpression(input);
        } catch (ParseException pex) {
            throw pex;
        }

        this.stack = new Stack(parsed.length);
        String[] parsedSymbols = Arrays.copyOf(parsed, parsed.length, String[].class);

        // Convert the expression to postfix.
        for (String symbol : parsedSymbols) {
            this.evaluateSymbol(symbol, postFix);
        }

        while (!this.stack.isEmpty()) {
            postFix.add(this.stack.pop());
            this.evaluator.notify(this.stack.getDSContents());
        }

        return Arrays.copyOf(postFix.toArray(), postFix.size(), String[].class);
    }

    public Stack getStack()
    {
        return this.stack;
    }

    private int getPrecedence(char operator)
    {
        if (operator == '*' || operator == '/') {
            return 1;
        } else {
            return 0;
        }
    }

    private void evaluateSymbol(String symbol, ArrayList<String> postFix)
    {
        if (Utility.isNumeric(symbol)) {
            postFix.add(symbol);
        } else if (symbol.equals("(")) {
            this.stack.push(symbol);
            this.evaluator.notify(this.stack.getDSContents());
        } else if (symbol.equals(")")) {
            while (!this.stack.peek().equals("(")) {
                postFix.add(this.stack.pop());
                this.evaluator.notify(this.stack.getDSContents());
            }

            this.stack.pop(); // Discard the '('.
            this.evaluator.notify(this.stack.getDSContents());
        } else if (this.stack.isEmpty() || this.stack.peek().equals("(")) {
            this.stack.push(symbol);
            this.evaluator.notify(this.stack.getDSContents());
        } else if (this.getPrecedence(symbol.charAt(0)) > this.getPrecedence(this.stack.peek().charAt(0))) {
            this.stack.push(symbol);
            this.evaluator.notify(this.stack.getDSContents());
        } else if (this.getPrecedence(symbol.charAt(0)) == this.getPrecedence(this.stack.peek().charAt(0))) {
            postFix.add(this.stack.pop());
            this.evaluator.notify(this.stack.getDSContents());
            this.stack.push(symbol);
            this.evaluator.notify(this.stack.getDSContents());
        } else if (this.getPrecedence(symbol.charAt(0)) < this.getPrecedence(this.stack.peek().charAt(0))) {
            postFix.add(this.stack.pop());
            this.evaluator.notify(this.stack.getDSContents());
            this.evaluateSymbol(symbol, postFix);
        }
    }

    private Object[] parseExpression(String expression) throws ParseException
    {
        HashSet<Character> operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');

        expression = Utility.insertMultiplication(Utility.stripWhitespace(expression), operators);
        StringValidator.resetParseOffset();
        if (!StringValidator.isValid(expression, operators)) {
            throw new ParseException("Invalid expression", StringValidator.getParseErrorOffset());
        }

        return this.explode(expression, operators);
    }

    private Object[] explode(String expression, HashSet<Character> operators) throws ParseException
    {
        ArrayList<String> symbols = new ArrayList<>();
        HashSet<Character> updatedOperators = new HashSet<>(operators);
        updatedOperators.add('(');
        updatedOperators.add(')');

        StringBuilder digits = new StringBuilder();
        int charCtr = 0;
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            } else if (updatedOperators.contains(c)) {
                if (digits.length() > 0) {
                    symbols.add(digits.toString());
                }

                symbols.add(Character.toString(c));
                digits.setLength(0);
            } else {
                throw new ParseException("Invalid expression.", charCtr);
            }

            charCtr++;
        }

        if (digits.length() > 0) {
            symbols.add(digits.toString());
        }

        return symbols.toArray();
    }
}
