/**
 * This class represents a card. It combines the Rank and Suit enum to make a card.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Oct 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #3
 * @section 06
 */
package proj3;

public class Tableau {

	private Deck deck;
	private Card[][] tableau;
	public final int ROWS , COLS;

	/**
	 * Initializing a 2D array of cards as a tableau.
	 * @param row
	 * @param col
	 */
	public Tableau (int row, int col) {

		ROWS = row;    COLS = col;

		deck = new Deck();
		deck.shuffleCard(12345); 

		tableau = new Card[ROWS][COLS];

		int z = 0; //This variable is used to get cards from the deck which is an array of cards.
		
		for (int i = 0; i < ROWS; i++) {
			
			for (int j = 0; j < COLS; j++){

				tableau[i][j] = deck.getCard(z); 	
				z++;
			}  
		}
	}

	/**
	 * Returning a card from the tableau
	 * @param r = row number
	 * @param c = column number
	 */
	public Card getCard (int r, int c) {

		Card card;
		card = tableau[r][c];

		return card;	  
	}

	/**
	 * Returning the rank of the card
	 */
	public Rank getRank (int i, int j) {

		Rank rank = tableau[i][j].getRank();

		return rank;
	}

	/**
	 * Returning the suit of the card
	 */
	public Suit getSuit (int i, int j){

		Suit suit = tableau[i][j].getSuit();

		return suit;
	}

	/**
	 * Returning a string representation of the tableau.
	 */
	public String toString () {

		String str = "";
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {

				str += getCard (i , j) + "  "; 

			}
			str += "\n";
		}    

		return str;
	}

	public static void main (String[] args) {

		Tableau  tabl = new Tableau(5,5);
		System.out.println (tabl);

	}
}
