import java.util.*;

public class Hand{
	
	private int ID;
	private ArrayList<Card> cards = new ArrayList<Card>;
	
	
	public Hand(int ID, ArrayList<Card> cards)
	{
		this.cards = cards;
		this.ID = ID;
				
	}
	
	public ArrayList<Card> getHand()
	{
		return cards;
	}
	
	public static int getID(int ID)
	{
		return ID;
	}
	
	public static boolean UNO(ArrayList<Card> cards)
	{
		return cards.size() == 1;
	}
	
	
	
	
}
