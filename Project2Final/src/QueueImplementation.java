import java.util.ArrayList;

public class QueueImplementation {
	ArrayList<Position> PositionArray = new ArrayList<Position>();
	//Position[] PositionArray = new Position[2];
	int FirstElementIndex = 0;
	int LastElementIndex = 0;
	
	QueueImplementation(){
		//Arrays.fill(PositionArray,null);
		FirstElementIndex = 0;
		LastElementIndex = 0;
	}
	
	void add(Position pos) {
		PositionArray.add(pos);
		LastElementIndex++;
	}
	
	Position get (int Index) {
		//check if null or not
		return PositionArray.get(Index);
	}	
	
	Position peek() {
		//check if null or not
		return PositionArray.get(FirstElementIndex);
//		if(PositionArray.get(FirstElementIndex)==null){
//		}
	}
	
	void remove() {
		//check if null or not
		PositionArray.set(FirstElementIndex, null);
		FirstElementIndex++;
	}
	
	boolean contains(Position pos) {
		
		for (int i=LastElementIndex-1 ; i>=0; i--) {
			if ((pos.row == PositionArray.get(i).row)&&(pos.column == PositionArray.get(i).column)) {
				return true;
			}
		}
		return false;
	}
}



//Make an array list of object or LinkedList of Objects
//Make an object Position that stores Row and Column
//Put that Object in the node either with LL or AL
/**
 * *this.row = row;
this.column = column;

 */
















/***
 * Methods in queue:
 * 
 */

/**
 * Queue is a First in First out
 * 
 * /




/**
 * Make a queue that holds two integers row and column
 * constructor make a object
 * a queue of object
 * need to learn how to do this
 * 
 * METHOD:
 * ADD
 * REMOVE
 * 
 */
 

/**
 * Basically Bruteforcing
 * Can store every in all possible directions
 * when result is met, I can check if it went all valid way
 * if it went valid way create a neew one
 * 
 */
