package Audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound 
{
	private AudioClip audio;
	
	private String name;
	
	public Sound(String name, URL path)
	{
		this.name = name;
		
		try
		{
			audio = Applet.newAudioClip(path);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public void play()
	{
		if(audio != null)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					audio.play();
				}
			}).start();
		}
	}
	
	public void loop()
	{
		if(audio != null)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					audio.loop();
				}
			}).start();;
		}
	}
	
	public void stop()
	{
		if(audio != null)
			audio.stop();
	}
}
