import java.awt.Color;
import java.util.*;

public class playerHand extends Hand
{
	
	public playerHand(int i, ArrayList<Card> hand)
	{
		super(i, hand);		
	}

	public int findPlay(Card topCard, int nextHandSize, unoGUI gui) 
	{
		return gui.getCardInput(topCard);
	}
	
	public boolean getUno()
	{
		return true;
	}
	
	public Color selectColor(unoGUI gui)
	{
		return gui.getColorInput();
	}
}