package app.processor;

import app.utils.ds.Stack;

import java.util.HashMap;

public class Evaluator
{
    private Stack stack;
    private String expression;

    public Evaluator(String expression)
    {
        this.stack = new Stack(15);
        this.expression = expression;
    }

    public void notify(HashMap<String, Object[]> data)
    {

    }
}
