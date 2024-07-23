package application;

import java.util.ArrayList;
import java.util.Collection;

public class NotationStack<T> implements StackInterface<T>{

	private int size; 
	private T[] data;
	private int topIndex;

	@SuppressWarnings("unchecked")

	public NotationStack(){	
		size=100;
		data = (T[]) new Object[size];
		topIndex=-1;
	}
	@SuppressWarnings("unchecked")
	/**
	 * @param element arraylist to copy to stack
	 */
	public NotationStack(ArrayList<T> element){
		this.size= element.size();
		T[] temp = (T[]) new Object[size];
		for(int i=0;i<element.size();i++) {
			temp[i]=element.get(i);
		}
		data=temp;
		topIndex=size-1;
	}
	
	public NotationStack(int size){	
		this.size = size;
		T[] temp = (T[]) new Object[size];
		data=temp;
		topIndex=-1;
	}
	/**
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		if(size()== size) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isEmpty() {
		if(topIndex<0) {
			return true;
		}
		else
		return false;
	}
	
	/**
	 * @throws StackUnderflowException stack is empty
	 */
	public T top() throws StackUnderflowException {
		if(isEmpty()==false) {
			return data[topIndex];
		}
		else 
			throw new StackUnderflowException("");
	}
	
	public int size() {
		return (topIndex+1);
	}
	/**
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if(isEmpty()==false) {
			T temp = data[topIndex];
			data[topIndex]=null;
			topIndex--;
			return temp;
		}
		else throw new StackUnderflowException("");
		
	}

	/**
	 * @throws StackOverflowException if the stack is full
	 */
	@SuppressWarnings("unchecked")
	public boolean push(Object e) throws StackOverflowException {
	
		if(isFull()==false) {
			data[topIndex+1]= (T) e;
			topIndex++;
			return true;
		}
		else
			throw new StackOverflowException();

		}
	
	/**
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String message = "";
		for (int i =0; i < size();i++) {
			message+=data[i];
		}
		return message;
	}

	/**
	 * @return string representation of the Stack from bottom to top with elements 
	 */
 public String toString(String delimiter) {
		
		String message = "";
		for (int i =0; i < size()-1;i++) {
			message+=data[i]+delimiter;
		}
		message+=data[size()-1];
		return message;
	}
}