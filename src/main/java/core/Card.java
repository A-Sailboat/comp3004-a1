package core;

public class Card  {
	
	private int rank;
	private int suit;
	private boolean visible;
	
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	public Card(String twoCharCardIdentifier) {
	
	}
	public int getRank() {return rank;}
	public int getSuit() {return suit;}
	public boolean isVisible() {return visible;}
	
}

