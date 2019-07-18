package ProjectWaterman;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

public class Map 
{
	public static BufferedImage loadMap(String fileName)//loads the file
	{
		File f = new File(fileName);
		BufferedImage image = null;
		try 
		{
			 image = ImageIO.read(f);
			 return image;
		}
		catch (IOException e)
		{
			System.out.println("Could not find image file");
			e.printStackTrace();
		}
		return image;
	}
	//test output method, probably won't be in final program
	public static void outputImage(BufferedImage i)
	{
		File f = new File("C:\\output.jpg");
		try 
		{
			ImageIO.write(i,"jpg",f);
		} catch (IOException e) 
		{
			System.out.println("Could not output file");
			e.printStackTrace();
		}
	}
	//looks for ocean(blue) pixels and sets all pixels that are not blue to green
	public static BufferedImage process(BufferedImage img)
	{
		BufferedImage outputImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		//creates a new blank image that is the same size at the original which we will fill in
		for(int i = 0; i < img.getHeight(); i++)
		{
			for(int j = 0; j < img.getWidth(); j++)
			{//height/width increment statements
				String clr = Integer.toHexString(img.getRGB(j, i)).substring(2);//converts int RGB values of the pixel to hex
			    Color c = Color.decode("#"+clr.toUpperCase());//converts hex to a color object which we will extract the blue value from
				if(c.getBlue() >= 220)//adjust this for different blue values(different ocean hues
				{
					outputImage.setRGB(j, i, -3610881);//sets ocean to a more oceanic blue
				}
				else
				{
					outputImage.setRGB(j, i, -6881386);//sets non-ocean pixels to green, denoting land
				}
			}
		}
		return outputImage;
	}
	public static void main(String[] args)
	{
		BufferedImage image = loadMap(args[0]);
		image = process(image);
		outputImage(image);
	}
}
