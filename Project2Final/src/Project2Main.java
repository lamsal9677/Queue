/**	
 * Project2Main.java
 * Project 2
 * 
 * This file includes a program for finding the shortest step that a chess Knight might take given an intial position and
 * a final position
 *   
 * @author Sanskar Lamsal
 * EECS 2500 - Linear Data Structure in Java
 * Dr. Gerald R. Heuring
 */
import java.util.Scanner;
import java.util.Stack;

public class Project2Main {
	Position InitialPosition;
	Position FinalPosition;
	
	static QueueImplementation secondQueue;
	/**
	 * This method is responsible for taking initial and final position of the knight
	 * and computing the number of steps from the initial to the final position.
	 * @param StartingRow
	 * @param StartingColumn
	 * @param FinalRow
	 * @param FinalColumn
	 */
	void ShortestKnightSteps(int StartingRow, int StartingColumn, int FinalRow, int FinalColumn) {
		
		InitialPosition = new Position(StartingRow,StartingColumn);
		FinalPosition = new Position(FinalRow,FinalColumn);
		int[][] KnightSteps = {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
		int RowSum;
		int ColumnSum;
		Position AddedPosition = new Position(0,0,0,0);
		QueueImplementation queue = new QueueImplementation();
		secondQueue = new QueueImplementation();
		Position ToAddPosition;
		ToAddPosition = new Position(InitialPosition.row, InitialPosition.column, InitialPosition.row, InitialPosition.column);
		queue.enqueue(ToAddPosition);
		secondQueue.enqueue(ToAddPosition);
		Position currentPosition;
		int PreviousStepRow;
		int PreviousStepColumn;
		currentPosition = queue.dequeue();
		int a = 0;
		int totalElements = 1;
		
		while(a>=0) {
			PreviousStepRow = currentPosition.row;
			PreviousStepColumn = currentPosition.column;
			
			for(int i=0; i<=7; i++) {
				RowSum = (currentPosition.row)+KnightSteps[i][0];
				ColumnSum = (currentPosition.column)+KnightSteps[i][1];
				AddedPosition = new Position(RowSum,ColumnSum);
				
				if (isFinalPosition(AddedPosition)) {
					a = -2;
					ToAddPosition = new Position(AddedPosition.row, AddedPosition.column, PreviousStepRow, PreviousStepColumn);
					queue.enqueue(ToAddPosition);
					secondQueue.enqueue(ToAddPosition);
					totalElements++;
					break;
				}
				
				if (isValidPosition(AddedPosition)) {
					if (!secondQueue.contains(AddedPosition)) {
						ToAddPosition = new Position(AddedPosition.row, AddedPosition.column, PreviousStepRow, PreviousStepColumn);
						queue.enqueue(ToAddPosition);
						secondQueue.enqueue(ToAddPosition);
						totalElements++;
						}
				}
			}
			currentPosition = queue.dequeue();
		}

		reversequeue();
		
		QueueImplementation FinalQueue = new QueueImplementation();
		
		Position dequeued = secondQueue.dequeue();
		FinalQueue.enqueue(dequeued);
		int NumberofSteps = 1;
		int PreviousRow = dequeued.PreviousRow;
		int PreviousColumn = dequeued.PreviousColumn;
		int Length = totalElements;
		while(Length>1) {
			dequeued = secondQueue.dequeue();
			if ((PreviousRow == dequeued.row) && (PreviousColumn == dequeued.column)) {
				PreviousRow = dequeued.PreviousRow;
				PreviousColumn = dequeued.PreviousColumn;
				FinalQueue.enqueue(dequeued);
				NumberofSteps++;
			}
			Length--;
		}
		secondQueue = FinalQueue;
		reversequeue();
		
		System.out.println("Found Path");
		while(NumberofSteps>0) {
			Position x =secondQueue.dequeue();
			System.out.println(x.row +"," + x.column);
			NumberofSteps--;
		}	
	}

	/**
	 * isValid checks whether the Parameters lie within the range 0-7 or not and return the error if they are not 
	 * between 0 and 7.
	 * @param PositionRow
	 * @param PositionColumn
	 * @return true if the row and column are between 0-7. False otherwise
	 */
	boolean isValid(int PositionRow, int PositionColumn) {
		if(PositionRow < 0 || PositionRow > 7) {
			System.out.println("The row must be between 0 and 7. Try again.");
			return false;
		}
		if(PositionColumn < 0 || PositionColumn > 7) {
			System.out.println("The Column must be between 0 and 7. Try again.");
			return false;
		}				
		return true;
	}
	/**
	 * This method checks if the parameter is same as the Final Position or not
	 * @param pos 
	 * @return true if they are the same. False otherwise
	 */
	boolean isFinalPosition(Position pos) {
		if ((FinalPosition.row == pos.row) && (FinalPosition.column == pos.column)) {
			return true;
		}
		return false;
	}
	
	/**
	 * isValidPosition checks whether the parameter is a valid position i.e if the row and column of the position is
	 * between 0 and 7 or not. This is not same as isValid because it returns no error message
	 * @param pos
	 * @return
	 */
	boolean isValidPosition(Position pos) {
		if ((pos.column>=0)&&(pos.column<=7)&&(pos.row>=0)&&(pos.row<=7)) {
			return true;
		}
		return false;
	}
	/**
	 * This method is responsible for reversing the secondQueue when necessary. It uses Stack and Queue side by side to 
	 * perform the operation
	 */
	static void reversequeue() { 
        Stack<Position> stack = new Stack<>(); 
        while (!secondQueue.isEmpty()) { 
            stack.add(secondQueue.peek()); 
            secondQueue.dequeue(); 
        } 
        while (!stack.isEmpty()) { 
            secondQueue.enqueue(stack.peek()); 
            stack.pop(); 
        } 
    } 
	/**
	 * Main method is responsible for running the code and calling out the appropriate methods
	 * @param args
	 */
	public static void main(String[] args) {
		Project2Main project = new Project2Main();

		int[] Inputs = project.UserInput();
		int StartingRow = Inputs[0];
		int StartingColumn = Inputs[1];
		int FinalRow = Inputs[2];
		int FinalColumn = Inputs[3];
		project.ShortestKnightSteps(StartingRow, StartingColumn, FinalRow, FinalColumn);
	}
	/**
	 * UserInput is responsible to take valid Initial Position and Final Position from the user
	 * @return an array of integer with first 2 elements the initial position and last two elements the final position
	 */
	int[] UserInput() {
		Scanner stdin = new Scanner(System.in);
		int[] UserInputArray = new int[4];
		
		System.out.print("Input the starting row and column separated by a space: ");
		String InitialPositionInput = stdin.nextLine();
		String[] InitialPositionArray = InitialPositionInput.split(" ");
		
		int StartingRow = Integer.parseInt(InitialPositionArray[0]);
		int StartingColumn = Integer.parseInt(InitialPositionArray[1]);
	
		while(!isValid(StartingRow,StartingColumn)) {
			System.out.print("Input the starting row and column separated by a space: ");
			InitialPositionInput = stdin.nextLine();
			InitialPositionArray = InitialPositionInput.split(" ");
			StartingRow = Integer.parseInt(InitialPositionArray[0]);
			StartingColumn = Integer.parseInt(InitialPositionArray[1]);
		}
		
		System.out.print("Input the final row and column separated by a space: ");
		String FinalPositionInput = stdin.nextLine();
		
		String[] FinalPositionArray = FinalPositionInput.split(" ");
		
		int FinalRow = Integer.parseInt(FinalPositionArray[0]);
		int FinalColumn = Integer.parseInt(FinalPositionArray[1]);
	
		while(!isValid(FinalRow,FinalColumn)) {
			System.out.print("Input the final row and column separated by a space: ");
			FinalPositionInput = stdin.nextLine();
			FinalPositionArray = FinalPositionInput.split(" ");
			FinalRow = Integer.parseInt(FinalPositionArray[0]);
			FinalColumn = Integer.parseInt(FinalPositionArray[1]);
		}
		stdin.close();
		
		UserInputArray[0] = StartingRow;
		UserInputArray[1] = StartingColumn;
		UserInputArray[2] = FinalRow;
		UserInputArray[3] = FinalColumn;
		
		return UserInputArray;
	}
}