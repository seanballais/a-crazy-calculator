package app.utils.ds;

public class PseudoArray
{
	private LinkedList list;
	
	public PseudoArray(int size)
	{
		this.list = new LinkedList();
		while (size > 0) {
			this.list.pushBack("");
			
			size--;
		}
	}
	
	public void set(String data, int index)
	{
		this.list.set(data, index);
	}
	
	public String get(int index)
	{
		return this.list.get(index);
	}
	
	public int getSize()
	{
		return this.list.getCount();
	}
	
	public LinkedList getDS()
	{
		return this.list;
	}
}