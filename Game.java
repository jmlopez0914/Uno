import java.util.ArrayList;

public class Game {
	private ArrayList<Card> discardPile;
	private Deck deck;
	private ArrayList<Hand> hands;
	private UnoGUI gui;
	
	int turn;
	boolean direction = true;	//handles reverse cards
	String forcedMove = "";		//handles d2, wd4, and s
	
	public Game(int players) {
		//creates hands
		hands.add(new PlayerHand(/**/));
		for (int x = 1; x <= players - 1; x++) {
			hands.add(new ComputerHand(/**/));
		}
		turn = (int) (Math.random() * players);	//determines who goes first
		
		//deals the cards to the players
		deck.shuffle();
		for (int x = 1; x <= 7; x++) {
			for (int y = 0; y < hands.size(); y++) {
				Card topCard = deck.draw();
				hands.get(x).add(topCard);
			}
		}
		
		//turns over the first card to begin
		discardPile.add(deck.draw());
		//handles wild d4
		while (discardPile.get(0) != null && discardPile.get(0).getID() != "D4")
			deck.add(discardPile.remove(0));
			discardPile.add(deck.draw());
		}
	}
	
	private void playSelection() {
		move = hands.get(turn)./*whatever*/(topCard());
		
	}
	
	private void playCard() {
		Card playedCard = hands.remove(turn).selectedCard();
		discardPile.add(playedCard);
		gui.placeCard(playedCard);
		handleCardEffect(playedCard);
	}
	
	private void handleCardEffect(Card playedCard) {
		forcedMove = playedCard.getID();
		switch (forcedMove) {
			case "S":
				moveToNextPlayer();
				break;
			case "D2":
				moveToNextPlayer();
				//draw two cards
				for (int x = 0; x < 2; x++) {
					hands.get(turn).add(deck.draw());
					handleEmptyDeck();
				}
				break;
			case "D4":			
				topCard().setColor(hands.get(turn).selectColor());
				moveToNextPlayer();
				//draw four cards
				for (int x = 0; x < 4; x++) {
					hands.get(turn).add(deck.draw());
					handleEmptyDeck();
				}
				break;
			case "R":
				direction = !direction;
				break;	
			case "W":
				topCard().setColor(hands.get(turn).selectColor());
				break;
		}	//end switch
		moveToNextPlayer();
	}
	
	private void moveToNextPlayer() {
		if (direction) {
			turn++;
			if (turn > hands.size() - 1)
				turn = 0;
		} else {
			turn--;
			if (turn < 0)
				turn = hands.size() - 1;
		}
	}

	
	private Card topCard() {
		return discardPile.get(discardPile.size() - 1);
	}
	
	private void handleEmptyDeck() {
		if (deck.size() == 0) {
			for (int x = 0; x < discardPile.size() - 1; x++) {
				deck.add(discardPile.remove(0));
			}
			deck.shuffle();
		}
	}
	
	private int checkForUncalledUno() {
		for (int c = 0; c < hands.size(); c++) {
			if (hands.get(c).size() == 1 && hands.get(c).getUno() == false) {
				return c;
			} else {
				for (int x = 0; x < 2; x++) {
					hands.get(turn).add(deck.draw());
					handleEmptyDeck();
				}
			}
		}
		return -1;
	}
	
	private int checkForWinner() {
		for (int c = 0; c < hands.size(); c++) {
			if (hands.get(c).size() == 0) {
				return c;
			}
		}
		return -1;
	}
	public static void main(String args[]) {
		
	}
}
