///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// Files:            WAL.java
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
 *  The WAL class represents the state before any operation performed. 
 *  This is used to undo and redo any operation. 
 *  For example, user1 changes the value of table[i][j] from a to b, 
 *  then we need to create a WAL object to record which cell is changed, 
 *  i.e. row index is i and col index is j, and what the previous value is, 
 *  i.e. a. Later on, if the user undoes this operation, 
 *  we can restore this cell based on this WAL object.
 * <p>Bugs: None known
 *
 * Han Jiang & You Wu
 */

public class WAL {
	//Data field
	private int rowIndex;
	private int colIndex;
	private int oldValue;

	public WAL(int rowIndex, int colIndex, int oldValue) {
		// throws IllegalArgumentException 
		//if any argument is invalid.
		if(rowIndex<0||colIndex<0){
			throw new IllegalArgumentException();
		}
		//Constructor
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.oldValue = oldValue;
	}

	/**
	 * 	Returns the old value.
	 * @return int oldvalue
	 */
	public int getOldValue() {
		return this.oldValue; 
	}

	/**
	 * 	Returns the row index.
	 * @return int rowindex
	 */
	public int getRowIndex() {
		return this.rowIndex; 
	}

	/**
	 * 	Returns the col index.
	 * @return int colindex
	 */
	public int getColIndex() {
		return this.colIndex;
	}

}
