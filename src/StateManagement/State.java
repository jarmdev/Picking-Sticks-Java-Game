package StateManagement;

import java.awt.Graphics2D;

public class State 
{
	private String stateName;
	
	public State(String name)
	{
		stateName = name;
	}
	
	public String getName()
	{
		return stateName;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g)
	{
		
	}
}
