package core;

public class Card  {
	
	private String rank;
	private String suit;
	
	private int points;
	
	private boolean visible;
	private boolean isAce;
	
	private final String[] SUITS = { "Diamonds","Clubs", "Hearts", "Spades"};
	private final String[] RANKS = { "Ace","2", "3", "4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", };
	private final String[] RANKIDS = { "A","2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K", };
	
	
	public Card(String twoCardIdentifier) {
		visible = true;
		//System.out.print(twoCardIdentifier);
		for(int i = 0; i<4; i++) {
			if(twoCardIdentifier.charAt(0) == SUITS[i].charAt(0)) {suit = SUITS[i];}
		}
		
		for(int q= 0; q<13; q++) {
			
			if(twoCardIdentifier.substring(1).equals(RANKIDS[q])) {rank = RANKS[q];}
			//System.out.println(twoCardIdentifier.substring(1)+" and "+RANKIDS[q] +" = "+ rank);
		}
		//System.out.print(rank + " " + suit);
		if (rank == "Ace") {
			isAce = true;
		    points = 11;
		}
		else if(rank == "Jack" || rank == "Queen" || rank == "King") {points = 10;}
		
		else {points = Integer.parseInt(rank);}
	}

	public String getRank() {return rank;}
	public String getSuit() {return suit;}
	public int getPoints() {return points;}
	
	public void setPoints(int points) {this.points = points;}
	
	public Card setVisible(boolean visible) {
		this.visible = visible;
		return this;
	}
	
	public boolean isVisible() {return visible;}

	@Override
	public final String toString(){
		if(!visible) {return " an hidden card";}
		else {return this.getRank() +" of "+ getSuit();}
	}
		 
	public boolean isAce() {
		return isAce;
	}
	
}



