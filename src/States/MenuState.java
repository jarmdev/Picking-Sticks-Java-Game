package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Audio.SoundManager;
import Image.ImageLoader;
import Image.SpriteSheetManager;
import StateManagement.State;

public class MenuState extends State
{
	private BufferedImage stick;
	private BufferedImage stick2;
	private BufferedImage player;
	
	private ImageLoader im;
	
	private static int stickX = 90;
	private static int stickY = 320;
	
	private static boolean exit = false;
	
	public MenuState()
	{
		super("menu");
		
		im = new ImageLoader();
		
		stick = im.loadImage("stick.png");
		stick2 = im.loadImage("stick2.png");
		player = im.loadImage("character1.png");
		
		SpriteSheetManager ssm = new SpriteSheetManager(player);
		
		player = ssm.grabSprite(64, 64, 32, 32);
	}
	
	@Override
	public void update()
	{
	}
	
	@Override
	public void render(Graphics2D g)
	{
		//BACKGROUND
		
		g.setColor(new Color(39, 177, 192));
		g.fillRect(0, 0, 400, 500);
		
		// TITLE
		
		g.setColor(Color.BLUE.brighter());
		g.setFont(new Font("Arial", Font.ITALIC, 50));
		g.drawString("Pickin' Sticks", 50, 90);
		g.setColor(Color.YELLOW);
		g.drawString("Pickin' Sticks", 53, 93);
		
		// DRAWINGS
		
		g.drawImage(stick2, 250, 190, 40, 40, null);
		g.drawImage(player, 135, 190, 40, 40, null);
		
		g.setColor(Color.BLACK);
		g.drawLine(100, 200, 125, 200);
		g.drawLine(90, 210, 115, 210);
		g.drawLine(100, 220, 125, 220);
		
		// OPTIONS
		
		g.setColor(Color.BLUE.brighter());
		g.setFont(new Font("Arial", Font.ITALIC, 32));
		g.drawString("Start Game", 110, 350);
		g.setColor(Color.YELLOW);
		g.drawString("Start Game", 112, 352);
		
		g.setColor(Color.BLUE.brighter());
		g.drawString("Exit", 160, 410);
		g.setColor(Color.YELLOW);
		g.drawString("Exit", 162, 412);
		
		g.drawImage(stick, stickX, stickY, null);
	}
	
	public static void moveStickToExit()
	{
		if(stickX != 140 && stickY != 380)
		{
			stickX = 140;
			stickY = 380;
			SoundManager.play("swap");
		}
		
		exit = true;
	}
	
	public static void moveStickToSG()
	{
		if(stickX != 90 && stickY != 320)
		{
			stickX = 90;
			stickY = 320;
			SoundManager.play("swap");
		}
		
		exit = false;
	}
	
	public static boolean exit()
	{
		return exit;
	}
	
	
}
