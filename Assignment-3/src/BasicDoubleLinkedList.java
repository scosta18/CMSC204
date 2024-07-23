import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	 
	protected Node head = null;
	protected Node tail = null;
	protected int size = 0;
	
	
	
	@Override
	public ListIterator<T> iterator()  throws UnsupportedOperationException,NoSuchElementException {
		return new Iterator2();
	}
	
	/**
	 * 
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param data - the data for the Node within the linked list
	 * @return reference to the current object 
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data){
		
		if(tail==null && head ==null) {
			Node newNode = new Node(data,null,null);
			tail = newNode;
			head = tail;
		}
		else {
			Node newNode = new Node(data,null,tail);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return this;
		
	}
	/**
	 * @param data - the data for the Node within the linked list
	 * @return reference to the current object 
	 */
	public BasicDoubleLinkedList<T> addToFront(T data){

		Node newNode = new Node(data,null,null);
		if(head ==null && tail ==null) {
			head = newNode;
			tail = head;
		}
		else {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		}
		size++;
		return this;
		
	}
	
	/**
	 * @param targetData - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator ){
		Node choose = head;
		while(choose !=null) {
			
			if (comparator.compare(choose.data, targetData) == 0) {
				if(choose == head) {
					choose = choose.next;
					head = choose;
					choose.previous = null;
				}
				else if (choose == tail) {
					choose = choose.previous;
					tail = choose;
					choose.next = null;
				}
				else {
				choose.previous.next = choose.next;
				choose.next.previous = choose.previous;
				choose.next = null;
				choose.previous = null;
				choose = null;
				}
			}
			else
				choose = choose.next;
		}
		size--;
		return this;
	}
	
	/**
	 * It returns and do not remove the first element.
	 * Returns null when there's no element.
	 * @return - the data element or null
	 */
	public T getFirst() {
		return head==null? null: head.data;
	}
	
	/**
	 * It returns and do not remove the first element.
	 * Returns null when there's no element.
	 * @return - the data of the last element or null
	 */
	public T getLast(){
		return tail!=null? tail.data : null;
	}
	
	
	/**
	 * * It returns and do not remove the first element.
	 * Returns null when there's no element.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {

		T temp = head.data;
		
		head = head.next;
		head.previous = null;
		size--;
		
		return temp;
	
			
			
	}
	/**
	 * It returns and do not remove the first element.
	 * Returns null when there's no element.
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		T temp = tail.data;
		tail = tail.previous;
		tail.next = null;
		size--;
		return temp;
	}
	
	/**
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> myArray = new ArrayList<T>();
		Node choose = head;
		while(choose != null) {
			myArray.add(choose.data);
			choose = choose.next;
		}
		return myArray;
	}
	protected class Node{
		T data = null;
		Node next = null;
		Node previous = null;
	
	protected Node(T data, Node nextNode, Node previous) {
		this.data = data;
		next = nextNode;
		this.previous = previous;
	}
	
	
	protected Node getNext(){
		return next;
	}
	
	protected void setNext(Node nextNode) {
		next = nextNode;
	}
	protected Node getPrevious() {
		return previous;
	}
	
	protected void setPrevioius(Node pre) {
		previous = pre;
	}
	
	protected Node(T data) {
		this(data,null,null);
	}
	
	protected void setData(T data) {
		this.data = data;
	}
	}
	
	
	
	protected class Iterator2 implements ListIterator<T>{
		Node current;
		
		protected Iterator2() {
			current = new Node (null,head,null);
		}
		

		@Override
		public boolean hasNext() {

			return current.next
					!= null;
		}

		@Override
		public boolean hasPrevious() {
			return current.data != null;
		}
		
		
		@Override
		public T next()  {
			
			if (hasNext()) {
				current = current.next;
				return current.data;
			}
			else
				throw new NoSuchElementException ();

			
		}


		@Override
		public T previous() { 
			if (hasPrevious()) {
				T info = current.data; 
				if(current==head) {
					current = new Node(null,current,null);
				}else
					current = current.previous;
				return info;
			 }
			 else 
				 throw new  NoSuchElementException ();
		}
		
		 @Override
	        public void add(T e)throws UnsupportedOperationException {
	            throw new UnsupportedOperationException(); 
	        }
		 
		 @Override
			public void remove() throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
				
			}

		   @Override
	        public void set(T e)throws UnsupportedOperationException {
	            throw new UnsupportedOperationException(); 
	        }
		   
		   
		   @Override
	        public int previousIndex() throws UnsupportedOperationException {
	            throw new UnsupportedOperationException();
	        }

			
	        @Override
	        public int nextIndex() throws UnsupportedOperationException {
	            throw new UnsupportedOperationException();
	        }


	}
}