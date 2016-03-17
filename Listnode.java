
/**
 * Generic singly linked list node. It serves as the basic building block for 
 * storing data in singly linked chains of nodes.
 * 
 * <b>Do not modify this file in any way!</b>
 */
class Listnode<E> {
	private E data;                // data to be stored 
	private Listnode<E> next;   // connection to next node
	
	/**
	 * Constructs a new list node with no links to its next or previous node.
	 * @param data the data to be stored in this node
	 */
	Listnode(E data) {
		this(data, null);
	}
	
	/**
	 * Constructs a new list node with links to its next and previous.
	 * @param data the data to be stored in this node
	 * @param next the node after this one
	 */
	Listnode(E data, Listnode<E> next) {
		this.data = data;
		this.next = next;
		
	}

	/**
	 * Returns the current data.
	 * @return the current data
	 */
	E getData() {
		return data;
	}

	/**
	 * Returns the current next node.
	 * @return the current next node
	 */
	Listnode<E> getNext() {
		return next;
	}

	/**
	 * Sets the data to the given new value.
	 * @param data the new data
	 */
	void setData(E data) {
		this.data = data;
	}

	/**
	 * Sets the next node to the given new value.
	 * @param next the new next node
	 */
	void setNext(Listnode<E> next) {
		this.next = next;
	}
	
}