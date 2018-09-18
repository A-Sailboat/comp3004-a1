package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	public void testDeckCount() {
		Deck aDeck  = new Deck();
		assertEquals(52, aDeck.size());
	}
	public void testFileLoad() {
		Casino aCasino  = new Casino();
		assertEquals(0, aCasino.runGame("f"));
	}
	
	public void testConsoleLoad() {
		Casino aCasino  = new Casino();
		assertEquals(0, aCasino.runGame("c"));
	}
	
	public void testPlayerCardVisiblity() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		aPlayer.addHand(new Hand());
		
		aPlayer.draw(aDeck,2);
		assertEquals(0, aPlayer.numHiddenCards());

	}
	
	public void testDealerCardSemiVisiblity() {
		Deck aDeck = new Deck();
		Dealer aDealer = new Dealer();
		aDealer.addHand(new Hand());
		aDealer.draw(aDeck,2);
		assertEquals(1, aDealer.numHiddenCards());

	}
	
	public void testPlayerHit() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		aPlayer.addHand(new Hand());
		
		assertThat(aPlayer.draw(aDeck), isA(Card));
		assertEqual((aPlayer.draw(aDeck)).size(),(hand.size()+1));
	}
	
	public void testPlayerMultiHit() {
		
	}

	public void testPlayerStand() {
		
	}
	public void testPlayerHandReveal() {
		
	}
	
	public void testPlayerBust() {
		
	}
	
	public void testDealerHitMin() {
		
	}
	public void testDealerSoftHitMin() {
		
	}
	public void testDealerMultiHit() {
		
	}
	public void testDealerHandReveal() {
		
	}		
	public void testDealerBust() {
		
	}
	public void testAceAsOne() {
		
	}
	public void testAceAsEleven() {
		
	}
	public void testTwoAceHand() {
		Hand aHand = new Hand();
		aHand.add(new Card("SA"));
		aHand.add(new Card("CA"));
		assertEquals(12, aHand.value());
	}
	public void testAceElevenToOne() {
		
	}
	public void testMultiAceAsOne() {
		Hand aHand = new Hand();
		Card redAce = new Card("DA");
		Card blackAce = new Card("SA");
		aHand.add(new Card("SA"));
		aHand.add(new Card("CA"));
		aHand.add(new Card("DT"));
		assertEquals(12,aHand.value());
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
		
	}
	public void testDealerBlackjackDetect() {
		
	}
	public void testPlayerBlackjackWin() {
		
	}
	public void testDoubleBlackjackDealerTrumps() {
		
	}
	public void testDealerBlackjackWin() {
		
	}
	public void testPlayerScoring() {
		
	}
	public void testDealerScoring() {
		
	}
	public void testPlayerBetterHand() {
		
	}
	public void testDealerBetterHand() {
		
	}
	public void testPlayerSpliting() {
		
	}
	public void testDealerSpliting() {
		
	}
}
