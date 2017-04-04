package app;

import app.processor.Evaluator;
import app.utils.patterns.Observer;

import java.util.HashMap;
import java.util.Scanner;

public class App extends Observer
{
    private Evaluator evaluator;

    public App()
    {
        super();

        this.evaluator = new Evaluator();
        this.evaluator.registerObserver(this);
    }

    public void update(HashMap<String, Object[]> stackContents)
    {
        System.out.println("===");
        System.out.print("Stack contents: ");
        this.printObjects(stackContents.get("stack"));
        System.out.print("Queue 1 contents: ");
        this.printObjects(stackContents.get("queue1"));
        System.out.print("Queue 2 contents: ");
        this.printObjects(stackContents.get("queue2"));
        System.out.print("Pseudo Array 1 contents: ");
        this.printObjects(stackContents.get("pseudoarray1"));
        System.out.print("Pseudo Array 2 contents: ");
        this.printObjects(stackContents.get("pseudoarray2"));
        System.out.print("Linked List 1 contents: ");
        this.printObjects(stackContents.get("linkedlist1"));
        System.out.print("Linked List 2 contents: ");
        this.printObjects(stackContents.get("linkedlist2"));
    }

    public void alertParseError(String error)
    {
        System.out.println(error);
        System.exit(-1);
    }

    public Evaluator getEvaluator()
    {
        return this.evaluator;
    }

    private void printObjects(Object[] contents)
    {
        for (int i = 0; i < contents.length; i++) {
            System.out.print(contents[i]);

            if (i < contents.length - 1) {
                System.out.print(' ');
            }
        }

        System.out.println("");
    }

    public static void main(String[] args)
    {
        App app = new App();

        Scanner scan = new Scanner(System.in);

        System.out.print("Expression: ");

        app.getEvaluator().setExpression(scan.nextLine());

        System.out.println("\nData Structure Contents");

        String value = app.getEvaluator().compute();
        System.out.println("Postfix String: " + app.getEvaluator().getPostFixExpression());
        System.out.println("\n-----");
        System.out.println("Computed Value: " + value);
    }
}
