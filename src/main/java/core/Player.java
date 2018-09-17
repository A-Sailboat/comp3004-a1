package core;

import java.util.ArrayList;
import java.util.List;

public class Player  {
	
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	private boolean isDealer;
	
	public Player() {
		
	}
	
	public Player(boolean isDealer) {this.isDealer = isDealer;}
	
	public void addHand (Hand aHand) {hands.add(hands.size(),aHand);}
	
	public boolean isDealer (Hand aHand) {return isDealer;}
	
	public List<Hand> getHands() {return hands;}
	
	
}


