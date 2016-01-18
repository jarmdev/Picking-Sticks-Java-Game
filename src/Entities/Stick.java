package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Audio.SoundManager;
import Image.ImageLoader;

public class Stick 
{
	private BufferedImage stick;
	
	private static Rectangle stickPosition;
	
	private int stickX;
	private int stickY;
	
	private Random rnd;
	
	public Stick()
	{
		ImageLoader im = new ImageLoader();
		
		stick = im.loadImage("stick.png");
		
		rnd = new Random();
		
		stickX = rnd.nextInt(360);
		stickY = 40 + rnd.nextInt(390);
		
		
		stickPosition = new Rectangle(stickX, stickY, 10, 40);
	}
	
	public void update()
	{
		if(Player.getPlayerPosition().intersects(stickPosition))
		{
			stickX = rnd.nextInt(385);
			stickY = 40 + rnd.nextInt(390);
			
			stickPosition.setBounds(stickX, stickY, 10, 40);
			SoundManager.play("pick");
			
			GamePlayStatus.addStick();
		}
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(stick, stickX, stickY, 12, 45, null);
	}
	
	public static Rectangle getStickPosition()
	{
		return stickPosition;
	}
}
