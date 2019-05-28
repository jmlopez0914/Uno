import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Game {
	private static ArrayList<Card> discardPile = new ArrayList<Card>();
	private static Deck deck = new Deck();
	private static ArrayList<Hand> hands = new ArrayList<Hand>();
	
	static int turn;
	static boolean direction = true;	//handles reverse cards
	static String forcedMove = "";		//handles special cards
	private static unoGUI UG;
	private static boolean begunPlay = false;
	private static boolean startedTimer = false;
	private static double unoTimer = 0;
	private static long lastTime = 0;
	
	public static void main(String[] args) throws IOException, InterruptedException {		
		int players = 4;
		//creates hands
		hands.add(new playerHand(0, new ArrayList<Card>()));
		for (int x = 1; x <= players - 1; x++) {
			hands.add(new computerHand(x, new ArrayList<Card>()));
		}
		turn = (int) (Math.random() * players);	//determines who goes first
		
		//deals the cards to the players
		deck.shuffle();
		for (int x = 1; x <= 7; x++) {
			for (int y = 0; y < hands.size(); y++) {
				Card topCard = deck.draw();
				hands.get(y).add(topCard);
			}
		}
		
		//turns over the first card to begin
		discardPile.add(deck.draw());

		//handles wild d4
		while (discardPile.get(0) != null && discardPile.get(0).getID() == "D4") {
			deck.add(discardPile.remove(0));
			discardPile.add(deck.draw());
		}
		
		//officially, every other card has their effect activated upon starting
		handleCardEffect(discardPile.get(0));
		
		JFrame frame = new JFrame("Game");
		UG = new unoGUI(hands.get(0), hands.get(1), hands.get(2), hands.get(3), topCard(), frame);
		frame.add(UG);
		frame.setVisible(true);
		frame.setResizable(false);	
		Color background = new Color(11, 102, 35);
		frame.getContentPane().setBackground(background);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 1000); 	
		
		UG.draw(-1);
		begunPlay = true;
		
		while(checkForWinner() == -1)
		{	
			UG.draw(turn);
			Thread.sleep(1000);
			playSelection();
			
		}
		
		UG.draw(-1);
		UG.setTopOfDiscard(topCard());
		
		System.out.println("Winner = " + checkForWinner());
		UG.displayGameOver(checkForWinner());
	}
	
	private static void playSelection() throws IOException {
		int nextPlayer;
		if(direction)
			nextPlayer = (turn + 1) % 4;
		else
			nextPlayer = (turn - 1 + 4) % 4;
		
		int move = hands.get(turn).findPlay(topCard(), hands.get(nextPlayer).getHand().size(), UG);		
		
		if (move >= 0)	//representing an index of a card in the hand
		{
			System.out.println("playSelection play");
			playCard(move);
		}
		else if (deck.size() == 0)
		{
			handleEmptyDeck();
			System.out.println("DeckOut, next player from P" + turn + "DP:" + discardPile.size() + " Deck:" + deck.size());
			moveToNextPlayer();
		}
		else {	//representing the drawing index
			System.out.println("playSelection draw");
			hands.get(turn).add(deck.draw());
			UG.draw(turn);
			handleEmptyDeck();
		}
	}
	
	private static void playCard(int move) throws IOException {
		Card playedCard = hands.get(turn).remove(move);
		System.out.println("playCard (" + playedCard.getColor() + ", " + playedCard.getID() + ") on " + topCard().toString());
		UG.placeCard(playedCard, hands.get(turn));
		discardPile.add(playedCard);
		UG.setTopOfDiscard(topCard());
		System.out.println("\t\ttopDiscardCard" + discardPile.get(discardPile.size() - 1) + "DP: " + discardPile.size());
		
		
		handleCardEffect(playedCard);
	}
	
	private static void handleCardEffect(Card playedCard) throws IOException {
		forcedMove = playedCard.getID();
		switch (forcedMove) {
			case "S":
				System.out.println("handleSkip");
				moveToNextPlayer();
				break;
			case "D2":
				System.out.println("handleD2");
				moveToNextPlayer();
				//draw two cards
				for (int x = 0; x < 2; x++) {
					if (deck.size() > 0)
					{
						hands.get(turn).add(deck.draw());
						if(begunPlay)
							UG.draw(turn);
						handleEmptyDeck();
					}
				}
				break;
			case "D4":		
				System.out.println("handleD4");
				topCard().setColor(hands.get(turn).selectColor(UG));
				moveToNextPlayer();
				//draw four cards
				for (int x = 0; x < 4; x++) {
					if (deck.size() > 0)
					{
						hands.get(turn).add(deck.draw());
						if(begunPlay)
							UG.draw(turn);
						handleEmptyDeck();
					}
				}
				break;
			case "R":
				System.out.println("handleReverse");
				direction = !direction;
				break;	
			case "W":
				System.out.println("handleWild");
				topCard().setColor(hands.get(turn).selectColor(UG));
				break;
		}	//end switch
		moveToNextPlayer();
	}
	
	private static void moveToNextPlayer() {
		if (direction) {
			System.out.println("nextPlayer direction: " + direction);
			turn++;
			if (turn > hands.size() - 1)
				turn = 0;
		} else {
			System.out.println("nextPlayer direction: " + direction);
			turn--;
			if (turn < 0)
				turn = hands.size() - 1;
		}
	}

	
	private static Card topCard() {
		return discardPile.get(discardPile.size() - 1);
	}
	
	private static void handleEmptyDeck() {
		if (deck.size() <= 0 && discardPile.size() > 1) {
			System.out.println("\t\t\t\thandleEmptyDeck (DP: " + discardPile.size() + ", Deck: " + deck.size());
			while (0 < discardPile.size() - 1) {
				if (discardPile.get(0).getID().equals("W") || discardPile.get(0).getID().equals("D4"))
				{
					discardPile.get(0).setColor(Color.BLACK);
				}
				deck.add(discardPile.remove(0));
				UG.setTopOfDiscard(topCard());
				System.out.println(discardPile.get(0));
			}
			deck.shuffle();
		}
	}
	
	private static void checkForUncalledUno() throws IOException {
			if (hands.get(turn).getHand().size() == 1 && hands.get(turn).getUno()) {
				return;
			} else if (hands.get(turn).getHand().size() == 1 ){
				for (int x = 0; x < 2; x++) {
					if(deck.size() > 0)
					{
						hands.get(turn).add(deck.draw());
						UG.draw(turn);
						handleEmptyDeck();
					}
				}
		}
	}

	private static void playerCallUno()
	{
		if(!startedTimer)
		{
			unoTimer = 2000;
			startedTimer = true;
		}
	}
	
	private static int checkForWinner() {
		for (int c = 0; c < hands.size(); c++) {
			if (hands.get(c).getHand().size() == 0) {
				return c;
			}
		}
		return -1;
	}
}