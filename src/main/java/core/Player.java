package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player  {
	
	protected Hand hand = new Hand();
	public Player() {

	}
	
	public Hand getHand() {return hand;}
	public void setHand() {this.hand = hand;}

	public void draw(Deck aDeck) {
		Card aCard = aDeck.draw();
		hand.add(aCard);	
	}

	public void draw(Deck aDeck,Card aCard) {
		hand.add(aDeck.draw(aCard));
		
	}
	
	
	public int numHiddenCards() {
		int count = 0;
		for(Card card: hand.getHand()) {
			if(card.isVisible() == false) {count++;}
		}
		
		return count;
	}
	public String toString() {
		String handDescriptions ="";
		handDescriptions += hand.toString();
		return handDescriptions;
		
	}

	public boolean hasBlackJack() {
		if (hand.size() == 2 && hand.value() == 21) {return true;}
		return false;
	}
	public boolean isBust() {
		if (hand.value() > 21) {return true;}
		return false;
	}
	
	public Card split() {
		return hand.getHand().remove(0);
	}
	public String decideTurn(Scanner reader) {
		try {
			System.out.print("What would you like to do? Hit (H), Stand(S)");
			if (hand.couldSplit()) {System.out.println(", Split(D)");}
			String selection = reader.next();
			
			if(selection.equals("S")||selection.equals("s")) {return "S";}
			if(selection.equals("H")||selection.equals("h")) {return "H";}
			if(selection.equals("D")||selection.equals("d") && hand.couldSplit()) {return "D";}
			
		}catch(Exception e){
			System.out.println("An error occured, please try again");
			return this.decideTurn(reader);
		}
		return null;
	}

}


