///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// Files:            SimpleStack.java
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
 * An ordered collection of items, where items are both added
 * and removed from the top.
 * @author CS367
 */
public class SimpleStack<E> implements StackADT<E> {
	//Data field
	private Listnode<E> head;
	private int numItems;

	public SimpleStack() {
		//Constructor(use singly linked list)
		numItems = 0;  
	}

	/**
	 * Adds item to the top of the stack.
	 * @param item the item to add to the stack.
	 * @throws IllegalArgumentException if item is null.
	 */
	public void push(E item) {
		if(item == null){
			throw new IllegalArgumentException();
		}
		Listnode<E> newnode = new Listnode<E>(item);
		if(head == null){
			head = newnode;
		}
		else{
			newnode.setNext(head);
			head = newnode;
		}
		numItems++;//increment the size of list
	}

	/**
	 * Removes the item on the top of the Stack and returns it.
	 * @return the top item in the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		if (isEmpty()) 
			throw new EmptyStackException(); 
		E tmp = head.getData();             
		head =  head.getNext();             
		numItems--;                        
		return tmp;     
	}

	/**
	 * Returns the item on top of the Stack without removing it.
	 * @return the top item in the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E peek() {
		if (isEmpty()) 
			throw new EmptyStackException(); 
		E tmp = head.getData();  
		return tmp;     
	}

	/**
	 * Returns true iff the Stack is empty.
	 * @return true if stack is empty; otherwise false.
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Removes all items on the stack leaving an empty Stack. 
	 */
	public void clear() {
		head = null;
		numItems =0; 
	}

	/**
	 * Returns the number of items in the Stack.
	 * @return the size of the stack.
	 */
	public int size() {
		return numItems;
	}
}
