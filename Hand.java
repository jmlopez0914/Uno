import java.awt.Color;
import java.util.*;

public class Hand{
	
	private int ID;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	
	public Hand(int ID, ArrayList<Card> cards)
	{
		this.cards = cards;
		this.ID = ID;
				
	}
	
	public ArrayList<Card> getHand()
	{
		return cards;
	}
	
	public int getID()
	{
		return ID;
	}
	
	//Game already took care of checking for 1 card, so this is just making the computer have a random chance to say UNO
	public boolean getUno()
	{
		return false;
	}
	
	public void add(Card card)
	{
		cards.add(card);
	}
	
	public Card remove(int index)
	{
		return cards.remove(index);
	}

	public int findPlay(Card topCard, int nextHandSize, unoGUI gui) {
		return 0;
	}
	
	public Color selectColor(unoGUI gui)
	{
		return null;	
	}
}