package core;

import java.util.ArrayList;
import java.util.List;

public class Player  {
	
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	public Player() {
		hands.add(0,new Hand());
	}
	
	public void addHand (Hand aHand) {hands.add(hands.size(),aHand);}
	
	public List<Hand> getHands() {return hands;}

	public Card draw(Deck aDeck) {
		// TODO Auto-generated method stub
		
	}
	public Card draw(Deck aDeck,Card aCard) {
		// TODO Auto-generated method stub
		
	}
	public void draw(Deck aDeck, int i) {
		// TODO Auto-generated method stub
		
	}
	
	public int numHiddenCards() {
		return null;
	}

	public Hand getHand(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasBlackJack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


