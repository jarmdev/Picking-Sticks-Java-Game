package Image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	BufferedImage image;
	
	public ImageLoader() {}
	
	public BufferedImage loadImage(String imageName)
	{
		URL path = this.getClass().getResource("Images/" + imageName);
		
		try
		{
			image = ImageIO.read(path);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
}
