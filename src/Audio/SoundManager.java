package Audio;

import java.util.ArrayList;

public class SoundManager 
{
	private static ArrayList<Sound> sounds;
	
	public SoundManager()
	{
		sounds = new ArrayList<Sound>();
		init();
	}
	
	public void init()
	{
		sounds.add(new Sound("pick", this.getClass().getResource("Sounds/pick.wav")));
		sounds.add(new Sound("swap", this.getClass().getResource("Sounds/swap.wav")));
		sounds.add(new Sound("game", this.getClass().getResource("Sounds/game.wav")));
		sounds.add(new Sound("menu", this.getClass().getResource("Sounds/menu.wav")));
		sounds.add(new Sound("selection", this.getClass().getResource("Sounds/select.wav")));
		sounds.add(new Sound("end", this.getClass().getResource("Sounds/end.wav")));
	}
	
	public static void play(String soundName)
	{
		for(int i = 0; i < sounds.size(); i++)
		{
			if(sounds.get(i).getName().equals(soundName))
				sounds.get(i).play();
		}
	}
	
	public static void loop(String soundName)
	{
		for(int i = 0; i < sounds.size(); i++)
		{
			if(sounds.get(i).getName().equals(soundName))
				sounds.get(i).loop();
		}
	}
	
	public static void stop(String soundName)
	{
		for(int i = 0; i < sounds.size(); i++)
		{
			if(sounds.get(i).getName().equals(soundName))
				sounds.get(i).stop();
		}
	}
}
