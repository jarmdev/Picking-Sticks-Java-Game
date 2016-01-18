package StateManagement;

import java.awt.Graphics2D;
import java.util.ArrayList;

import States.CharacterSelectState;
import States.GameOverState;
import States.GameState;
import States.IntroState;
import States.MenuState;

public class StateManager 
{
	private static ArrayList<State> states;
	
	private static int currentState;
	
	public StateManager()
	{
		states = new ArrayList<State>();
		init();
	}
	
	public void init()
	{
		states.add(new MenuState());
		states.add(new CharacterSelectState());
		states.add(new GameOverState());
		states.add(new IntroState());
	}
	
	public static void setCurrentState(String stateName)
	{	
		for(int i = 0; i < states.size(); i++)
		{
			if(states.get(i).getName().equals(stateName))
				currentState = i;
		}
	}
	
	public static String getCurrentState()
	{
		return states.get(currentState).getName();
	}
	
	public static void updateCurrentstate()
	{
		states.get(currentState).update();
	}
	
	public static void renderCurrentState(Graphics2D g)
	{
		states.get(currentState).render(g);
	}
	
	public static void addGameState()
	{
		states.add(new GameState());
	}
	

}
