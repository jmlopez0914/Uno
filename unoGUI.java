import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class unoGUI extends JComponent /*implements ActionListener*/{	
	private BufferedImage cards0[] = new BufferedImage[108];
	private BufferedImage cards1[] = new BufferedImage[108];
	private BufferedImage cards2[] = new BufferedImage[108];
	private BufferedImage cards3[] = new BufferedImage[108];
	private BufferedImage deck = null;
	private BufferedImage discard[] = new BufferedImage[108];
	private BufferedImage arrow = null;
	private Hand hand0, hand1, hand2, hand3;
	private Card topOfDiscard;
	
	
	
	public unoGUI(Hand hand0, Hand hand1, Hand hand2, Hand hand3, Card topDiscard, JFrame frame)
	{
		//Timer t = new Timer(5, this);	
		JButton unoButton = new JButton("UNO!!");	
		unoButton.setText("UNO!!");
		unoButton.setBounds(1500, 950, 100, 50);
		
		unoButton.setLocation(1500, 950);	
		frame.add(unoButton);
		System.out.println(unoButton.getSize());
		System.out.println("\t" + unoButton.getLocation());	
		
		this.hand0 = hand0;
		this.hand1 = hand1;
		this.hand2 = hand2;
		this.hand3 = hand3;
		
		topOfDiscard = topDiscard;
		
		
		
		
		
		//t.start();
	}	
	
	/*public void getInput()
	{
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				press = true;
			}
		});
		
	}*/
	
	/*public void takeCard(Card card, Hand hand)
	{
		int cardID = card.getID();
		Color cardColor = card.getColor();
		int handID = hand.getID();
	}
	*/
	public void placeCard(Card card, Hand hand) throws IOException
	{
		draw(hand.getID());
	}
	
	
	/*private void movingCard(int ID, int Color)
	{
		
	}*/
	
	public void draw(int turn) throws IOException
	{
		Graphics g = getGraphics();
		Graphics2D g2 = (Graphics2D)g; 
			
		//repaint(12);
		
		
		//g2.fillRect(-200, -200, 1800, 1400);
		
		if(turn == 0) 
			g2.drawLine(750, 860, 850, 865);
		
		//green rectangle over the middle
		g2.setColor( new Color(11, 102, 35));
		g2.fillRect(200, 200, 1000, 600); //resize and move as needed
		
		arrow = ImageIO.read(new File("arrow.png"));
		if (turn != -1) {
			g2.rotate((turn+1)*Math.PI/2, 800, 500);
			g2.drawImage(arrow, 800, 500, null);
			g2.rotate(-(turn+1)*Math.PI/2, 800, 500);
		}

		deck = ImageIO.read(new File("card_back.png"));
		g2.drawImage(deck, 700, 430, 100, 140, null);
		

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
		
		if(card.getID() == "W")
		{
			IDText = "W";
		}
		else if(card.getID() == "D4")
		{
			IDText = "D4";
		}
		else
		{
			if(card.getColor() == Color.RED)
				colorText = "R";
			else if(card.getColor() == Color.BLUE)
				colorText = "B";
			else if(card.getColor() == Color.GREEN)
				colorText = "G";
			else if(card.getColor() == Color.YELLOW)
				colorText = "Y";
			
			IDText += card.getID();
		}
		
		//System.out.println("card_" + colorText + IDText + ".png");
		
		return new File("card_" + colorText + IDText + ".png");
		
		//return new File("card_" + card.getID() + ".png");
	}
	
	public void setTopOfDiscard(Card card)
	{
		topOfDiscard = card;
	}
}
