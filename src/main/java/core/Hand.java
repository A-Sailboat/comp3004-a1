package core;

import java.util.ArrayList;
import java.util.List;

public class Hand  {
	
	private ArrayList<Card> hand = new ArrayList<Card>();

	public Hand() {}
	public Hand(ArrayList<Card> hand ) {this.hand = hand;}
	public List<Card> getHand() {return hand;}
	
	private final String[] SUITS = { "Diamonds","Clubs", "Hearts", "Spades"};
	private final String[] RANKS = { "Ace","2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", };
	
	public int numHiddenCards() {
		int count = 0;
	
		for(Card card: hand) {
			if (!card.isVisible()) {count++;}
			}
		return count;
	}
	
	public void add(Card aCard) {
		if (aCard.isAce() && this.value() + 11 > 21) {aCard.setPoints(1);}
		hand.add(aCard);
		
		if (this.value() > 21) {
			for(Card card: hand) {
				if(card.isAce() && card.getPoints() == 11) {
					card.setPoints(1);
				}
			}
		}
	}
	
	
	public boolean hasFullAce() {
		for(Card card: hand) {
			if(card.isAce() && card.getPoints() == 11) {
				return true;
			}
		}
		return false;
	}
	
	public int value() {
		
		int score = 0;
		for(Card card: hand) {
			score += card.getPoints();
		}
		return score;
	}
	
	public int size() {
		return hand.size();
	}
	public boolean couldSplit() {
		
		if ((hand.get(0).getRank().equals(hand.get(1).getRank()))){return true;}
		else {
			return false;
		}
	}
	public String toString() {
		String cards = "";
		for(Card card: hand) {
			cards += card.toString()+ ", ";
		}
		cards += " ("+value()+")";
		return cards;
	}

	
	
}



