package Image;

import java.awt.image.BufferedImage;

public class SpriteSheetManager 
{
	BufferedImage spriteSheet;
	
	public SpriteSheetManager(BufferedImage sheet)
	{
		spriteSheet = sheet;
	}
	
	public BufferedImage grabSprite(int x, int y, int width, int height)
	{
		BufferedImage tempImage;
		tempImage = spriteSheet.getSubimage(x, y, width, height);
		
		return tempImage;
	}

}
