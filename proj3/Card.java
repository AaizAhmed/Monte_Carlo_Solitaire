 /**
  * This class represents a card. It combines the Rank and Suit enum to make a card.
  * @author Aaiz Ahmed <aaiza2@umbc.edu>
  * @version Oct 4, 2013
  * @project CMSC 202 - Fall 2013 - Project #2
  * @section 06
  */
package proj3;

public class Card {

	private Suit suit;
	private Rank rank;


	/**
	 * Constructor: Sets up a card object.
	 * @param suit
	 * @param rank
	 */
	public Card (Suit s, Rank r) {

		suit = s; 
		rank = r;
	}

	/**
	 * To access a suit.
	 * @return suit
	 */
	public Suit getSuit (){

		return suit;
	}

	/**
	 * To access a rank.
	 * @return rank
	 */
	public Rank getRank (){

		return rank;
	}
	/**
	 * Converting rank of a card to an integer so that cards can be compared. 
	 * @return integer value associated with rank.
	 */
	public int value () {
		
		int y;
		
		if (rank.getSymbol() == 'a'){
			return 14;
		}
		if (rank.getSymbol() == 'k'){
			return 13;
		}
		if (rank.getSymbol() == 'q'){
			return 12;
		}
		if (rank.getSymbol() == 'j'){
			return 11;
		}
		if (rank.getSymbol() == 't'){
			return 10;
		}
		else {
		 y = Character.getNumericValue (rank.getSymbol());
		}
		
		return y;
	}
	
	
	/**
	 * To Print a card 
	 * @return a string representing a card.
	 */
	public String toString (){

		return rank.getName() + " of " + suit.getName();
	}
  
	
//For unit testing to make sure this class and it's methods are executing correctly.  
public static void main (String [] args){

Card card = new Card (Suit.HEARTS, Rank.QUEEN);		
 
System.out.println (card + "\n" + card.value() ); 
	} 
}