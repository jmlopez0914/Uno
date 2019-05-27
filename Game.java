import java.awt.Color;
import java.util.ArrayList;

public class Game {
	private ArrayList<Card> discardPile = new ArrayList<Card>();
	private Deck deck = new Deck();
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	private static UnoGUI gui = new UnoGUI();
	
	int turn;
	boolean direction = true;	//handles reverse cards
	String forcedMove = "";		//handles special cards
	
	public Game(int players) {
		//creates hands
		hands.add(new PlayerHand());
		for (int x = 1; x <= players - 1; x++) {
			hands.add(new ComputerHand());
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
		while (discardPile.get(0) != null && discardPile.get(0).getID() != "D4") {
			deck.add(discardPile.remove(0));
			discardPile.add(deck.draw());
		}
		//officially, every other card has their effect activated upon starting
		handleCardEffect(discardPile.get(0));
	}
	
	private void makeAMove() {
		int move = hands.get(turn).findPlay(topCard());
		if (move >= 0)	//representing an index of a card in the hand
			playCard(move);
		else {	//-1 represents the drawing index
			hands.get(turn).add(deck.draw());
			handleEmptyDeck();
			
			//check if the hand plays the card they drew
			if (hands.get(turn).playDrawnCard(topCard())) {
				//the last card in the ArrayList is the drawn card
				playCard(hands.get(turn).size() - 1);	
			}		
		}
	}
	
	private void playCard(int move) {
		Card playedCard = hands.get(turn).remove(move);
		gui.placeCard(playedCard, hands.get(turn));	//if needed
		discardPile.add(playedCard);
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
				if (hands.get(turn).challengeDrawFour()) {
					moveToPreviousPlayer();	//go back to the player that played the D4
					hands.get(turn).setVisibility(true);	//makes visible the hand
					int z = 0; boolean matchingColor = false;
					while (z < hands.get(turn).size() && !matchingColor) {
						if (hands.get(turn).getHand().get(z).getColor().equals(discardPile.get(discardPile.size() - 2).getColor())) {
							//this is correct; official Uno rules allow a draw four to be played even if a number/word match can be played
							matchingColor = true;
						}
					}
					
					if (matchingColor) {	//the card played was illegal
						//return the wild to the player's hand
						topCard().setColor(Color.black);	//resets the wild so it call be played on anything again
						hands.get(turn).add(discardPile.remove(discardPile.size() - 1));
						//force the player to play a legal card that is not D4
						int move = -1;	//the check guaranteed a legal card exists, so this index is a placeholder and not for drawing
						do {
							move = hands.get(turn).findPlay(topCard());
						} while (move >= 0 && !hands.get(turn).getHand().get(move).getID().equals("D4"));
						playCard(move);
						
						//gives four cards to the player who played illegally as punishment
						for (int x = 0; x < 4; x++) {
							hands.get(turn).add(deck.draw());
							handleEmptyDeck();
						}
						
					} else {	//the card played was legal
						//gives four cards for the draw four and two penalty cards for the false challenge (six total)
						for (int x = 0; x < 6; x++) {
							hands.get(turn).add(deck.draw());
							handleEmptyDeck();
						}						
					}
				} else {	//the play was not challenged
				//draw four cards
					for (int x = 0; x < 4; x++) {
						hands.get(turn).add(deck.draw());
						handleEmptyDeck();
					}
				}
				break;
			case "R":
				reverse();
				break;	
			case "W":
				topCard().setColor(hands.get(turn).selectColor());
				break;
		}	//end switch
	}
	
	private void reverse() {
		direction = !direction;
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
	private void moveToPreviousPlayer() {
		reverse();
		moveToNextPlayer();
		reverse();
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
				for (int x = 0; x < 2; x++) {
					hands.get(turn).add(deck.draw());
					handleEmptyDeck();
				}
				return c;
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
	
	public UnoGUI getGUI() {
		return gui;
	}
	
	public static void main(String args[]) {
		Game unoRound = new Game(4);
		unoRound.getGUI().unoGUI2(/*just tell me the parameters you need to render and I'll give 'em*/);
		while (unoRound.checkForWinner() == -1) {
			//if any other gui calls are needed here, I will take them
			unoRound.makeAMove();
			unoRound.checkForUncalledUno();
			unoRound.moveToNextPlayer();
		}
		//make a winner screen for the winner
	}
}
