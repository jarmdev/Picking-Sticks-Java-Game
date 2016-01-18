package States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import Audio.SoundManager;
import Image.ImageLoader;
import StateManagement.State;
import StateManagement.StateManager;

public class IntroState extends State implements ActionListener
{
	private int alpha = 250;
	private int dAlpha = 0;
	
	private int time = 0;
	
	private boolean up = false;
		
	private Timer timer;
	
	private BufferedImage logo;
	
	public IntroState()
	{
		super("intro");
		
		ImageLoader im = new ImageLoader();
		
		logo = im.loadImage("logo.png");
		
		timer = new Timer(1000, this);
		timer.start();
	}
	
	public void update()
	{
		alpha += dAlpha;

		if(time == 1)
		{
			timer.stop();
			dAlpha = -2;
			time = 3;
		}
		
		if(alpha < 3)
		{
			timer.start();
			dAlpha = 0;
		}
		
		if(time == 5)
		{
			timer.stop();
			dAlpha = 2;
			up = true;
			time = 6;
		}
		
		if(time == 7)
		{
			timer.stop();
			up = false;
			StateManager.setCurrentState("menu");
			SoundManager.loop("menu");
		}
		
		if(alpha > 248)
		{
			if(up)
			{
				timer.start();
				dAlpha = 0;
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(new Color(0, 0, 0, alpha));
		g.drawImage(logo, 25, 20, null);
		g.fillRect(0, 0, 400, 500);
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		time += 1;
		System.out.println(time);
	}

}
