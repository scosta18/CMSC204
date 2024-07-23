import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	protected Comparator<T> comparator = null;
	
	/**
	 * Constructor - Creates an empty list linked to the given comparator.
	 * @param comparator2 - Comparator to compare data elements
	 */
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		comparator = comparator2;
	}
	
	/**
	 * @param data - data to be added
	 * @throws UnsupportedOperationException
	 */
	@Override
	 public BasicDoubleLinkedList<T> addToEnd(T data) {
	        throw new UnsupportedOperationException( "Invalid operation for sorted list");
	    }
	
	/**
	 * @return data element or null
	 * @param data - data element to remove
	 * @param comparator - the comparator to determine equality of data elements
	 */
	@Override
	public SortedDoubleLinkedList<T> remove (T data, Comparator<T> comparator) {
	        super.remove(data, comparator);
	        return this;
	    }
	
	/**
	 * @param data - data to be added
	 * @throws UnsupportedOperationException
	 */
	@Override 
	public BasicDoubleLinkedList<T> addToFront(T data) {
	        throw new UnsupportedOperationException("Invalid operation for sorted list");
	    }	 
	
	/**
	 * @return an iterator positioned at the head of the list
	 * @throws UnsupportedOperationException, NoSuchElementException
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
	        return super.iterator();
	    }
	 
	    
	/**
	 * @param data - the data to be added
	 * @return a reference to the current object
	 */
	
	 public SortedDoubleLinkedList<T> add(T data){
		Node element = new Node(data);
        
        if (size == 0) {
            head = tail = element;
        }else if (comparator.compare(tail.data, data) < 0) {
            tail.next = element;
            element.previous = tail;
            tail = element;
            }
        else if (comparator.compare(head.data, data) > 0) {
            head.previous = element;
            element.next = head;
            head = element;
       
        } else {
            Node current = head;
            while (current != null) {
                if (comparator.compare(current.data, data) <= 0) {
                    Node before = current;
                    Node after = current.next;
                    after.previous = before.next = element;
                    element.next = after;
                    element.previous = before;   
                }
                current = current.next;
            }
        }
        size++;
        return this;
    }	
	
	
}