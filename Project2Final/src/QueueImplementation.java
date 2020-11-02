import java.util.ArrayList;
/**	
 * QueueImplementation.java
 * Project 2
 * 
 * This file includes a program for constructing a queue
 *   
 * @author Sanskar Lamsal
 * EECS 2500 - Linear Data Structure in Java
 * Dr. Gerald R. Heuring
 */
public class QueueImplementation {
	ArrayList<Position> PositionArray = new ArrayList<Position>();
	int FirstElementIndex = 0;
	int LastElementIndex = 0;
	
	QueueImplementation(){
		FirstElementIndex = 0;
		LastElementIndex = 0;
	}
	/**
	 * enqueue adds item into the queue
	 * @param pos
	 */
	void enqueue(Position pos) {
		PositionArray.add(pos);
		LastElementIndex++;
	}	
	
	Position peek() {
		return PositionArray.get(FirstElementIndex);
	}
	
	/**
	 * Checks if the queue is empty
	 * @return true if the queue is empty
	 */
	boolean isEmpty() {
		if(FirstElementIndex == LastElementIndex) {
			return true;
		}
		return false;
	}
	/**
	 * Removes the head of the queue
	 * @return the head of the queue
	 */
	Position dequeue() {
		return PositionArray.get(FirstElementIndex++);
	}
	/**
	 * Checks if the queue contains the position parameter
	 * @param pos
	 * @return true if it contains
	 */
	boolean contains(Position pos) {
		
		for (int i=LastElementIndex-1 ; i>=0; i--) {
			if ((pos.row == PositionArray.get(i).row)&&(pos.column == PositionArray.get(i).column)) {
				return true;
			}
		}
		return false;
	}	
}