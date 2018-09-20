package core;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Casino {
	private Deck deck = new  Deck();
	private Player player = new Player();
	private Dealer dealer = new Dealer();
	
	private Player[] playerTurn = new Player[2];
	private Player[] dealerTurn = new Player[2];
	private int commandNum = 0;
	private String[] commands;
	private Player winner;
	private static Scanner reader = new Scanner(System.in);
	
	public Casino() {
		
	}
	public Player getPlayer() {return player;}
	public Dealer getDealer() {return dealer;}
	public Player getWinner() {return winner;}
	
	public String toString() {
		String gameState = "Player has "+ player.toString() +"\n"+ "Dealer has " + dealer.toString();
		return gameState;
	}
	
	public int runGame(String gameType) {
		try {
		playerTurn[0] = player;
		dealerTurn[0] = dealer;
		

		if (gameType.equals("c")|| gameType.equals("C")) {
		
			player.draw(deck);
			player.draw(deck);
			
			dealer.draw(deck);
			dealer.draw(deck);
			System.out.println(this.toString());
			if(dealer.hasBlackJack() || player.hasBlackJack()) {determineWinner();}
			
			else {
				for(int i = 0; i<2; i++) {
					if(playerTurn[i] != null) {
						System.out.println(i);
						int bust = resolveTurn(playerTurn[i], playerTurn[i].decideTurn(reader),deck);
						if(bust == -1 ) {i+=5;}
						System.out.println(this.toString());
					}
				}
				for(int i = 0; i<2; i++) {
					if(dealerTurn[i] != null) {
						int bust = resolveTurn(dealerTurn[i], dealerTurn[i].decideTurn(reader),deck);
						if(bust == -1 ) {i+=5;}

					}
				}
			}
		
			determineWinner();
			
			
		}
		
		if (gameType.equals("f") || gameType.equals("F")) {
			Deck aDeck = new Deck();
			
			System.out.println("Enter the file name");
			String selection = reader.next();
			File file = new File(selection);
			try {
				Scanner sc = new Scanner(file);
				String commandLine =  sc.nextLine();
				//System.out.println(commandLine);
				String[] commands = commandLine.split("\\s+");
				System.out.println(commands[0]);
					playerTurn[0] = player;
					dealerTurn[0] = dealer;
					
					
						player.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						player.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						
						dealer.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						dealer.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						
						System.out.println(this.toString());
						if(dealer.hasBlackJack() || player.hasBlackJack()) {determineWinner();}
						
						else {
	
							resolveTurn(player, commands[commandNum],deck,true);
							System.out.println(this.toString());

							resolveTurn(dealer, commands[commandNum],deck,true);
								
							}
						
			determineWinner();
			return 0;
			}catch(Exception e) {
				System.out.print("Error");
			}
			
			
		}
		
		return 0;
		}
		catch(Exception e){
			return -1;
		}
	}
	public int runGame(String gameType, String fileName) {
		try {
		playerTurn[0] = player;
		dealerTurn[0] = dealer;
		
		
		if (gameType.equals("f") || gameType.equals("F")) {
			Deck aDeck = new Deck();
			
			
			File file = new File(fileName);
			try {
				Scanner sc = new Scanner(file);
				String commandLine =  sc.nextLine();
				//System.out.println(commandLine);
				String[] commands = commandLine.split("\\s+");
				System.out.println(commands[0]);
					playerTurn[0] = player;
					dealerTurn[0] = dealer;
					
					
						player.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						player.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						
						dealer.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						dealer.draw(deck,new Card(commands[commandNum]));
						commandNum++;
						
						System.out.println(this.toString());
						if(dealer.hasBlackJack() || player.hasBlackJack()) {determineWinner();}
						
						else {
	
							resolveTurn(player, commands[commandNum],deck,true);
							System.out.println(this.toString());

							resolveTurn(dealer, commands[commandNum],deck,true);
								
							}
						
			determineWinner();
			return 0;
			}catch(Exception e) {
				System.out.print("Error");
			}
			
			
		}
		
		return 0;
		}
		catch(Exception e){
			return -1;
		}
	}
	private void determineWinner() {
		/*System.out.println(this.toString());
		int bestHandPlayer =0;
		int bestHandDealer =0;
		boolean playerBust = false;
		boolean dealerBust = false;
		
		for(int i = 0; i<4; i+=2) {
			if (turnOrder[i] != null) {
				if (turnOrder[i].getHand().value() <= 21 && turnOrder[i].getHand().value() > bestHandPlayer ) {
					bestHandPlayer = turnOrder[i].getHand().value();
					
				}
			}
		}
		
		for(int i = 1; i<4; i+=2) {
			if (turnOrder[i] != null) {
				if (turnOrder[i].getHand().value() <= 21 && turnOrder[i].getHand().value() > bestHandDealer ) {
					bestHandDealer = turnOrder[i].getHand().value();
				}
			}
		}
		System.out.println("Dealer Best:"+bestHandDealer);
		System.out.println("Player Best:"+bestHandPlayer);
		System.out.println(this.toString());
		if(bestHandPlayer <= bestHandDealer) {
			winner = dealer;
			if (bestHandPlayer ==0) {System.out.println("Player Busts");}
			System.out.println("Dealer wins with "+ bestHandDealer);
		}else {
			if (bestHandDealer ==0) {System.out.println("Dealer Busts");}
			System.out.println("Player wins with " + bestHandPlayer);
		*/
		dealer.getHand().getHand().get(0).setVisible(true);
		System.out.println(this.toString());
		if(player.getHand().value() > 21 ) {
			System.out.println("Player Busts");
			winner = dealer;
		}else if(dealer.getHand().value() > 21 ) {
			System.out.println("Dealer Busts");
			winner = player;
		}else if(dealer.getHand().value() >= player.getHand().value()) {
			System.out.println("Dealer Wins");
			winner = dealer;
		}else {
			System.out.println("Player Wins");
			winner = player;
		}
		
		
	}
	
	
	public int resolveTurn(Player player, String selection,Deck aDeck) {

		if (selection  == "H") {
			player.draw(aDeck);
			if (player.isBust()) {return -1;}
			System.out.println(this.toString());
			resolveTurn(player, player.decideTurn(reader), aDeck);
			
		}
		else if (selection  == "S") {
			return 0;
			
		}
		else if(selection  == "D") {
			if(player instanceof Dealer) {
				Dealer dealerTwo = new Dealer();
				dealerTwo.getHand().add(dealer.split());
				dealerTurn[1] = dealerTwo;
				resolveTurn(player, player.decideTurn(reader), aDeck);
			}else {
				Player playerTwo = new Player();
					playerTwo.getHand().add(player.split());
					playerTurn[1] = playerTwo;
					resolveTurn(player, player.decideTurn(reader), aDeck);
			}
		}	
		return -1;
		}
		
	public int resolveTurn(Player player, String selection,Deck aDeck,boolean isFile) {
		commandNum++;
		if (selection  == "H") {
			player.draw(aDeck,new Card(commands[commandNum]));
			commandNum++;
			if (player.isBust()) {return -1;}
			System.out.println(this.toString());
			resolveTurn(player, player.decideTurn(reader), aDeck);
			
		}
		else if (selection  == "S") {
			return 0;
			
		}
		else if(selection  == "D") {
			if(player instanceof Dealer) {
				Dealer dealerTwo = new Dealer();
				dealerTwo.getHand().add(dealer.split());
				dealerTurn[1] = dealerTwo;
				resolveTurn(player, player.decideTurn(reader), aDeck);
			}else {
				Player playerTwo = new Player();
					playerTwo.getHand().add(player.split());
					playerTurn[1] = playerTwo;
					resolveTurn(player, player.decideTurn(reader), aDeck);
			}
		}	
		return -1;
		}
	

}
