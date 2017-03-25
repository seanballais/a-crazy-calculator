package app.utils.ds;

import java.util.ArrayList;

public class Stack
{
    private Queue q1;
    private Queue q2;
    private Queue activeQueue;

    public Stack(int size)
    {
        this.q1 = new Queue(size);
        this.q2 = new Queue(size);
        this.activeQueue = this.q1;
    }

    public void push(String data)
    {
        this.getActiveQueue().enqueue(data);
    }

    public String pop() throws IllegalStateException
    {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        String data;
        while (true) {
            data = this.getActiveQueue().dequeue();
            if (this.getActiveQueue().isEmpty()) {
                break;
            }

            this.getDropQueue().enqueue(data);
        }

        this.switchActiveQueue();
        return data;
    }

    public String peek() throws IllegalStateException
    {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        String data = "";
        while (!this.getActiveQueue().isEmpty()) {
            data = this.getActiveQueue().dequeue();
            this.getDropQueue().enqueue(data);
        }

        this.switchActiveQueue();
        return data;
    }

    public boolean isEmpty()
    {
        return q1.isEmpty() && q2.isEmpty();
    }

    public Object[] getContents() throws IllegalStateException
    {
        if (this.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        ArrayList<String> contents = new ArrayList<>();
        while (!this.getActiveQueue().isEmpty()) {
            String content = this.getActiveQueue().dequeue();
            contents.add(content);
            this.getDropQueue().enqueue(content);
        }

        this.switchActiveQueue();
        return contents.toArray();
    }

    private Queue getActiveQueue()
    {
        return this.activeQueue;
    }

    private Queue getDropQueue()
    {
        if (this.activeQueue == this.q1) {
            return this.q2;
        } else {
            return this.q1;
        }
    }

    private void switchActiveQueue()
    {
        if (this.activeQueue == this.q1) {
            this.activeQueue = this.q2;
        } else {
            this.activeQueue = this.q1;
        }
    }
}
