package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	
	public void testInitCardWithString() {
		Card diamondsJack  = new Card("DJ");
		Card heartsNine  = new Card("H9");
		assertEquals("Diamonds", diamondsJack.getSuit());
		assertEquals("Jack", diamondsJack.getRank());
		
		assertEquals('H', heartsNine.getSuit());
		assertEquals('9', heartsNine.getRank());
	}
	public void testDeckCount() {
		Deck aDeck  = new Deck();
		assertEquals(52, aDeck.size());
	}
	public void testFileGameRun() {
		Casino aCasino  = new Casino();
		assertEquals(0, aCasino.runGame("f"));
	}
	
	public void testGameRunLoad() {
		Casino aCasino  = new Casino();
		assertEquals(0, aCasino.runGame("c"));
	}
	
	public void testFileLoad() {
		Casino aCasino  = new Casino();
		assertEquals(0,aCasino.runGame("f", "hitTest.txt"));
	}
	
	public void shuffleTest() {
		Deck aDeck = new Deck();
		Deck bDeck = new Deck();
		
		aDeck.shuffle();
		bDeck.shuffle();
		
		assertNotSame(aDeck.draw(),bDeck.draw());
		
	}
	
	
	public void testPlayerCardVisiblity() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		
		aPlayer.draw(aDeck);
		aPlayer.draw(aDeck);
		assertEquals(0, aPlayer.numHiddenCards());

	}
	
	public void testDealerCardSemiVisiblity() {
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		aDealer.draw(aDeck);
		aDealer.draw(aDeck);
		
		assertEquals(1, aDealer.numHiddenCards());

	}
	
	public void testPlayerHit() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "hitTest.txt"));
		//game ran successfully
		assertEquals(2,aCasino.getPlayer().getHand().size());
		//Player hit at least once as they have 3 cards

	}
	
	public void testPlayerMultiHit() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "multiHitTest.txt"));
		//game ran successfully
		assertEquals(11,aCasino.getPlayer().getHand().size());
		//Player hit at nine times which is the max possible 
		//in blackjack
	}

	public void testPlayerStand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "standTest.txt"));
		//game ran successfully
		assertEquals(14,aCasino.getPlayer().getHand().value());
		//Both hands worth <20 and the game ended therefore
		//the player  must have stood.
		
	}
	public void testPlayerHandReveal() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "handReveal.txt"));
		//game ran successfully
		assertEquals(0,aCasino.getPlayer().numHiddenCards());
		//Player has/had no hidden cards
		
	}
	
	public void testPlayerBust() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "bustTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getDealer());
		//Dealer won and therefore the player lost
	}
	
	public void testDealerHitMin() {
		Scanner reader = new Scanner(System.in);
		Dealer aDealer = new Dealer();
		aDealer.getHand().add(new Card("C6"));
		aDealer.getHand().add(new Card("SK"));
	
		//hit on 16
		assertTrue("H" == aDealer.decideTurn(reader));
		
		Dealer bDealer = new Dealer();
		
		bDealer.getHand().add(new Card("C7"));
		bDealer.getHand().add(new Card("SK"));
		
		//hit on 17
		assertTrue("S" == aDealer.decideTurn(reader));
		
	}
	public void testDealerSoftHitMin() {
		Scanner reader = new Scanner(System.in);
		Dealer aDealer = new Dealer();
		
		aDealer.getHand().add(new Card("C6"));
		aDealer.getHand().add(new Card("SA"));
		assertEquals('S', aDealer.decideTurn(reader));
		//Hits on soft 17
	}
	
	public void testDealerMultiHit() {
		Scanner reader = new Scanner(System.in);
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		assertEquals("H", aDealer.decideTurn(reader));
		aDealer.getHand().add(aDeck.draw());
		assertEquals("H", aDealer.decideTurn(reader));
		aDealer.getHand().add(aDeck.draw());
		
	}
	public void testDealerHandReveal() {
		
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f","genericTest.txt"));
		assertEquals(0,aCasino.getDealer().numHiddenCards());
		//None of the  dealers cards are still concealed
		
	}		
	public void testDealerBust() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerBust.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getPlayer());
		//Dealer lost and therefore the player won
	}
	public void testAceAsOne() {
		Hand aHand = new Hand();
		aHand.add(new Card("S9"));
		aHand.add(new Card("C9"));
		assertEquals(18, aHand.value());
		aHand.add(new Card("SA"));
		assertEquals(19, aHand.value());
	}
	public void testAceAsEleven() {
		Hand aHand = new Hand();
		aHand.add(new Card("CK"));
		aHand.add(new Card("SA"));
		assertEquals(21, aHand.value());
	}
	public void testTwoAceHand() {
		Hand aHand = new Hand();
		aHand.add(new Card("SA"));
		aHand.add(new Card("CA"));
		assertEquals(12, aHand.value());
	}
	public void testAceElevenToOne() {
		Hand aHand = new Hand();
		aHand.add(new Card("S9"));
		aHand.add(new Card("CA"));
		assertEquals(20, aHand.value());
		aHand.add(new Card("H5"));
		assertEquals(15, aHand.value());
		
	}
	public void testMultiAceAsOne() {
		Hand aHand = new Hand();
		
		aHand.add(new Card("SA"));
		aHand.add(new Card("CA"));
		aHand.add(new Card("D10"));
		aHand.add(new Card("DA"));
		aHand.add(new Card("HA"));
		assertEquals(14,aHand.value());
		//drew all 4 aces ie max possible
	}
	public void testCourtIsTen() {
		Card jack = new Card("SJ");
		Card queen = new Card("HQ");
		Card king = new Card("HK");
		
		assertEquals(10,jack.getPoints());
		assertEquals(10,queen.getPoints());
		assertEquals(10,king.getPoints());
	}
	public void testPlayerBlackjackDetect() {
		Player aPlayer = new Player();
		aPlayer.getHand().add(new Card ("SA"));
		aPlayer.getHand().add(new Card ("SK"));
		
		assertTrue(aPlayer.hasBlackJack());
	}
	
	public void testDealerBlackjackDetect() {
		Dealer aDealer = new Dealer();
		aDealer.getHand().add(new Card ("SA"));
		aDealer.getHand().add(new Card ("SK"));
		
		assertTrue(aDealer.hasBlackJack());
	}
	public void testPlayerBlackjackWin() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "playerBlackjackWinTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getPlayer());
		//Player  was the winner
		assertTrue(aCasino.getPlayer().hasBlackJack());
		//player had blackjack
	}
	public void testDoubleBlackjackDealerTrumps() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "doubleBlackjackDealerTrumpsTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner().equals(aCasino.getDealer()));
		//Dealer won and therefore the player lost
		assertTrue(aCasino.getPlayer().hasBlackJack());
		assertTrue(aCasino.getDealer().hasBlackJack());
		//Both had blackjack
		
	}
	public void testDealerBlackjackWin() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerBlackjackWinTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getPlayer());
		//Player  was the winner
	}
	public void testPlayerScoring() {
		Player aPlayer = new Player();
		Player bPlayer = new Player();
		Player cPlayer = new Player();
		
		aPlayer.getHand().add(new Card("H10"));
		aPlayer.getHand().add(new Card("SA"));
		aPlayer.getHand().add(new Card("CA"));
		assertEquals(12,aPlayer.getHand().value());

		
		bPlayer.getHand().add(new Card("H2"));
		bPlayer.getHand().add(new Card("S2"));
		bPlayer.getHand().add(new Card("C2"));
		bPlayer.getHand().add(new Card("D2"));
		bPlayer.getHand().add(new Card("D8"));
		assertEquals(16,bPlayer.getHand().value());

		
		cPlayer.getHand().add(new Card("H10"));
		cPlayer.getHand().add(new Card("S10"));
		cPlayer.getHand().add(new Card("C5"));
		assertEquals(25,cPlayer.getHand().value());
		
		
	}
	public void testDealerScoring() {
		
		Dealer aDealer = new Dealer();
		Dealer bDealer = new Dealer();
		Dealer cDealer = new Dealer();
		
		aDealer.getHand().add(new Card("H10"));
		aDealer.getHand().add(new Card("SA"));
		aDealer.getHand().add(new Card("CA"));
		assertEquals(12,aDealer.getHand().value());

		
		bDealer.getHand().add(new Card("H2"));
		bDealer.getHand().add(new Card("S2"));
		bDealer.getHand().add(new Card("C2"));
		bDealer.getHand().add(new Card("D2"));
		bDealer.getHand().add(new Card("D8"));
		assertEquals(16,bDealer.getHand().value());

		
		cDealer.getHand().add(new Card("H10"));
		cDealer.getHand().add(new Card("S10"));
		cDealer.getHand().add(new Card("C5"));
		assertEquals(25,cDealer.getHand().value());
		
	}
	public void testPlayerBetterHand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "playerBetterHandTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getPlayer());
		//Player  was the winner
		assertTrue(aCasino.getDealer().getHand().value() < aCasino.getPlayer().getHand().value());
		
		
	}
	
	public void testDealerBetterOrEqualHand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerBetterOrEqualHandTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getDealer());
		assertTrue(aCasino.getDealer().getHand().value() >= aCasino.getPlayer().getHand().value());
		//Player  was the winner
		
	}
	public void testPlayerSpliting() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "playerSplitTest.txt"));
		fail();
		//game ran successfully
	}
	public void testDealerSpliting() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerSplitTest.txt"));
		//game ran successfully
		fail();

	}
}
