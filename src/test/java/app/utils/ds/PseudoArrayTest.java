package app.utils.ds;

import org.junit.*;
import static org.junit.Assert.*;

public class PseudoArrayTest
{
	@Test public void arrayTest()
	{
		PseudoArray array = new PseudoArray(5);
		assertEquals("The size of the array must be 5", 5, array.getSize());
		
		for (int ctr = 0; ctr < 5; ctr++) {
			array.set(Integer.toString(ctr), ctr);
		}
		
		for (int ctr = 0; ctr < 5; ctr++) {
			assertEquals("Element " + ctr + " must have the value '" + ctr + "'", Integer.toString(ctr), array.get(ctr));
		}
	}
}