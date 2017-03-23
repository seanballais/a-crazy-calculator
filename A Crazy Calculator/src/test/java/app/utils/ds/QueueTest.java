package app.utils.ds;

import org.junit.*;
import static org.junit.Assert.*;

public class QueueTest
{
	@Test public void queueTest()
	{
		Queue q1 = new Queue(5);
		
		assertTrue(q1.isEmpty());
		
		try {
			q1.peekFront();
			fail("Queue is still empty. An exception should occur.");
		} catch (IllegalStateException istex) {
			assertEquals("An exception should say that the queue is empty.", "Queue is empty.", istex.getMessage());
		}
		
		try {
			q1.dequeue();
			fail("Queue is still empty. An exception should occur.");
		} catch (IllegalStateException istex) {
			assertEquals("An exception should say that the queue is empty.", "Queue is empty.", istex.getMessage());
		}
		
		for (int ctr = 0; ctr < 10; ctr++) {
			q1.enqueue(Integer.toString(ctr));
		}
		
		assertFalse(q1.isEmpty());
		
		for (int ctr = 0; ctr < 10; ctr++) {
			assertEquals("Element " + ctr + " must have the value '" + ctr + "'", Integer.toString(ctr), q1.dequeue());
		}
		
		assertTrue(q1.isEmpty());
	}
}