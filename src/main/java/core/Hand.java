package core;

import java.util.ArrayList;
import java.util.List;

public class Hand  {
	
	private ArrayList<Card> hand = new ArrayList<Card>();

	public Hand() {}
	public Hand(ArrayList<Card> hand ) {this.hand = hand;}
	public List<Card> getHand() {return hand;}
	
	public int numHiddenCards() {
		
		
	}
	
	
}



