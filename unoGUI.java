import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class unoGUI extends JComponent /*implements ActionListener*/{	
	private BufferedImage cards0[] = new BufferedImage[108];
	private Rectangle2D bounds[] = new Rectangle2D.Double[108];
	private BufferedImage cards1[] = new BufferedImage[108];
	private BufferedImage cards2[] = new BufferedImage[108];
	private BufferedImage cards3[] = new BufferedImage[108];
	private BufferedImage textBox = null;
	private BufferedImage deck = null;
	private Rectangle2D deckBound = null;
	private BufferedImage arrow = null;
	private BufferedImage discard[] = new BufferedImage[108];
	private Hand hand0, hand1, hand2, hand3;
	private Card topOfDiscard;
	boolean press = false;
	
	public unoGUI(Hand hand0, Hand hand1, Hand hand2, Hand hand3, Card topDiscard, JFrame frame)
	{			
		
		this.hand0 = hand0;
		this.hand1 = hand1;
		this.hand2 = hand2;
		this.hand3 = hand3;
		
		topOfDiscard = topDiscard;
		
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				press = true;
			}
		});		
	}	
	
	public int getCardInput(Card top)
	{
		boolean turnPlayed = false;
		int cardPlayed = 0;
		
		ArrayList<Integer> useful = new ArrayList<Integer>();
		
		for(int i = 0; i < hand0.getHand().size(); i++)
		{
			if(hand0.getHand().get(i).isUseful(top))
			{
				useful.add(i);
			}
		}
		
		
			
		
		while(turnPlayed == false)
		{
			//Can't remove
			System.out.print("");
			
			
			
			if(press == true)
			{
				if(deckBound.getBounds2D().contains(getMousePosition()))
				{
					turnPlayed = true;
					press = false;
					return -1;
				}
				

				for(int x = 0; x < cards0.length; x ++)
				{
					for(int i = 0; i < useful.size(); i++)
					{
						if(x == useful.get(i))
						{
							if(bounds[x].getBounds2D().contains(getMousePosition()))
							{
								turnPlayed = true;
								cardPlayed = x;
							}
						}
					}
				}
	
				press = false;
			}
		}
		return cardPlayed;
		
	}
	
	public Color getColorInput()
	{
		boolean colorChosen = false;
		
		Arc2D redArc = new Arc2D.Double(1200, 700, 120, 120, 90, 90, Arc2D.PIE);
		Arc2D blueArc = new Arc2D.Double(1200, 700, 120, 120, 0, 90, Arc2D.PIE);
		Arc2D greenArc = new Arc2D.Double(1200, 700, 120, 120, 270, 90, Arc2D.PIE);
		Arc2D yellowArc = new Arc2D.Double(1200, 700, 120, 120, 180, 90, Arc2D.PIE);
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g; 
		
		g2.setColor(Color.red);
		g2.fill(redArc);
		g2.setColor(Color.blue);
		g2.fill(blueArc);
		g2.setColor(Color.green);
		g2.fill(greenArc);
		g2.setColor(Color.yellow);
		g2.fill(yellowArc);
		
		while(colorChosen == false)
		{		
			System.out.println("");
			if(press == true)
			{				
				if(redArc.getBounds2D().contains(getMousePosition()))
				{
					press = false;
					colorChosen = true;
					g2.setColor( new Color(11, 102, 35));
					g2.fillRect(1080, 580, 240, 240);
					return Color.red;
					
				}
				else if(blueArc.getBounds2D().contains(getMousePosition()))
				{
					press = false;
					colorChosen = true;
					g2.setColor( new Color(11, 102, 35));
					g2.fillRect(1080, 580, 240, 240);
					return Color.blue;
				}
				else if(greenArc.getBounds2D().contains(getMousePosition()))
				{
					press = false;
					colorChosen = true;
					g2.setColor( new Color(11, 102, 35));
					g2.fillRect(1080, 580, 240, 240);
					return Color.green;
				}
				else if(yellowArc.getBounds2D().contains(getMousePosition()))
				{
					press = false;
					colorChosen = true;
					g2.setColor( new Color(11, 102, 35));
					g2.fillRect(1080, 580, 240, 240);
					return Color.yellow;
				}	
			}
			press = false;
		}
		return null;
	}


	
	public void placeCard(Card card, Hand hand) throws IOException
	{
		draw(hand.getID());
	}
	
	public void draw(int turn) throws IOException

	{
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g; 

		g2.setColor( new Color(11, 102, 35));
		
		g2.rotate((0)*Math.PI/2, 800, 500);
		g2.fillRect(1000, 500, 100, 50); //resize and move as needed
		g2.rotate(-(0)*Math.PI/2, 800, 500);
		g2.rotate((1)*Math.PI/2, 800, 500);
		g2.fillRect(1000, 500, 100, 50); //resize and move as needed
		g2.rotate(-(1)*Math.PI/2, 800, 500);
		g2.rotate((2)*Math.PI/2, 800, 500);
		g2.fillRect(1000, 500, 100, 50); //resize and move as needed
		g2.rotate(-(2)*Math.PI/2, 800, 500);
		g2.rotate((3)*Math.PI/2, 800, 500);
		g2.fillRect(1000, 500, 100, 50); //resize and move as needed
		g2.rotate(-(3)*Math.PI/2, 800, 500);
		
		arrow = ImageIO.read(new File("arrow.png"));
		if (turn != -1) {
			g2.rotate((turn+1)*Math.PI/2, 800, 500);
			g2.drawImage(arrow, 1000, 500, 100, 50, null);
			g2.rotate(-(turn+1)*Math.PI/2, 800, 500);
		}
		
		deck = ImageIO.read(new File("card_back.png"));

		g2.drawImage(deck, 700, 430, 100, 140, null);
		deckBound = new Rectangle2D.Double(700, 430, 100, 140);
		

		discard[0] = ImageIO.read(getCardFile(topOfDiscard));
		
		g2.drawImage(discard[0], 900, 430, 100, 140, null);
		
		if(turn == 0 || turn == -1)
		{
			g2.setColor( new Color(11, 102, 35));
			g2.fillRect(0, 880, 1600, 140);
			for(int x = 0; x < hand0.getHand().size(); x++)
			{
				cards0[x] = ImageIO.read(getCardFile(hand0.getHand().get(x)));		
			}		
			for(int x = 0; x < hand0.getHand().size(); x++)
			{
				int spacing = 1200 / (hand0.getHand().size() + 1);
				g2.drawImage(cards0[x], 200 + spacing * (x + 1) - 50, 880, 100, 140, null);
				bounds[x] = new Rectangle2D.Double(200 + spacing * (x + 1) - 50, 880, 100, 140);
			}
		}
		
		
		if(turn == 1 || turn == -1)
		{
			g2.setColor( new Color(11, 102, 35));
			g2.fillRect(-20, 0, 140, 1000);
			for(int x = 0; x < hand1.getHand().size(); x++)
			{
				cards1[x] = ImageIO.read(new File("card_back.png"));		
			}
			for(int x = 0; x < hand1.getHand().size(); x++)
			{
				int spacing = 700 / (hand1.getHand().size() + 1);
				g2.rotate(1.57, 30, 150 + spacing * (x + 1));			
				g2.drawImage(cards1[x], -20, 150 + spacing * (x + 1) - 70, 100, 140, null);
				g2.rotate(-1.57, 30, 150 + spacing * (x + 1));		
			}
		}
		
		
		if(turn == 2 || turn == -1)
		{
			g2.setColor( new Color(11, 102, 35));
			g2.fillRect(0, -20, 1600, 140);
			for(int x = 0; x < hand2.getHand().size(); x++)
			{
				cards2[x] = ImageIO.read(new File("card_back.png"));		
			}
			for(int x = 0; x < hand2.getHand().size(); x++)
			{
				int spacing = 1200 / (hand2.getHand().size() + 1);
				g2.drawImage(cards2[x], 200 + spacing * (x + 1) - 50, -20, 100, 140, null);		
			}
		}
		
		
		if(turn == 3 || turn == -1)
		{
			g2.setColor( new Color(11, 102, 35));
			g2.fillRect(1460, 0, 140, 1000);
			for(int x = 0; x < hand3.getHand().size(); x++)
			{
				cards3[x] = ImageIO.read(new File("card_back.png"));
				
				
			}
			for(int x = 0; x < hand3.getHand().size(); x++)
			{
				int spacing = 700 / (hand3.getHand().size() + 1);
				g2.rotate(-1.57, 1530, 150 + spacing * (x + 1));
				
				g2.drawImage(cards3[x], 1480, 150 + spacing * (x + 1) - 70, 100, 140, null);
				g2.rotate(1.57, 1530, 150 + spacing * (x + 1));		
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private File getCardFile(Card card)

	{	
		String colorText = "";
		String IDText = "_";
		
		if(card.getID() == "W" && card.getColor() == Color.BLACK)
		{
			IDText = "W";
		}
		else if(card.getID() == "D4" && card.getColor() == Color.BLACK)
		{
			IDText = "D4";
		}
		else
		{
			IDText += card.getID();
		}

			if(card.getColor() == Color.RED)
				colorText = "R";
			else if(card.getColor() == Color.BLUE)
				colorText = "B";
			else if(card.getColor() == Color.GREEN)
				colorText = "G";
			else if(card.getColor() == Color.YELLOW)
				colorText = "Y";
			else if(card.getColor() == Color.BLACK)
				colorText = "";
		
		return new File("card_" + colorText + IDText + ".png");
	}
	
	public void setTopOfDiscard(Card card)
	{
		topOfDiscard = card;
	}
	
	public void displayGameOver(int handID) throws IOException
	{
		System.out.println("Displaying GameOver");
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g; 
		
		
		textBox = ImageIO.read(new File("win" + handID + ".png"));
		
		g2.drawImage(textBox, 650, 200, 400, 200, null);
	}
}
