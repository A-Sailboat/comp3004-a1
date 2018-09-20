package core;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> deck = new ArrayList<Card>(52);
	
	public final String[] SUITS = { "D","C", "H", "S"};
	public final String[] RANKS = { "A","2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K", };
	
	
	public Deck() {
		for(int i = 0; i<13; i++){
			for(int q = 0; q<4; q++){
				deck.add(new Card(SUITS[q]+RANKS[i]));
			}
		}
		Collections.shuffle(deck);
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	public Card draw() {
		Card drawnCard = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		
		return drawnCard;
		
	}
	public Card draw(Card aCard) {
		deck.remove(aCard);
		return aCard;
	}
	
	public int size() {return deck.size();}

}
