import java.util.*;

public class Hand{

	public Hand(int ID, Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7)
	{
		
	}
	
	public static void showUseful()
	{
		Card top = GetPile().getTop();
		for (Card thisCard : Hand)
		{
			if(thisCard.getColor().equals(top.GetColor()) || thisCard.getNumber() top.getNumber())
			{
				Useful.add(thisCard);
				
			}
			if(thisCard.getNumber() == 13 || thisCard.getNumber() == 14)
			{
				Useful.add(thisCard);
			}
		}
		
	}
	
	public static void PickUp(Card card)
	{
		while (Useful.size() == 0)
		{
			Hand.add(getDeck().getTop());
		}
	}
	
	public static boolean UNO(Hand hand)
	{
		return hand.size() == 1;
	}
	
	
}
