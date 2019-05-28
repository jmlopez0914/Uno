import java.awt.Color;
import java.util.*;

public class computerHand extends Hand{
		
	public computerHand(int id, ArrayList<Card> hand)
	{	
		super(id, hand);
	}
	
public int findPlay(Card topCard, int nextHandSize) {
		
		/*for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i).isUseful(topCard))
			{
				return i;
			}
		}
		
		return -1;*/
		
		Color bestColor = selectColor();
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
}