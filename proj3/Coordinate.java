/**
 * This class represents a card. It combines the Rank and Suit enum to make a card.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Oct 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #3
 * @section 06
 */
package proj3;

public class Coordinate {
	
	private int row, col;

	public Coordinate(int r, int c) {
	   row = r;
	   col = c;

	}

	public int getRow() {
		
		return row;
	}

	public int getColumn() {
		
		return col;
	}
	
public String toString () {
	
	String str = "";
	str += getRow() + " " + getColumn();
	
	return str;
}
	
	public static void main (String[] args) {
		
		Coordinate cord = new Coordinate(3,3);
		System.out.println (cord);
		
	}
}
