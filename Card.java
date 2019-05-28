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
	
	public boolean isUseful(Card topCard)
	{	
		return color == topCard.getColor() || ID == topCard.getID() || ID == "W" || ID == "D4";
	}
	
	public Color selectColor(Color input)
	{
		return input;
	}
	
	public void setColor(Color newColor)
	{
		color = newColor;
	}
	
	public String toString()
	{
		return "card " + "(" + getColor() + ", " + getID() + ")";
	}
}