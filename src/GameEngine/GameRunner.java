package GameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Audio.SoundManager;
import Entities.GamePlayStatus;
import Entities.Player;
import StateManagement.StateManager;
import States.CharacterSelectState;
import States.GameOverState;
import States.GameState;
import States.MenuState;

public class GameRunner extends JPanel implements Runnable, KeyListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// SCREEN SIZE
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	// ANIMATION THREAD
	
	private Thread gameThread;
	
	// DOUBLE BUFFERING COMPONENTS
	
	private Image dbImage;
	private Graphics2D g;
	
	// STATUS BOOLEANS
	
	private volatile boolean running = false;
	private volatile boolean gameOver = false;
	private volatile boolean paused = false;
	
	// UPDATING TIME COMPONENTS
	
	double targetTime = (1.0/60.0);
	double nextTime;
	double currTime;
	
	// GAME STATE MANAGER AND SOUND MANAGER INITIALIZATION
	
	StateManager sm = new StateManager();
	SoundManager soundM = new SoundManager();
	
	
	
	public GameRunner()
	{
		super();
		setSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		StateManager.setCurrentState("intro");
	}
	
	public void addNotify()
	{
		super.addNotify();
		
		if(gameThread == null)
		{
			gameThread = new Thread(this);
			gameThread.start();
		}
	}
	
	public void update()
	{
		if(!paused && !gameOver)
		{
			StateManager.updateCurrentstate();
		}
	}
	
	public void render()
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		StateManager.renderCurrentState(g);
	}
	
	public void drawToScreen()
	{
		Graphics g = super.getGraphics();
		g.drawImage(dbImage, 0, 0, null);
		g.dispose();
	}
	
	public void run()
	{
		running = true;
		
		dbImage = createImage(WIDTH, HEIGHT);
		
		g = (Graphics2D) dbImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		nextTime = (double) System.nanoTime() / 1000000000.0;
        
        while(running)
        {
            currTime = (double) System.nanoTime() / 1000000000.0;
           
            if(currTime >= nextTime)
            {
                nextTime += targetTime;
                update();
                
                if((currTime < nextTime))
                {
                    render();
                    drawToScreen();
                }
            }
            
            else
            {
                int sleepTime = (int) (1000.0 * (nextTime - currTime));
                

                if(sleepTime > 0)
                {
                    try
                    {
                        Thread.sleep(sleepTime);
                    }
                    catch(InterruptedException e) {}
                }
            }
        }
	}
	
	public void pauseGame()
	{
		paused = true;
	}
	
	public void resumeGame()
	{
		paused = false;
	}

	@Override
	public void keyPressed(KeyEvent kev) 
	{
		// CHARACTER SELECTION ACTIONS

		if(StateManager.getCurrentState().equals("chSelect"))
		{
			if(kev.getKeyCode() == KeyEvent.VK_LEFT)
			{
				CharacterSelectState.left();
			}

			if(kev.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				CharacterSelectState.right();
			}

			if(kev.getKeyCode() == KeyEvent.VK_ENTER)
			{
				StateManager.setCurrentState("game");
				GamePlayStatus.startTimer();
				SoundManager.play("selection");
				SoundManager.stop("menu");
				SoundManager.loop("game");
			}
		}
		
		// MENU ACTIONS
		
		if(StateManager.getCurrentState().equals("menu"))
		{
			if(kev.getKeyCode() == KeyEvent.VK_UP)
			{
				MenuState.moveStickToSG();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_DOWN)
			{
				MenuState.moveStickToExit();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_ENTER)
			{
				SoundManager.play("selection");
				
				if(MenuState.exit())
					System.exit(0);
				else
					StateManager.setCurrentState("chSelect");
			}
		}
		
		// GAME ACTIONS
		
		if(StateManager.getCurrentState().equals("game"))
		{
			if(kev.getKeyCode() == KeyEvent.VK_UP)
			{
				Player.setVelY(-2);
				Player.movingUp();
			}
			
			else if(kev.getKeyCode() == KeyEvent.VK_DOWN)
			{
				Player.setVelY(2);
				Player.movingDown();
			}
			
			else if(kev.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				Player.setVelX(2);
				Player.movingRight();
			}
			
			else if(kev.getKeyCode() == KeyEvent.VK_LEFT)
			{
				Player.setVelX(-2);
				Player.movingLeft();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_P)
			{
				pauseGame();
				GameState.pauseGame();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_R)
			{
				resumeGame();
				GameState.resumeGame();
			}
		}
		
		// GAME OVER ACTIONS
		
		if(StateManager.getCurrentState().equals("gameOver"))
		{
			if(kev.getKeyCode() == KeyEvent.VK_UP)
			{
				GameOverState.up();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_DOWN)
			{
				GameOverState.down();
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_ENTER)
			{
				GamePlayStatus.reset();
				
				if(GameOverState.getMenuCondition())
				{
					StateManager.setCurrentState("menu");
					SoundManager.play("selection");
				}
				else
					System.exit(0);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent kev) 
	{
		// GAME ACTIONS

		if(StateManager.getCurrentState().equals("game"))
		{
			if(kev.getKeyCode() == KeyEvent.VK_UP)
			{
				Player.setVelY(0);
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_DOWN)
			{
				Player.setVelY(0);
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				Player.setVelX(0);
			}
			
			if(kev.getKeyCode() == KeyEvent.VK_LEFT)
			{
				Player.setVelX(0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent kev) {}
}
