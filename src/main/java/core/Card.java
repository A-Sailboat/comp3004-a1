package core;

public class Card  {
	
	private char rank;
	private char suit;
	private boolean visible;
	
	public Card(char suit, char rank) {
		this.suit = suit;
		this.rank = rank;
	}
	public Card(String twoCardIdentifier) {
	
	}
	
	
	public int getRank() {return rank;}
	public int getSuit() {return suit;}
	public Card setVisible(boolean visible) {
		this.visible = visible;
		return this;
	}
	public boolean isVisible() {return visible;}
	
}

