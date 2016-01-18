package Entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Audio.SoundManager;
import StateManagement.StateManager;

public class GamePlayStatus implements ActionListener 
{
	private static int sticks = 0;
	private int minutes = 3;
	private int seconds = 00;
	private static String rank = "Rookie";
	private static Timer timer;
	
	public GamePlayStatus() 
	{
		timer = new Timer(1020, this);
	}
	
	public void update()
	{
		if(sticks > 10)
			rank = "Prospect";
		
		if(sticks > 25)
			rank = "Pro";
		
		if(sticks > 40)
			rank = "Legend";
		
		if(sticks > 60)
			rank = "Master";
		
		if(minutes == 0 && seconds == 0)
		{
			SoundManager.stop("game");
			SoundManager.play("end");
			SoundManager.loop("menu");
			
			StateManager.setCurrentState("gameOver");
			
			Player.setVelX(0);
			Player.setVelY(0);
			Player.playerX = 180;
			Player.playerY = 220;
			Player.movingDown();
		}
	}
	
	public void render(Graphics2D g)
	{
		// INFO BOXES
		
		g.setColor(new Color(128, 64, 0, 200));
		g.fillRect(0, 0, 394, 40);
		g.setColor(new Color(39, 177, 192, 200));
		g.fillRect(7, 7, 380, 26);
		
		// INFO
		
		if(seconds < 10)
		{
			g.setFont(new Font("Arial", Font.ITALIC, 20));
			g.setColor(Color.BLUE.brighter());
			g.drawString("Time: " + minutes + ":0" + seconds, 10, 26);
			g.setColor(Color.YELLOW);
			g.drawString("Time: " + minutes + ":0" + seconds, 11, 27);
		}
		
		else
		{
			g.setFont(new Font("Arial", Font.ITALIC, 20));
			g.setColor(Color.BLUE.brighter());
			g.drawString("Time: " + minutes + ":" + seconds, 10, 26);
			g.setColor(Color.YELLOW);
			g.drawString("Time: " + minutes + ":" + seconds, 11, 27);
		}
		
		g.setColor(Color.BLUE.brighter());
		g.drawString("Sticks: " + sticks, 125, 26);
		g.setColor(Color.YELLOW);
		g.drawString("Sticks: " + sticks, 126, 27);
		
		g.setColor(Color.BLUE.brighter());
		g.drawString("Rank: " + rank, 240, 26);
		g.setColor(Color.YELLOW);
		g.drawString("Rank: " + rank, 241, 27);
	}
	
	public static void addStick()
	{
		sticks++;
	}
	
	public static void startTimer()
	{
		timer.start();
	}
	
	public static int getSticks()
	{
		return sticks;
	}
	
	public static String getRank()
	{
		return rank;
	}
	
	public static void reset()
	{
		sticks = 0;
		rank = "Rookie";
	}
	
	public static void pauseTime()
	{
		timer.stop();
	}
	
	public static void resumeTime()
	{
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if(seconds == 00)
		{
			minutes -= 1;
			seconds = 60;
		}
		
		seconds -= 1;
	}
}
