package app.utils.ds;

import org.junit.*;
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
            stack.getContents();
            fail("Stack should throw an exception because it is empty.");
        } catch (IllegalStateException istex) {
            assertEquals(
                "Stack should throw an exception that says it is empty.",
                "Stack is empty.",
                istex.getMessage());
        }

        for (int ctr = 0; ctr < 5;) {
            stack.push(Integer.toString(++ctr));
        }

        Object[] stackContent = stack.getContents();
        for (Object content : stackContent) {
            System.out.println(content);
        }
    }
}
