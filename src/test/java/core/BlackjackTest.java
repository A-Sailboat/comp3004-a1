package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	
	public void testInitCardWithString() {
		Card diamondsJack  = new Card("DJ");
		Card heartsNine  = new Card("H9");
		assertEquals('D', diamondsJack.getSuit());
		assertEquals('J', diamondsJack.getRank());
		
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
		assertNotNull(aCasino.getFile("example.txt"));
	}
	
	
	
	public void testPlayerCardVisiblity() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		aPlayer.addHand(new Hand());
		
		aPlayer.draw(aDeck);
		aPlayer.draw(aDeck);
		assertEquals(0, aPlayer.numHiddenCards());

	}
	
	public void testDealerCardSemiVisiblity() {
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		aDealer.addHand(new Hand());
		aDealer.draw(aDeck);
		aDealer.draw(aDeck);
		
		assertEquals(1, aDealer.numHiddenCards());

	}
	
	public void testPlayerHit() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "hitTest.txt"));
		//game ran successfully
		assertEquals(3,aCasino.getPlayer().getActiveHand().size());
		//Player hit at least once as they have 3 cards

	}
	
	public void testPlayerMultiHit() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "multiHitTest.txt"));
		//game ran successfully
		assertEquals(11,aCasino.getPlayer().getActiveHand().size());
		//Player hit at nine times which is the max possible 
		//in blackjack
	}

	public void testPlayerStand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "standTest.txt"));
		//game ran successfully
		assertEquals(11,aCasino.getPlayer().getActiveHand().value());
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
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerHitMinTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getDealer());
		//Dealer won and therefore the player lost
		
	}
	public void testDealerSoftHitMin() {
		
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		aDealer.draw(aDeck,new Card("SK"));
		aDealer.draw(aDeck,new Card("H6"));
		assertEquals('H', aDealer.decideTurn(aDealer.getActiveHand()));
		//Hits on 16
		
		Deck bDeck = new Deck();
		Dealer bDealer = new Dealer();
		bDealer.draw(bDeck,new Card("SK"));
		bDealer.draw(bDeck,new Card("H7"));
		assertEquals('S', aDealer.decideTurn(aDealer.getActiveHand()));
		//Stands on hard 17
		
		
		
	}
	public void testDealerMultiHit() {
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		aDealer.draw(aDeck,new Card("SK"));
		aDealer.draw(aDeck,new Card("H6"));
		assertEquals('H', aDealer.decideTurn(aDealer.getActiveHand()));
		
	}
	public void testDealerHandReveal() {
		
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f","handReveal.txt"));
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
		aHand.add(new Card("DT"));
		aHand.add(new Card("DA"));
		aHand.add(new Card("HA"));
		assertEquals(14,aHand.value());
		//drew all 4 aces ie max possible
	}
	public void testCourtIsTen() {
		Hand jackHand = new Hand();
		jackHand.add(new Card("HJ"));
		assertEquals(10,jackHand.value());
		
		Hand queenHand = new Hand();
		queenHand.add(new Card("HQ"));
		assertEquals(10,queenHand.value());
		
		Hand kingHand = new Hand();
		kingHand.add(new Card("HK"));
		assertEquals(10,kingHand.value());
	}
	public void testPlayerBlackjackDetect() {
		Casino aCasino = new Casino();
		aCasino.getPlayer().getActiveHand().add(new Card("SK"));
		aCasino.getPlayer().getActiveHand().add(new Card("SA"));
		assertTrue(aCasino.getPlayer().hasBlackJack());
	}
	
	public void testDealerBlackjackDetect() {
		Casino aCasino = new Casino();
		aCasino.getDealer().getActiveHand().add(new Card("SK"));
		aCasino.getDealer().getActiveHand().add(new Card("SA"));
		assertTrue(aCasino.getDealer().hasBlackJack());
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
		assertEquals(0,aCasino.runGame("f", "doubleBlackjackDealerTrumps.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getDealer());
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
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "aPlayerScoringTest.txt"));
		//game ran successfully
		assertEquals(12,aCasino.getPlayer().getActiveHand().value());
		//Player  was the winner
		
		Casino bCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "bPlayerScoringTest.txt"));
		//game ran successfully
		assertEquals(23,bCasino.getPlayer().getActiveHand().value());
		//Player  was the winner
		
		Casino cCasino = new Casino();
		assertEquals(0,cCasino.runGame("f", "cPlayerScoringTest.txt"));
		//game ran successfully
		assertEquals(25,cCasino.getPlayer().getActiveHand().value());
		//Player  was the winner
		
		
	}
	public void testDealerScoring() {
			Casino aCasino = new Casino();
			assertEquals(0,aCasino.runGame("f", "aDealerScoringTest.txt"));
			//game ran successfully
			assertEquals(12,aCasino.getDealer().getActiveHand().value());
			//Dealer  was the winner
			
			Casino bCasino = new Casino();
			assertEquals(0,aCasino.runGame("f", "bDealerScoringTest.txt"));
			//game ran successfully
			assertEquals(23,bCasino.getDealer().getActiveHand().value());
			//Dealer  was the loser
			
			Casino cCasino = new Casino();
			assertEquals(0,cCasino.runGame("f", "cDealerScoringTest.txt"));
			//game ran successfully
			assertEquals(17,cCasino.getDealer().getActiveHand().value());
			//Dealer  was the winner
		
	}
	public void testPlayerBetterHand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "playerBetterHandTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getPlayer());
		//Player  was the winner
		assertTrue(aCasino.getDealer().getActiveHand().value() < aCasino.getPlayer().getActiveHand().value());
		
		
	}
	
	public void testDealerBetterOrEqualHand() {
		Casino aCasino = new Casino();
		assertEquals(0,aCasino.runGame("f", "dealerBetterOrEqualHandTest.txt"));
		//game ran successfully
		assertTrue(aCasino.getWinner() == aCasino.getDealer());
		assertTrue(aCasino.getDealer().getActiveHand().value() >= aCasino.getPlayer().getActiveHand().value());
		//Player  was the winner
		
	}
	public void testPlayerSpliting() {
		
	}
	public void testDealerSpliting() {
		
	}
}
