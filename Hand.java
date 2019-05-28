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
		return Math.random() * 5 <= 4;
	}
	
	public void add(Card card)
	{
		cards.add(card);
	}
	
	public Card remove(int index)
	{
		return cards.remove(index);
	}

	public int findPlay(Card topCard, int nextHandSize) {
		return 0;
	}
	
	public Color selectColor()
	{
		int highestColorNum = 0;
		int colorType = 7;   //this number doesn't matter (Patrick's orders for 7)
		
		for(int c = 0; c < 4; c++)
		{
			int colorCounter = 0;
			
			for(int v = 0; v < cards.size(); v++)
			{
				if(c == 0 && cards.get(v).getColor() == Color.RED)
					colorCounter++;	
				else if(c == 1 && cards.get(v).getColor() == Color.BLUE)
					colorCounter++;	
				else if(c == 2 && cards.get(v).getColor() == Color.GREEN)
					colorCounter++;	
				else if(c == 3 && cards.get(v).getColor() == Color.YELLOW)
					colorCounter++;	
			}
			
			if(colorCounter > highestColorNum)
			{
				highestColorNum = colorCounter;
				colorType = c;
			}
		}
		
		if(colorType == 0)
			return Color.RED;	
		else if(colorType == 1)
			return Color.BLUE;	
		else if(colorType == 2)
			return Color.GREEN;	
		else
			return Color.YELLOW;	
	}
}