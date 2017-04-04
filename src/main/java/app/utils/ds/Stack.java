package app.utils.ds;

import java.util.ArrayList;
import java.util.HashMap;

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
        return this.activeQueue.isEmpty();
    }

    public HashMap<String, Object[]> getDSContents()
    {
        HashMap<String, Object[]> contents = new HashMap<>();
        contents.put("stack", this.getStackContents());
        contents.put("queue1", this.getQueueContents(this.q1));
        contents.put("queue2", this.getQueueContents(this.q2));
        contents.put("pseudoarray1", this.getArrayContents(this.q1.getDS()));
        contents.put("pseudoarray2", this.getArrayContents(this.q2.getDS()));
        contents.put("linkedlist1", this.getListContents(this.q1.getDS().getDS()));
        contents.put("linkedlist2", this.getListContents(this.q2.getDS().getDS()));

        return contents;
    }

    private Object[] getStackContents()
    {
        if (this.isEmpty()) {
            return new Object[] {};
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

    private Object[] getQueueContents(Queue q)
    {
        Queue tmpQ = this.deepCopyQueue(q);

        // I think we forgot to add back the contents of q.

        if (tmpQ.isEmpty()) {
            return new Object[] {};
        }

        ArrayList<String> contents = new ArrayList<>();
        while (!tmpQ.isEmpty()) {
            contents.add(tmpQ.dequeue());
        }

        return contents.toArray();
    }

    private Object[] getArrayContents(PseudoArray array)
    {
        if (array.getSize() == 0) {
            return new Object[] {};
        }

        ArrayList<String> contents = new ArrayList<>();
        for (int i = 0; i < array.getSize();) {
            contents.add(array.get(i++));
        }

        return contents.toArray();
    }

    private Object[] getListContents(LinkedList list)
    {
        if (list.getCount() == 0) {
            return new Object[] {};
        }

        ArrayList<String> contents = new ArrayList<>();
        for (int i = 0; i < list.getCount();) {
            contents.add(list.get(i++));
        }

        return contents.toArray();
    }

    private Queue deepCopyQueue(Queue q)
    {
        Queue tmpQ = new Queue(5);
        Queue dropQ = new Queue(5);
        while (!q.isEmpty()) {
            String data = q.dequeue();
            tmpQ.enqueue(data);
            dropQ.enqueue(data);
        }

        while (!dropQ.isEmpty()) {
            q.enqueue(dropQ.dequeue());
        }

        return tmpQ;
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
