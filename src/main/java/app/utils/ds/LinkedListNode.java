package app.utils.ds;

public class LinkedListNode
{
	private String data;
	private LinkedListNode next;
	
	public LinkedListNode()
	{
		this.setUpNode("", null);
	}
	
	public LinkedListNode(String data, LinkedListNode next)
	{
		this.setUpNode(data, next);
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public void setNext(LinkedListNode next)
	{
		this.next = next;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public LinkedListNode getNext()
	{
		return this.next;
	}
	
	private void setUpNode(String data, LinkedListNode next)
	{
		this.data = data;
		this.next = next;
	}
}