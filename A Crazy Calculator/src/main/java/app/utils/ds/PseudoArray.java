package app.utils.ds;

public class PseudoArray
{
	private LinkedList list;
	
	public PseudoArray(int size)
	{
		list = new LinkedList();
		while (size > 0) {
			list.pushBack("");
			
			size--;
		}
	}
	
	public void set(String data, int index)
	{
		list.set(data, index);
	}
	
	public String get(int index)
	{
		return list.get(index);
	}
	
	public int getSize()
	{
		return list.getCount();
	}
}