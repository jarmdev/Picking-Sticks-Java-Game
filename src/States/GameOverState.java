package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Audio.SoundManager;
import Entities.GamePlayStatus;
import Image.ImageLoader;
import StateManagement.State;

public class GameOverState extends State
{
	private static boolean menu = true;
	
	private static int stickX = 85;
	private static int stickY = 320;
	
	private BufferedImage stick;
	private ImageLoader im;

	public GameOverState()
	{
		super("gameOver");
		im = new ImageLoader();
		stick = im.loadImage("stick.png");
	}
	
	public void update()
	{
	}
	
	public void render(Graphics2D g)
	{
		// BACKGROUND
		
		g.setColor(new Color(39, 177, 192, 200));
		g.fillRect(0, 0, 400, 500);
		
		g.setColor(Color.BLUE.brighter());
		g.setFont(new Font("Aharoni", Font.ITALIC, 40));
		
		// TITLE
		
		g.drawString("Time Over", 100, 60);
		g.setColor(Color.YELLOW);
		g.drawString("Time Over", 102, 62);
		
		// RESULTS BOX
		
		g.setColor(new Color(128, 64, 0, 200));
		g.fillRect(15, 140, 360, 120);
		
		g.setColor(new Color(39, 177, 192, 200));
		g.setFont(new Font("Aharoni", Font.ITALIC, 24));
		g.setColor(Color.BLUE.brighter());
		g.drawString("Sticks Collected: " + GamePlayStatus.getSticks(), 100, 180);
		g.setColor(Color.YELLOW);
		g.drawString("Sticks Collected: " + GamePlayStatus.getSticks(), 102, 182);
		
		g.setColor(Color.BLUE.brighter());
		g.drawString("Rank Reached: " + GamePlayStatus.getRank(), 72, 230);
		g.setColor(Color.YELLOW);
		g.drawString("Rank Reached: " + GamePlayStatus.getRank(), 74, 232);
		
		// OPTIONS
		
		g.setFont(new Font("Aharoni", Font.ITALIC, 32));
		g.setColor(Color.BLUE.brighter());
		g.drawString("Main Menu", 110, 350);
		g.setColor(Color.YELLOW);
		g.drawString("Main Menu", 112, 352);
		
		g.setColor(Color.BLUE.brighter());
		g.drawString("Exit", 160, 410);
		g.setColor(Color.YELLOW);
		g.drawString("Exit", 162, 412);
		
		// STICK
		
		g.drawImage(stick, stickX, stickY, null);
	}
	
	public static void up()
	{
		menu = true;
		
		if(stickX == 140 && stickY == 380)
		{
			stickX = 85;
			stickY = 320;
			SoundManager.play("swap");
		}
	}
	
	public static void down()
	{
		menu = false;
		
		if(stickX == 85 && stickY == 320)
		{
			stickX = 140;
			stickY = 380;
			SoundManager.play("swap");
		}
	}
	
	public static boolean getMenuCondition()
	{
		return menu;
	}
	
}
