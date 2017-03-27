package app.utils.ds;

import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StackTest
{
    @Test public void stackTest()
    {
        Stack stack = new Stack(1);
        assertTrue(stack.isEmpty());

        stack.push("1");
        assertFalse(stack.isEmpty());

        String data = stack.peek();
        assertFalse(stack.isEmpty());
        assertEquals("Expected that 'data' contains '1'", "1", data);

        stack.pop();
        assertTrue(stack.isEmpty());

        try {
            stack.pop();
            stack.peek();
            fail("Stack should throw an exception because it is empty.");
        } catch (IllegalStateException istex) {
            assertEquals(
                "Stack should throw an exception that says it is empty.",
                "Stack is empty.",
                istex.getMessage());
        }

        for (int ctr = 0; ctr < 10;) {
            stack.push(Integer.toString(++ctr));
        }

        HashMap<String, Object[]> contents = stack.getDSContents();
        String[] contentKeys = { "stack", "queue1", "queue2", "pseudoarray1", "pseudoarray2", "linkedlist1", "linkedlist2" };
        String[] expectedValues = { "1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        for (String key : contentKeys) {
            Object[] content = contents.get(key);
            for (int i = 0; i < content.length; i++) {
                String expected = "";
                if (key.equals("stack") || key.equals("queue1") || key.equals("queue2")) {
                    expected = expectedValues[i + 1];
                } else {
                    expected = expectedValues[i];
                }
                assertEquals(
                    "The " + key + " content of index '" + i + "' must be " + expected,
                    expected,
                    content[i]
                );
            }
        }
    }
}
