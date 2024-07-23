package application;
import java.util.ArrayList;

public class NotationQueue<T>implements QueueInterface<T> {
	private ArrayList<T> queue;
	private int size;
	
	public NotationQueue() {
		this.size=100;
				this.queue = new ArrayList<>(100);
	}
	
	public NotationQueue(int size) {
		this.size = size;
		this.queue = new ArrayList<>(size);
	}
	public NotationQueue(ArrayList<T> newArray) {
		this.size = newArray.size();
		this.queue = new ArrayList<>(size);
		queue.addAll(newArray);
	}
	
	public T dequeue() throws QueueUnderflowException{
		if(isEmpty()==true) {
			throw new QueueUnderflowException();
		}
		else {
			T temp = queue.get(0);
			queue.remove(0);
			return temp;
		}
	}
	

	public boolean isEmpty() {
		if(queue.isEmpty()!=true)
			return true;
		else 
			return false;
	}
	
	public int size() {
		return queue.size();
	}
	

	public boolean enqueue(Object e) throws QueueOverflowException{
		if(isFull()==true)
			throw new QueueOverflowException();
		else {
			queue.add((T)e);
			return true;
		}
	}
	
	@Override
	public void fill(ArrayList <T> list) {
			ArrayList<T> copied = new ArrayList<>(list);
			
			for(int i=0;i< copied.size();i++) 
			{ 
			  queue.add(copied.get(i));
			}
	}
	@Override
	public boolean isFull() {
		return false;
	}
	
	@Override
	public String toString() {
		String message = "";
		for (int i=0; i<queue.size(); i++) {
			message
			+=queue.get(i);
		}
		
		return message;
	}
	@Override
	public String toString(String delimiter) {
		String message = "";
		for(int i=0; i<queue.size(); i++) {
			if(i==queue.size()-1)
				message+=queue.get(queue.size()-1);
			else
				message+=queue.get(i)+delimiter;
		}
		return message;
		}

}
