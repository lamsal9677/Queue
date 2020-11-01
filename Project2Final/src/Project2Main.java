import java.util.Scanner;

public class Project2Main {
	Position InitialPosition;
	Position FinalPosition;
	
	public static void main(String[] args) {
		Project2Main project = new Project2Main();
		Scanner stdin = new Scanner(System.in);
		String InitialPositionInput;
		String[] InitialPositionArray;
		int StartingRow;
		int StartingColumn;
		String FinalPositionInput;
		String[] FinalPositionArray;
		int FinalRow;
		int FinalColumn;
		QueueImplementation AllValidStepsQueue = new QueueImplementation();
		int[][] KnightSteps = {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
		int RowSum;
		int ColumnSum;
		Position AddedPosition;
		int CurrentIndex;
		int LastElementIndex;
		QueueImplementation KnightShortestSteps = new QueueImplementation();
		int NumberofSteps;
		int CurrentElementIndex;
		
		
		System.out.print("Input the starting row and column separated by a space: ");
		InitialPositionInput = stdin.nextLine();
		
		InitialPositionArray = InitialPositionInput.split(" ");
		
		StartingRow = Integer.parseInt(InitialPositionArray[0]);
		StartingColumn = Integer.parseInt(InitialPositionArray[1]);
	
		while(!project.isValid(StartingRow,StartingColumn)) {
			System.out.print("Input the starting row and column separated by a space: ");
			InitialPositionInput = stdin.nextLine();
			InitialPositionArray = InitialPositionInput.split(" ");
			StartingRow = Integer.parseInt(InitialPositionArray[0]);
			StartingColumn = Integer.parseInt(InitialPositionArray[1]);
		}
		
		System.out.print("Input the final row and column separated by a space: ");
		FinalPositionInput = stdin.nextLine();
		
		FinalPositionArray = FinalPositionInput.split(" ");
		
		FinalRow = Integer.parseInt(FinalPositionArray[0]);
		FinalColumn = Integer.parseInt(FinalPositionArray[1]);
	
		while(!project.isValid(FinalRow,FinalColumn)) {
			System.out.print("Input the final row and column separated by a space: ");
			FinalPositionInput = stdin.nextLine();
			FinalPositionArray = FinalPositionInput.split(" ");
			FinalRow = Integer.parseInt(FinalPositionArray[0]);
			FinalColumn = Integer.parseInt(FinalPositionArray[1]);
		}
		stdin.close();
		project.InitialPosition = new Position(StartingRow,StartingColumn);
		project.FinalPosition = new Position(FinalRow,FinalColumn);
		
		
		AllValidStepsQueue.add(project.InitialPosition);
			
		CurrentIndex = 0;
		LastElementIndex = 0;
		while(CurrentIndex >= 0) {
			for(int i=0; i<=7; i++) {
				RowSum = (AllValidStepsQueue.get(CurrentIndex).row)+KnightSteps[i][0];
				ColumnSum = (AllValidStepsQueue.get(CurrentIndex).column)+KnightSteps[i][1];
				AddedPosition = new Position(RowSum,ColumnSum);
				
				if (project.isFinalPosition(AddedPosition)) {
					AllValidStepsQueue.add(AddedPosition);
					LastElementIndex++;
					CurrentIndex = -2;//stopping the while loop
					break;
				}
				
				if (project.isValidPosition(AddedPosition)) {
					if (!AllValidStepsQueue.contains(AddedPosition)) {
						AllValidStepsQueue.add(AddedPosition);
						LastElementIndex++;
					}
				}
			}
			CurrentIndex++;
		}

		
		KnightShortestSteps.add(AllValidStepsQueue.get(LastElementIndex));
		NumberofSteps = 0;
		CurrentElementIndex = LastElementIndex;//This is the current Element Index that can change(but not always)
		NumberofSteps++;
		for (int j=(CurrentElementIndex-1); j>=0; j--) {
			for (int i=0; i<=7; i++) {
				if (((AllValidStepsQueue.get(CurrentElementIndex).row) - (KnightSteps[i][0]))==(AllValidStepsQueue.get(j).row)
				&& (((AllValidStepsQueue.get(CurrentElementIndex).column) - (KnightSteps[i][1])) == (AllValidStepsQueue.get(j).column))){
					KnightShortestSteps.add((AllValidStepsQueue.get(j)));
					CurrentElementIndex = j;
					NumberofSteps++;
				}
			}
		}
		System.out.println("Found Path");
		for (int j = NumberofSteps-1; j>=0; j--) {
			System.out.println(KnightShortestSteps.get(j).row + "," + KnightShortestSteps.get(j).column);
		}
	}

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
	
	boolean isFinalPosition(Position pos) {
		Project2Main project = new Project2Main();
		if ((FinalPosition.row == pos.row) && (FinalPosition.column == pos.column)) {
			return true;
		}
		return false;
	}

	boolean isValidPosition(Position pos) {
		if ((pos.column>=0)&&(pos.column<=7)&&(pos.row>=0)&&(pos.row<=7)) {
			return true;
		}
		return false;
	}
}