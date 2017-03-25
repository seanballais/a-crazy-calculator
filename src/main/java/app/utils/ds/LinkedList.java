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
	
	public void insert(String data, int index) throws IndexOutOfBoundsException
	{		
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index specified must be greater than 0.");
		}
		
		if (index > Math.max(0, this.count - 1)) {
			throw new IndexOutOfBoundsException("Index specified is greater than the number of nodes.");
		}
		
		LinkedListNode prevNode = this.root;
		LinkedListNode currNode = this.root.getNext();
		
		int currNodeIndex = 0;
		while (currNode != null) {
			if (currNodeIndex == index) {
				LinkedListNode newNode = new LinkedListNode(data, currNode);
				prevNode.setNext(newNode);
				this.count++;
				
				break;
			}
			
			currNodeIndex++;
			prevNode = currNode;
			currNode = currNode.getNext();
		}
	}
	
	public void delete(int index) throws IndexOutOfBoundsException, IllegalStateException
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
		
		LinkedListNode prevNode = this.root;
		LinkedListNode currNode = this.root.getNext();
		
		int currNodeIndex = 0;
		while (currNode != null) {
			if (currNodeIndex == index) {
				prevNode.setNext(currNode.getNext());
				this.count--;
				
				break;
			}
			
			currNodeIndex++;
			prevNode = currNode;
			currNode = currNode.getNext();
		}
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
	
	public void pushFront(String data)
	{
		this.root.setNext(new LinkedListNode(data, this.root.getNext()));
		this.count++;
	}
	
	public String popBack() throws IllegalStateException
	{
		if (this.count == 0) {
			throw new IllegalStateException("Linked list is empty.");
		}
		
		LinkedListNode currNode = this.root;
		String poppedData = "";
		while (currNode.getNext() != null) {
			if (currNode.getNext().getNext() == null) {
				poppedData = currNode.getNext().getData();
				currNode.setNext(null);
				this.count--;
				
				break;
			}
			
			currNode = currNode.getNext();
		}
		
		return poppedData;
	}
	
	public String popFront() throws IllegalStateException
	{
		if (this.count == 0) {
			throw new IllegalStateException("Linked list is empty.");
		}
		
		String poppedData = this.root.getNext().getData();
		this.root.setNext(this.root.getNext().getNext());
		this.count--;
		
		return poppedData;
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