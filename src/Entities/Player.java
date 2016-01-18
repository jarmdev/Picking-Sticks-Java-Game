package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Image.ImageLoader;
import Image.SpriteSheetManager;

public class Player 
{
	private static ArrayList<BufferedImage> currentMovement;
	
	private static ArrayList<BufferedImage> moveUp;
	private static ArrayList<BufferedImage> moveDown;
	private static ArrayList<BufferedImage> moveRight;
	private static ArrayList<BufferedImage> moveLeft;
	
	private BufferedImage sprite;
	
	private int currentFrame = 0;
	public static double playerX = 180, playerY = 220;
	
	private static double velX;
	private static double velY;
	
	private static int character = 1;
	
	private volatile boolean moving = false;
	private volatile boolean forward;
	
	private long speed = 60;
	private long previousTime;
	
	private static Rectangle playerPosition;
	
	public Player()
	{
		currentMovement = new ArrayList<BufferedImage>();
		
		moveUp = new ArrayList<BufferedImage>();
		moveDown = new ArrayList<BufferedImage>();
		moveRight = new ArrayList<BufferedImage>();
		moveLeft = new ArrayList<BufferedImage>();
		
		currentFrame = 1;
		
		playerPosition = new Rectangle();
		
		init();
	}
	
	private void init()
	{
		ImageLoader im = new ImageLoader();
		
		SpriteSheetManager ssm = new SpriteSheetManager(im.loadImage("character" + character + ".png"));
		
		// UP MOVEMENTS

		moveUp.add(ssm.grabSprite(0, 96, 32, 32));
		moveUp.add(ssm.grabSprite(32, 96, 32, 32));
		moveUp.add(ssm.grabSprite(64, 96, 32, 32));

		//DOWN MOVEMENTS

		moveDown.add(ssm.grabSprite(0, 0, 32, 32));
		moveDown.add(ssm.grabSprite(32, 0, 32, 32));
		moveDown.add(ssm.grabSprite(64, 0, 32, 32));

		//RIGHT MOVEMENTS

		moveRight.add(ssm.grabSprite(0, 64, 32, 32));
		moveRight.add(ssm.grabSprite(32, 64, 32, 32));
		moveRight.add(ssm.grabSprite(64, 64, 32, 32));

		//LEFT MOVEMENTS

		moveLeft.add(ssm.grabSprite(0, 32, 32, 32));
		moveLeft.add(ssm.grabSprite(32, 32, 32, 32));
		moveLeft.add(ssm.grabSprite(64, 32, 32, 32));
		
		currentMovement = moveDown;
		sprite = currentMovement.get(1);
	}
	
	public void update(long time)
	{	
		// COLLISION BOUNDS
		
		if(playerX < 0)
			playerX = 0;
		
		if(playerX > 365)
			playerX = 365;
		
		if(playerY > 438)
			playerY = 438;
		
		if(playerY < 37)
			playerY = 37;
		
		playerX += velX;
		playerY += velY;
		
		playerPosition.setBounds((int) playerX + 5, (int) playerY, 22, 32);
		
		if((time - previousTime) >= speed)
		{
			
			if(velX == 0 && velY == 0)
				moving = false;
			else
				moving = true;
			
			if(moving)
			{
				if(currentFrame == 0)
					forward = true;
				if(currentFrame == 2)
					forward = false;
				
				if(forward)
					currentFrame++;
				else
					currentFrame--;
				
				sprite = currentMovement.get(currentFrame);
				
				previousTime = time;
			}
			
			else
				sprite = currentMovement.get(1);
		}
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(sprite, (int) playerX, (int) playerY, null);
	}
	
	public static void setVelX(double v)
	{
		velX = v;
	}
	
	public static void setVelY(double v)
	{
		velY = v;
	}
	
	public static void setCharacter(int characterNumber)
	{
		character = characterNumber;
	}
	
	public static void movingUp()
	{
		currentMovement = moveUp;
	}
	
	public static void movingDown()
	{
		currentMovement = moveDown;
	}
	
	public static void movingRight()
	{
		currentMovement = moveRight;
	}
	
	public static void movingLeft()
	{
		currentMovement = moveLeft;
	}
	
	public static Rectangle getPlayerPosition()
	{
		return playerPosition;
	}
}
