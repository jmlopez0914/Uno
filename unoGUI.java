import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class unoGUI {
	public static void main(String[] args)
	{
		unoGUI2();
	}
	
	public static void unoGUI2(/*  Hand hand1, Hand hand2, Hand hand3, Hand hand4  */)
	{
		JFrame frame = new JFrame("Game");
		frame.setVisible(true);
		frame.setResizable(false);	
		Color background = new Color(11, 102, 35);
		frame.getContentPane().setBackground(background);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(1600, 1000); 	
		
		
		
		
	}
	
	public void takeCard(/*  Card card, Hand hand  */)
	{
		
	}
	
	public void placeCard(/* Card card, Hand hand */)
	{
		
	}
	
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g; 
		
		
	}
}