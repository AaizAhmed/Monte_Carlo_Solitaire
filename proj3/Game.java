/**
 * This class represents a card. It combines the Rank and Suit enum to make a card.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Oct 21, 2013
 * @project CMSC 202 - Fall 2013 - Project #3
 * @section 06
 */
package proj3;

public class Game {

	private int tRows, tCols, deckIndex, left = 27, score = 0;
	private long seed = 12345;
	private Deck deck;
	private Card[][] tableau;

	/** 
	 * Constructor of the game class takes 2 integers as argument for dimension of the tableau.
	 * @param tableauRows
	 * @param tableauCols
	 */
	public Game(int rows, int cols) {

		tRows = rows;
		tCols = cols;

		deck = new Deck();
		deck.shuffleCard(seed); 

		tableau = new Card[tRows][tCols];

		for (int i = 0; i < tRows; i++) {
			for (int j = 0; j < tCols; j++){
				
				if (deckIndex < 52) {
					
					tableau[i][j] = deck.getCard(deckIndex); 	
					deckIndex++;
				}
			}  
		}

	} 

	/**
	 * This method returns the suit of a card from a specified position if that position is not empty.
	 * @param coordinate
	 * @return suit of the card.
	 */
	public Suit getSuit(Coordinate coordinate) {

		if (tableau[coordinate.getRow()][coordinate.getColumn()] == null) {
			return null;
		}

		return tableau[coordinate.getRow()][coordinate.getColumn()].getSuit(); 
	}

	/**	
	 * This method returns the rank of a card from a specified position if that position is not empty.    
	 * @param coordinate
	 * @return rank of the card
	 */
	public Rank getRank(Coordinate coordinate) {

		if (tableau[coordinate.getRow()][coordinate.getColumn()] == null) {
			return null;
		}      

		return tableau[coordinate.getRow()][coordinate.getColumn()].getRank();
	}
	
	/**
	 * This methods tells if hint is implemented or not. 
	 * @return
	 */
	public boolean isHintImplemented() {

		return true;
	}
	
	/**
	 * This method moves cards forward in the tableau and fill empty spaces by taking remaining 
	 * cards from the deck
	 */
	public void consolidate() {

		Card[] consolidate = new Card[25];

		int c = 0;
		
		for ( int i = 0; i < 5; i ++) {
			for ( int j = 0; j < 5; j ++) {

				//Saving current cards with out a gap in a new array.
				//Some tableau positions will be empty so check for null.
				if ( tableau[i][j] != null) {
					consolidate[c] = tableau [i][j];
					c++;
				}
			}

		}

		//Filling the rest of the array with the cards from the deck.
		while ( c < 25) { 
			
			if (deckIndex < 52) {
				consolidate [c] = deck.getCard(deckIndex);
			}
			c++; deckIndex++;
		}

		
		int r = 0;
		for (int a = 0; a < tRows; a++) {
			for (int b = 0; b < tCols; b++){

				//Putting the consolidated cards back in the tableau array.
				tableau[a][b] = consolidate[r];  
				r++;
			} 
		}    
	}

	/**
	 * This method starts a new game and takes a long as a parameter for seed, to shuffle cards and set up the game.	
	 * @param gameNumber  
	 */
	public void newGame (long gameNumber) {
		
		seed = gameNumber;
		deck = new Deck(); 		
		deck.shuffleCard(gameNumber); 
		tableau = new Card[tRows][tCols];

		deckIndex = 0; score = 0;
		
		for (int i = 0; i < tRows; i++) {
			for (int j = 0; j < tCols; j++){
				
				if (deckIndex < 52) {
					tableau[i][j] = deck.getCard(deckIndex); 	
					deckIndex++;
				}
			}  
		}  

	}
	
	/**
	 * Returns the number of remaining cards in the deck. 
	 * @return remaining cards. 
	 */
	public int numberOfCardsLeft() {

		int remain = 0;

		if (left - score < 0) {
			
			remain = 0;
			return remain;
		}
		else {
			remain = left - score;
		}

		return remain;
	}  

	/**
	 * This method returns the Score of the player during the game. 	
	 * @return score
	 */
	public int getScore() {

		return score;
	}
	
	/**
	 * This method starts over the same game again.
	 */
	public void replay() {

		deck = new Deck(); 		
		deck.shuffleCard(seed); 
		tableau = new Card[tRows][tCols];

		deckIndex = 0; score = 0;
		
		for (int i = 0; i < tRows; i++) {
			for (int j = 0; j < tCols; j++){
				
				if (deckIndex < 52) {
					tableau[i][j] = deck.getCard(deckIndex); 	
					deckIndex++;
				}
			}  
		}  
	}

	/**
	 * This methods sorts through the tableau and tells if there are any cards that can be removed.
	 * @return cards that can be removed.
	 */
	public Coordinate[] getHint() {
		
		Coordinate [] cord = new Coordinate[2];

		// Horizontal hint
		for ( int i = 0; i < tRows; i++) {
			for ( int j = 0; j < tCols-1; j++) {

				if ((tableau[i][j] != null && tableau[i][j+1] != null)) {

					if (tableau[i][j].getRank() == tableau[i][j+1].getRank()) {
						cord[0] = new Coordinate(i,j);  cord[1] = new Coordinate(i,j+1);
						return cord;
					}
				}
			}
		}

		// Vertical hint
		for ( int i = 0; i < tRows-1; i++) {
			for ( int j = 0; j < tCols; j++) {

				if ((tableau[i][j] != null && tableau[i+1][j] != null)) {

					if (tableau[i][j].getRank() == tableau[i+1][j].getRank()) {
						cord[0] = new Coordinate(i,j);  cord[1] = new Coordinate(i+1,j);
						return cord;
					}
				}
			}
		}

		// Diagonal 1: Top left to Bottom right hint. 
		for ( int i = 0; i < tRows-1; i++) {
			for ( int j = 0; j < tCols-1; j++) {

				if ((tableau[i][j] != null && tableau[i+1][j+1] != null)) {

					if (tableau[i][j].getRank() == tableau[i+1][j+1].getRank()) {
						cord[0] = new Coordinate(i,j);  cord[1] = new Coordinate(i+1,j+1);
						return cord;
					}
				}
			}
		}

		// Diagonal 2: Bottom left to Top right hint.
		for ( int i = tRows-1; i > 0; i--) {
			for ( int j = 0; j < tCols-1; j++) {

				if ((tableau[i][j] != null && tableau[i-1][j+1] != null)) {

					if (tableau[i][j].getRank() == tableau[i-1][j+1].getRank()) {
						cord[0] = new Coordinate(i,j);  cord[1] = new Coordinate(i-1,j+1);
						return cord;
					}
				}
			}
		}  
		return null;
	}

	/**
	 * This method removes cards from specified coordinates. 
	 * @param coordinate
	 * @param coordinate2
	 * @return true or false
	 */
	public boolean removeCards(Coordinate coordinate, Coordinate coordinate2) {

		int w, x, y, z;

		w = coordinate.getRow(); x = coordinate.getColumn();
		y = coordinate2.getRow(); z = coordinate2.getColumn();


		   if ( (w == y && x + 1 == z) || (w == y && x - 1 == z) ||                // Horizontal check
				(w + 1 == y && x == z) || (w - 1 == y && x == z) ||                // Vertical check
				(w + 1 == y && x + 1 == z) || (w - 1 == y && x - 1 == z)||         // Diagonal 1 check
				(w + 1 == y && x - 1 == z) || (w - 1 == y && x + 1 == z) )         // Diagonal 2 check
		   {
				if	 (tableau[w][x].getRank() == tableau[y][z].getRank()) {
	
					//Removing both cards.
					tableau[w][x] = null;    tableau[y][z] = null;
					score += 2;
					return true;
				}

		   }
		return false;
	}

	/**
	 * This method returns true if player wins the game and false otherwise. 
	 * @return
	 */
	public boolean isWin() {

		if (getScore() == 52) {

			return true;
		}
		return false;
	} 
	
	/**
	 * This method returns a text describing the rules of the games. 	
	 * @return text explaining game.
	 */
	public String getHelpText() {

		String str = "";
		str += "Welcome to the game. To play the game click on the 2 cards \nthat have the same rank and are touching each other.\n";
		str += "Two cards are touching if they are next to each other \nhorizontally vertically, or diagonally.\n";
		str +=	"Cards at the top and bottom of a column, or the ends of a \nrow or the opposite corners of the diagonal are\n";
		str +=	"NOT considered touching. Which means, the columns, rows\nand diagonal do not wrap.\n";
		str += "If you click on 2 cards that have same rank and are touching\nthey will be removed and your score will be added by 2.\n" +
				"To win the game you have to earn a score of 52 hence you have\nto remove all the cards.\nTo fill up the empty " +
				"spaces use consolidate button.\nIt will move the cards and fill empty spaces with new cards from by remaining cards.";
		return str;
	}

	/**
	 * This methods prints out cards in the tableau.
	 */  
	public String toString () {
		
		String str = "";
		
		for (int i = 0; i < tRows; i++) {
			for (int j = 0; j < tCols; j++) {

				str += tableau[i][j] + "  "; 

			}
			str += "\n";
		}    

		return str;
	} 

	/*
	 * Main methods is being used to test the class throughly as it is being developed. 
	 */
	public static void main (String [] args) {

		Game game = new Game (5, 5);
		System.out.println (game);
		System.out.println (game.getRank(new Coordinate (3,3)) + " of " + game.getSuit(new Coordinate (3,3)) + "\n");
		System.out.println ("Score: " + game.getScore());
		System.out.println ("Cards left: " + game.numberOfCardsLeft());

		game.removeCards(new Coordinate (4,0), new Coordinate (4,1));
		game.removeCards(new Coordinate (1,1), new Coordinate (2,2));
		System.out.println (game);
		System.out.println ("Score: " + game.getScore());
		System.out.println ("Cards left: " + game.numberOfCardsLeft());

		game.removeCards(new Coordinate (2,3), new Coordinate (3,4));
		System.out.println ("Score: " + game.getScore());
		System.out.println ("Cards left: " + game.numberOfCardsLeft() +"\n");

		game.removeCards(new Coordinate (0,4), new Coordinate (1,3));
		System.out.println (game + "\n");
		System.out.println ("Score: " + game.getScore());
		System.out.println ("Cards left: " + game.numberOfCardsLeft() + "\n");

		System.out.println (game.isWin() + "\n"); 
		game.consolidate();
		System.out.println (game + "\n");

		System.out.println("12345 seed");
		game.newGame(12345);
		System.out.println(game);                                                                    //

		System.out.println("60295 seed");
		game.newGame(602958604);
		System.out.println(game);
		//	System.out.println(game.getHelpText());
		game.consolidate();
		System.out.println (game + "\n");

	}

} 
