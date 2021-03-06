 import java.awt.Color;
import java.util.ArrayList;

public class Deck {

	private static final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	private ArrayList<Card> cards;
	
	public Deck()
	{
		cards = new ArrayList<Card>();
		for (Color c: colors)
		{
			cards.add(new Card(0 + "", c));
			
			for(int i = 0; i < 2; i++)
			{
				for (int n = 1; n <= 9; n++)
					cards.add(new Card(n + "", c));
				
				cards.add(new Card("S", c));
				cards.add(new Card("D2", c));
				cards.add(new Card("R", c));
			}
			
		}
		
		for (int i = 0; i < 4; i++)
		{
			cards.add(new Card("W", Color.black));
			cards.add(new Card("D4", Color.black));
		}
	}
	
	public void shuffle()
	{
		for (int i = 0; i < cards.size(); i++)
		{
			int rand = (int)(Math.random() * i);
			Card r = cards.get(rand);
			Card c1 = cards.get(i);
			cards.set(rand, c1);
			cards.set(i, r);
		}
	}
	
	public void add(Card c)
	{
		cards.add(c);
	}
	
	public Card draw()
	{
		if(cards.size() > 0)
			return cards.remove(0);
		else
			return null;
	}
	
	public int size()
	{
		return cards.size();
	}
}