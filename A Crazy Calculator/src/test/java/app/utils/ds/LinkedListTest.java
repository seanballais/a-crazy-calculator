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
		
		try {
			list.insert("-", 1);
		} catch (IndexOutOfBoundsException iex) {
			fail("A new node must be inserted in the list.");
		}
		
		try {
			list.insert("-", -1);
			fail("Index must not be lower than 0.");
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception should say that the index must not be lower than 0.",
				"Index specified must be greater than 0.",
				iex.getMessage()
			);
		}
		
		try {
			list.insert("!", list.getCount() + 1);
			fail("Index is out of bounds. An exception must be raised.");
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception must say that the index is out of bounds.",
				"Index specified is greater than the number of nodes.",
				iex.getMessage()
			);
		}
		
		try {
			list.delete(1);
		} catch (IndexOutOfBoundsException iex) {
			fail("A node of index 1 must be deleted in the list.");
		}
		
		list.pushFront("Hello");
		
		assertEquals(
			"List must now have 4 nodes",
			4,
			list.getCount()
		);
		
		try {
			assertEquals(
				"'3' must be popped out",
				"3",
				list.popBack()
			);
		} catch (IllegalStateException iex) {
			fail("List is supposed to be filled with nodes");
		}
		
		try {
			assertEquals(
				"'3' must be popped out",
				"Hello",
				list.popFront()
			);
		} catch (IllegalStateException iex) {
			fail("List is supposed to be filled with nodes");
		}
		
		assertEquals(
			"List must now only contain 2 nodes.",
			2,
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
		
		
		list.popFront();
		list.popBack();
		
		assertEquals(
			"List must now be empty.",
			0,
			list.getCount()
		);
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
		
		try {
			list.popFront();
			fail("This must fail. List is still empty.");
		} catch (IllegalStateException istex) {
			assertEquals(
				"Exception must say that the list is empty.",
				"Linked list is empty.",
				istex.getMessage()
			);
		}
		
		try {
			list.popBack();
			fail("This must fail. List is still empty.");
		} catch (IllegalStateException istex) {
			assertEquals(
				"Exception must say that the list is empty.",
				"Linked list is empty.",
				istex.getMessage()
			);
		}
		
		list.pushFront("1");
		
		try {
			list.insert("", 1);
			fail("This must fail. List only has 1 node.");
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception must say that the is out of bounds.",
				"Index specified is greater than the number of nodes.",
				iex.getMessage()
			);
		}
		
		try {
			list.insert("", -1);
			fail("This must fail. Index is way below the bounds.");
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception must say that the is out of bounds.",
				"Index specified must be greater than 0.",
				iex.getMessage()
			);
		}
		
		list.popFront();
		
		try {
			list.delete(0);
			fail("This must fail. List is empty.");
		} catch (IllegalStateException istex) {
			assertEquals(
				"Exception must say that the list is empty.",
				"Linked list is empty.",
				istex.getMessage()
			);
		}
		
		list.pushFront("1");
		list.pushBack("2");
		
		assertEquals(
			"The list must contain 2 nodes.",
			2,
			list.getCount()
		);
		
		try {
			list.delete(2);
			fail("The index specified does not map to a node in the list.");
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception must say that the index is out of bounds.",
				"Index specified is greater than the number of nodes.",
				iex.getMessage()
			);
		}
		
		try {
			list.delete(-1);
		} catch (IndexOutOfBoundsException iex) {
			assertEquals(
				"Exception must say that the index is out of bounds.",
				"Index specified must be greater than 0.",
				iex.getMessage()
			);
		}
		
		list.popBack();
		
		try {
			list.insert("Test", 0);
			list.delete(0);
		} catch (IndexOutOfBoundsException iex) {
			fail("This should not have failed. Index is out of bounds. Message: " + iex.getMessage());
		} catch (IllegalStateException istex) {
			fail("This should not have failed. List is empty.");
		}
		
		assertEquals(
			"The first node in the list must be '1'",
			"1",
			list.get(0)
		);
		
		list.popFront();
		
		try {
			list.set("1", 0);
			fail("This must fail since the list is empty.");
		} catch (IllegalStateException istex) {
			assertEquals(
				"Exception must say the the list is empty",
				"Linked list is empty.",
				istex.getMessage()
			);
		}
		
		list.pushFront("1");
		list.set("Hello", 0);
		assertEquals(
			"First node must contain 'Hello'",
			"Hello",
			list.get(0)
		);
	}
}