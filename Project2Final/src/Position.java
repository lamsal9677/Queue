/**	
 * Position.java
 * Project 2
 * 
 * This file includes a program for making a Position object that stores Row, Column, Previous row and Previous column
 *   
 * @author Sanskar Lamsal
 * EECS 2500 - Linear Data Structure in Java
 * Dr. Gerald R. Heuring
 */
public class Position{
	int row;
	int column;
	int PreviousRow;
	int PreviousColumn;
	
	/***
	 * Position is a constructor that assigns value to the object when there are 2 arguments
	 * @param row
	 * @param column
	 */
	Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	/**
	 * Position is a constructor that assigns value to the object when there are 4 arguments
	 * @param row
	 * @param column
	 * @param PreviousRow
	 * @param PreviousColumn
	 */
	Position(int row, int column, int PreviousRow, int PreviousColumn){
		this.row = row;
		this.column = column;
		this.PreviousRow = PreviousRow;
		this.PreviousColumn = PreviousColumn;
	}
}