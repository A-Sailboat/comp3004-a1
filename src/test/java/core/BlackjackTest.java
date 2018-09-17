package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{

	public void testFileLoad() {
		BlackjackInputSelect start  = new BlackjackInputSelect();
		
	
	public void testDeck() {
		Deck aDeck = new Deck();
		
		assertEquals(52, aDeck.size());
		
	}
	public void testShuffle() {
		Deck aDeck = new Deck();
		Deck aCopyDeck = aDeck;
		
		aDeck.shuffle();
		assertEquals(52, aDeck.size());
		
	}
	
	public void testConsole() {
		BlackjackInputSelect start = new BlackjackInputSelect();
		assertEquals(1, BlackjackInputSelect("c"));
		
	}
	public void testPlayerCardVisiblity() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		
		aPlayer.deal(aDeck.draw);
		
		assertEqual(False, player.hand.numHiddenCard());

	}
	
	public void testDealerCardSemiVisiblity() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player("Dealer");
		
		aPlayer.deal(aDeck.draw);
		
		
		assertEqual(1, hand.numHiddenCard());

	}
	
	public void testPlayerHit() {
		Deck aDeck = new Deck();
		Player aPlayer = new Player();
		handSize = aPlayer.hands[0].size();
		
		assertThat(player.hit(aDeck), is(instanceof(Card)));
		assertEqual((hand.hit(deck)).size(),(hand.size()+1));
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
		aHand.hit(new Card("Ace","Spades"));
		aHand.hit(new Card("Ace","Clubs"));
		assertEqual(12, aHand.value());
	}
	public void testAceElevenToOne() {
		
	}
	public void testMultiAceAsOne() {
		Hand aHand = new Hand();
		aHand.hit(new Card("Ace","Spades"));
		aHand.hit(new Card("Ace","Clubs"));
		aHand.hit(new Card(10,"Diamonds"));
		assertEqual(12,aHand.value());
	}
	public void testCourtIsTen() {
		Card jack = new Card("Jack","Hearts");
		Card queen = new Card("Queen","Spades");
		Card king = new Card("King","Clubs");
		
		assertEqual(10,jack.getValue());
		assertEqual(10,queen.getValue());
		assertEqual(10,king.getValue());
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
