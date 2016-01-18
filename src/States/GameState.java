package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.GamePlayStatus;
import Entities.Player;
import Entities.Stick;
import Image.ImageLoader;
import StateManagement.State;

public class GameState extends State
{
	private BufferedImage background;
	private ImageLoader im;
	
	private Player player;
	private Stick stick;
	private GamePlayStatus status;
	
	private static Boolean paused = false;
	
	public GameState()
	{
		super("game");
		
		im = new ImageLoader();
		
		background = im.loadImage("bg.png");
		
		player = new Player();
		stick = new Stick();
		status = new GamePlayStatus();
	
	}
	
	public static void pauseGame()
	{
		paused = true;
		GamePlayStatus.pauseTime();
	}
	
	public static void resumeGame()
	{
		paused = false;
		GamePlayStatus.resumeTime();
	}
	
	@Override
	public void update()
	{	
		stick.update();
		status.update();
		player.update(System.currentTimeMillis());
	}
	
	@Override
	public void render(Graphics2D g)
	{	
		g.drawImage(background, 0, 0, 400, 500, null);
		
		player.render(g);
		stick.render(g);
		status.render(g);
		
		if(paused)
		{
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, 400, 500);
			g.setColor(Color.WHITE.brighter());
			g.setFont(new Font("Arial", Font.ITALIC, 25));
			g.drawString("-Paused-", 140, 220);
		}
		
	}

}
