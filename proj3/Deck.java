/**
 * This class represents a full deck of the 52 cards. It shuffles the cards and returns a string for the deck.
 * @author Aaiz Ahmed <aaiza2@umbc.edu> 
 * @project CMSC 202 - Fall 2013 - Project #3
 * @section 06
 * @created: Oct 4, 2013
 * @modified Dec 21, 2016
 */
package proj3;
import java.util.Random;

public class Deck {

	private Card [] everyCard;
	private final int MAX = 52;

	/**
	 * Constructor:	Instantiates the card array with all the 52 cards. 
	 */
	public Deck ( ) {

		everyCard = new Card [MAX];
		
		int i = 0;	
		for (Suit s : Suit.values()) {

			for (Rank r : Rank.values()) {

				Card card = new Card (s , r);

				everyCard[i] = card;

				i++;
			}

		}

	}

	/**
	 * This method shuffle the cards in the deck.	
	 * @param seed for Random class
	 */
	public void shuffleCard (long seed) {

		Random random = new Random (seed);

		Card temp ;

		for (int N = 51; N >= 0; N--) {

			int index = random.nextInt (N+1);

			temp = everyCard[N];
			everyCard[N] = everyCard[index];
			everyCard[index] = temp;
		}
	} 

	/**
	 * This method return a card from a specified index value.	
	 * @param i to specify from which position of array we want card
	 * @return chosen card
	 */
	public Card getCard (int i) {

		return everyCard[i];
	}

	/**
	 * Returns the string for all the cards.
	 */
	public String toString () {

		String str = "";

		for ( int i = 0; i < MAX; i ++) {

			str += everyCard[i] + "\n";
		}
		return str;
	}

	//For unit testing to make sure this class and it's methods are executing correctly. 

	public static void main (String [] args){

		Deck deck = new Deck() ;
		System.out.println (deck + "\nShuffled Deck\n");

		deck.shuffleCard(123456);

		System.out.println (deck);
		System.out.println (deck.getCard(7));

	}


}
