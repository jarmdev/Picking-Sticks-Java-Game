package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Audio.SoundManager;
import Entities.Player;
import Image.ImageLoader;
import StateManagement.State;
import StateManagement.StateManager;

public class CharacterSelectState extends State
{
	private ImageLoader im;
	private BufferedImage character;
	
	private static int selectionX = 53;
	
	public CharacterSelectState()
	{
		super("chSelect");
		
		im = new ImageLoader();
		left();
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
		g.setFont(new Font("Arial", Font.ITALIC, 35));
		g.drawString("Select your character!", 18, 50);
		g.setColor(Color.YELLOW);
		g.drawString("Select your character!", 20, 52);
		
		// SELECTION BOX

		g.setColor(Color.WHITE);
		g.fillRect(selectionX, 173, 44, 44);
		
		// CHARACTERS BOXES
		
		g.setColor(Color.GREEN);
		g.fillRect(55, 175, 40, 40);
		g.fillRect(180, 175, 40, 40);
		g.fillRect(305, 175, 40, 40);
		
		// CHARACTERS
		
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		
		character = im.loadImage("ch1.png");
		g.drawImage(character, 59, 179, null);
		g.setColor(Color.BLUE.brighter());
		g.drawString("Normal", 36, 245);
		g.setColor(Color.YELLOW);
		g.drawString("Normal", 38, 247);
		
		character = im.loadImage("ch2.png");
		g.drawImage(character, 184, 179, null);
		g.setColor(Color.BLUE.brighter());
		g.drawString("Old Style", 158, 245);
		g.setColor(Color.YELLOW);
		g.drawString("Old Style", 160, 247);
		
		character = im.loadImage("ch3.png");
		g.drawImage(character, 308, 179, null);
		g.setColor(Color.BLUE.brighter());
		g.drawString("Masked", 290, 245);
		g.setColor(Color.YELLOW);
		g.drawString("Masked", 292, 247);
		
		// INSTRUCTIONS
		
		g.setColor(Color.BLUE.brighter());
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.drawString("Pick the character and then press ENTER", 10, 400);
		g.setColor(Color.YELLOW);
		g.drawString("Pick the character and then press ENTER", 11, 401);
	}
	
	public static void left()
	{
		if(selectionX == 53)
		{
			selectionX = 53;
			setPlayer();
		}
		
		else if(selectionX == 178)
		{
			SoundManager.play("swap");
			selectionX = 53;
			setPlayer();
		}
		else
		{
			SoundManager.play("swap");
			selectionX = 178;
			setPlayer();
		}
	}
	
	public static void right()
	{
		if(selectionX == 53)
		{
			SoundManager.play("swap");
			selectionX = 178;
			setPlayer();
		}
		else if(selectionX == 178)
		{
			SoundManager.play("swap");
			selectionX = 303;
			setPlayer();
		}
		else
		{
			selectionX = 303;
			setPlayer();
		}
	}
	
	private static void setPlayer()
	{
		if(selectionX == 53)
		{	
			Player.setCharacter(1);
		}
		
		else if(selectionX == 178)
		{
			Player.setCharacter(2);
		}
		
		else
		{
			Player.setCharacter(3);
		}
		
		StateManager.addGameState();
	}
}
