import java.awt.Color;

public class Card 
{
	
	private String ID;
	private Color color;
	
	public Card(String i, Color c)
	{
		color = c;
		ID = i;
		
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String getID()
	{
		return ID;
	}
	
}
