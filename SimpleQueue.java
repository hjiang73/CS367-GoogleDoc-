///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// Files:            SimpleQueue.java
// Semester:         CS 367 Fall 2015
//
// Author:           Han Jiang
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
// Lab Section:      02
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     You Wu
// Email:            wu278@wisc.edu
// CS Login:         ywu
// Lecturer's Name:  Jim Skrentny
// Lab Section:      01
////////////////////////////////////////////////////////////////////////////////
/**
 * An ordered collection of items, where items are added to the rear
 * and removed from the front.
 */
public class SimpleQueue<E> implements QueueADT<E> {
	//Data field
	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;


	public SimpleQueue() {
		//Constructor(use singly linked list)
		numItems=0;

	}

	/**
	 * Adds an item to the rear of the queue.
	 * @param item the item to add to the queue.
	 * @throws IllegalArgumentException if item is null.
	 */
	public void enqueue(E item) {
		if(item == null){
			throw new IllegalArgumentException();
		}
		Listnode<E> newnode = new Listnode<E>(item);
		if(tail == null){
			tail = newnode;
			head = newnode;
		}
		else{
			tail.setNext(newnode);
			tail = newnode;
		}
		numItems ++;//increment the size of list

	}

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * @return the front item in the queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
	public E dequeue() {
		if (isEmpty()) 
			throw new EmptyStackException(); //TODO
		E tmp = head.getData();
		head = head.getNext();
		numItems --;
		return tmp;

	}

	/**
	 * Returns the item at front of the Queue without removing it.
	 * @return the front item in the queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
	public E peek() {
		if (isEmpty()) 
			throw new EmptyStackException(); 
		E tmp = head.getData();  
		return tmp;     
	}

	/**
	 * Returns true iff the Queue is empty.
	 * @return true if queue is empty; otherwise false.
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	public void clear() {
		head = null;
		tail = null;
		numItems = 0;
	}

	/**
	 * Returns the number of items in the Queue.
	 * @return the size of the queue.
	 */
	public int size() {
		return numItems;
	}
}
