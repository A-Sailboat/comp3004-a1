package core;

import java.util.Scanner;

public class Dealer extends Player{
	
		
		
	public Dealer() {}

	public String decideTurn(Scanner reader) {
		String response = "S";
		
		if(hand.value() <= 16) {response = "H";}
		if(hand.value() == 17 && hand.hasFullAce()) {response = "H";}
		if(hand.size() == 2 && hand.getHand().get(0).getRank() == hand.getHand().get(1).getRank()) {response = "S";}
		return response;
	}
	
	public void draw(Deck aDeck) {
		Card aCard = aDeck.draw();
		if(hand.size() == 0) {aCard.setVisible(false);};
		
		hand.add(aCard);
		
	}

	public void draw(Deck aDeck,Card aCard) {
		Card newCard = aDeck.draw();
		if(hand.size() == 0) {newCard.setVisible(false);}

		hand.add(newCard);
		
	}
		
		
}
