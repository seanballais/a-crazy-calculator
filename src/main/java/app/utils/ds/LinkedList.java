package app.utils.ds;

public class LinkedList
{
	private LinkedListNode root;
	private int count;
	
	public LinkedList()
	{
		this.root = new LinkedListNode("\0", null);
		this.count = 0;
	}

	public void pushBack(String data)
	{
		LinkedListNode currNode = this.root;
		
		while (currNode.getNext() != null) {
			currNode = currNode.getNext();
		}
		
		currNode.setNext(new LinkedListNode(data, null));
		this.count++;
	}
	
	public String get(int index) throws IndexOutOfBoundsException, IllegalStateException
	{
		if (this.count == 0) {
			throw new IllegalStateException("Linked list is empty.");
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index specified must be greater than 0.");
		}
		
		if (index > Math.max(0, this.count - 1)) {
			throw new IndexOutOfBoundsException("Index specified is greater than the number of nodes.");
		}
		
		LinkedListNode currNode = this.root.getNext();
		
		int currNodeIndex = 0;
		while (currNode != null) {
			if (currNodeIndex == index) {
				break;
			}
			
			currNode = currNode.getNext();
			currNodeIndex++;
		}
		
		return currNode.getData();
	}
	
	public void set(String data, int index) throws IndexOutOfBoundsException, IllegalStateException
	{
		if (this.count == 0) {
			throw new IllegalStateException("Linked list is empty.");
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index specified must be greater than 0.");
		}
		
		if (index > Math.max(0, this.count - 1)) {
			throw new IndexOutOfBoundsException("Index specified is greater than the number of nodes.");
		}
		
		LinkedListNode currNode = this.root.getNext();
		
		int currNodeIndex = 0;
		while (currNode != null) {
			if (currNodeIndex == index) {
				currNode.setData(data);
			}
			
			currNode = currNode.getNext();
			currNodeIndex++;
		}
	}
	
	public int getCount()
	{
		return this.count;
	}
}