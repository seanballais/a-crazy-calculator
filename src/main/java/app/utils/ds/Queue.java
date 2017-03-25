package app.utils.ds;

public class Queue
{
	private PseudoArray elements;
	private int frontIndex;
	private int rearIndex;
	
	public Queue(int size)
	{
		this.elements = new PseudoArray(size);
		this.frontIndex = 0;
		this.rearIndex = -1;
	}
	
	public boolean isEmpty()
	{
		return this.rearIndex < this.frontIndex;
	}
	
	public String peekFront() throws IllegalStateException
	{
		if (this.isEmpty()) {
			throw new IllegalStateException("Queue is empty.");
		}
		
		return this.elements.get(this.frontIndex);
	}
	
	public String dequeue() throws IllegalStateException
	{
		if (this.isEmpty()) {
			throw new IllegalStateException("Queue is empty.");
		}
		
		String data = this.elements.get(this.frontIndex);
		this.frontIndex++;
		
		return data;
	}
	
	public void enqueue(String data)
	{
		this.rearIndex++;
		
		if (this.rearIndex >= this.elements.getSize()) {
			this.extendList(5); // Arbitrarily, we extend it by 5.
		}
		
		this.elements.set(data, this.rearIndex);
	}
	
	public void clear()
	{
		this.frontIndex = 0;
		this.rearIndex = -1;
	}
	
	private void extendList(int size)
	{
		int newSize = this.elements.getSize() + size;
		PseudoArray tmpArray = new PseudoArray(newSize);
		for (int ctr = 0; ctr < this.elements.getSize(); ctr++) {
			tmpArray.set(this.elements.get(ctr), ctr);
		}
		
		this.elements = tmpArray;
	}
}