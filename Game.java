import java.util.ArrayList;

public class Game {
	ArrayList<Card> discardPile;
	Deck deck;
	ArrayList<Hand> hands;
		
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
				Card topCard = deck.draw(1);
				hands.get(x).add(topCard);
			}
		}
		//turns over the first card to begin
		discardPile.add(deck.draw(1));
	}
	
	public void playCard() {
		Card playedCard = hands.remove(turn).selectedCard();
		discardPile.add(playedCard);
		forcedMove = playedCard.nextPlay();	//getIntermediate();
		switch (forcedMove) {
			case "S":
				moveToNextPlayer();
				break;
			case "D2":
				moveToNextPlayer();
				//draw two cards
				hands.get(turn).add(deck.draw(2));
				break;
			case "D4":
				moveToNextPlayer();
				//draw four cards
				hands.get(turn).add(deck.draw(4));
				break;
			case "R":
				reverse();
				break;	
			case "W":
				//GUI call
				break;
		}
		moveToNextPlayer();
	}
	
	public void moveToNextPlayer() {
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
	
	public void reverse() {
		direction = !direction;
	}
	
	public int checkForWinner() {
		for (int c = 0; c < hands.size(); c++) {
			if (hands.get(c).size() == 0) {
				return c;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		//GUI calls
	}
}
