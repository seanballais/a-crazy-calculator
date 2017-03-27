package app.utils.ds;

import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTest
{
	@Test public void threeNodeTest()
	{
		LinkedList list = new LinkedList();
		
		list.pushBack("1");
		list.pushBack("2");
		list.pushBack("3");
		
		assertEquals("List must have 3 nodes inside", 3, list.getCount());
		
		list.pushBack("Hello");
		
		assertEquals(
			"List must now have 4 nodes",
			4,
			list.getCount()
		);
		
		try {
			assertEquals(
				"Must get '2' from a node of index '1'",
				"2",
				list.get(1)
			);
		} catch (IndexOutOfBoundsException iex) {
			fail("The index is supposed to be allowed since it is less than number of nodes minus 1.");
		} catch (IllegalStateException istex) {
			fail("The list is not supposed to be empty.");
		}
		
		try {
			list.set("10", 1);
		} catch (IndexOutOfBoundsException iex) {
			fail("The index is supposed to be allowed since it is less than number of nodes minus 1.");
		} catch (IllegalStateException istex) {
			fail("The list is not supposed to be empty.");
		}
		
		try {
			assertEquals(
				"The second node must have a value of '10'",
				"10",
				list.get(1)
			);
		} catch (IndexOutOfBoundsException iex) {
			fail("The index is supposed to be allowed since it is less than number of nodes minus 1.");
		} catch (IllegalStateException istex) {
			fail("The list is not supposed to be empty.");
		}
	}
	
	@Test public void emptyListExceptionsTest()
	{
		LinkedList list = new LinkedList();
		
		try {
			list.get(0);
			fail("This must fail. List is still empty.");
		} catch (IllegalStateException istex) {
			assertEquals(
				"Exception must say that the list is empty.",
				"Linked list is empty.",
				istex.getMessage()
			);
		}

		list.pushBack("1");
		
		assertEquals(
			"The list must contain 1 node.",
			1,
			list.getCount()
		);
		
		assertEquals(
			"The first node in the list must be '1'",
			"1",
			list.get(0)
		);

		list.set("Hello", 0);
		assertEquals(
			"First node must contain 'Hello'",
			"Hello",
			list.get(0)
		);
	}
}