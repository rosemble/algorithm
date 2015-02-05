import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	
	private int N;
	private Node first;
	private Node last;
	
    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }
    
    
	public Deque()                           // construct an empty deque
	{
		first = null;
		last = null;
		N = 0;
		
	}
	public boolean isEmpty()                 // is the deque empty?
	{
		return first == null;
	}
	
	public int size()                        // return the number of items on the deque
	{
		return N;
	}
	public void addFirst(Item item)          // insert the item at the front
	{
		if (item == null) throw new java.lang.NullPointerException();
		
		Node oldFirst = first;
		first = new Node();
		
		first.item = item;
		first.next = null;
		
		if ( isEmpty())
			last = first;
		else 
			first.next = oldFirst;
		N++;
	}
	
	public void addLast(Item item)          // insert the item at the end
	{
		if (item == null) throw new java.lang.NullPointerException();
		
		Node oldFirst = first;
		first = new Node();
		
		first.item = item;
		first.next = null;
		
		if ( isEmpty())
			last = first;
		else 
			first.next = oldFirst;
		N++;
	}
	
	public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
        //assert check();
    }
	
	public Item removeFirst()                // delete and return the item at the front
	{
		return null;
	}
	public Item removeLast()                 // delete and return the item at the end
	{
		return null;
	}
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
