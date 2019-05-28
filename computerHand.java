import java.awt.Color;
import java.util.*;

public class computerHand extends Hand{
		
	public computerHand(int id, ArrayList<Card> hand)
	{	
		super(id, hand);
	}
	
public int findPlay(Card topCard, int nextHandSize, unoGUI gui) {





		
		Color bestColor = selectColor(gui);
		int latestIndex = -1;
		int D4Index = -1;
		
		if(nextHandSize <= 4)
		{
			if(getHand().indexOf(new Card("D4", topCard.getColor())) >= 0)
			{
				System.out.println("Choosing D2 from " + getHand().indexOf(new Card("D2", topCard.getColor())));
				return getHand().indexOf(new Card("D4", topCard.getColor()));
			}
			else if(getHand().indexOf(new Card("S", topCard.getColor())) >= 0)
			{
				System.out.println("Choosing S from " + getHand().indexOf(new Card("S", topCard.getColor())));
				return getHand().indexOf(new Card("S", topCard.getColor()));
			}
		}
		
		for(int i = 0; i < getHand().size(); i++)
		{
			if(getHand().get(i).isUseful(topCard))
			{
				if(getHand().get(i).getColor() == bestColor)
				{
					System.out.println("Choosing First in bestColor");

					return i;
				}
				else if(getHand().get(i).getID() != "D4")
				{
					latestIndex = i;
				}
				else
					D4Index = i;
			}
		}
		
		if(latestIndex == -1)
		{
			if(D4Index >= 0)
			{
				System.out.println("Choosing D4");
				return D4Index;
			}
			else
				System.out.println("NOT USEFUL!!");
				
		}
		
		return latestIndex;
	}

	public Color selectColor(unoGUI gui)
	{
		int highestColorNum = 0;
		int colorType = 7;   //this number doesn't matter (Patrick's orders for 7)
		
		for(int c = 0; c < 4; c++)
		{
			int colorCounter = 0;
			
			for(int v = 0; v < getHand().size(); v++)
			{
				if(c == 0 && getHand().get(v).getColor() == Color.RED)
					colorCounter++;	
				else if(c == 1 && getHand().get(v).getColor() == Color.BLUE)
					colorCounter++;	
				else if(c == 2 && getHand().get(v).getColor() == Color.GREEN)
					colorCounter++;	
				else if(c == 3 && getHand().get(v).getColor() == Color.YELLOW)
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
	
	public boolean getUno()
	{
		return (Math.random() * 5) <= 4;
	}
}