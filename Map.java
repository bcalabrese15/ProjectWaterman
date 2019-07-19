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
	public static BufferedImage continentize(BufferedImage img)
	{
		BufferedImage outputImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		//creates a new blank image that is the same size at the original which we will fill in
		for(int i = 0; i < img.getHeight(); i++)
		{
			for(int j = 0; j < img.getWidth(); j++)
			{//height/width increment statements
				String clr = Integer.toHexString(img.getRGB(j, i)).substring(2);//converts int RGB values of the pixel to hex
			    Color c = Color.decode("#"+clr.toUpperCase());//converts hex to a color object which we will extract the blue value from
				if(c.getBlue() >= 234)//adjust this for different blue values(different ocean hues
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
	public static BufferedImage coastlineMaker(BufferedImage img)
	{
		BufferedImage outputImage = img;//have to set it to the input image or else it'll be a black map
		for(int i = 1; i < img.getHeight(); i++)
		{
			for(int j = 1; j < img.getWidth(); j++)
			{
				String clr = Integer.toHexString(img.getRGB(j, i)).substring(2);
				String adjneg10 = Integer.toHexString(img.getRGB(j-1, i)).substring(2);//pixel to the left of current
				String adj0neg1 = Integer.toHexString(img.getRGB(j, i-1)).substring(2);//pixel below the current
				Color c = Color.decode("#"+clr.toUpperCase());
				Color cadjneg10 = Color.decode("#"+adjneg10.toUpperCase());
				Color cadj0neg1 = Color.decode("#"+adj0neg1.toUpperCase());
				if(cadjneg10.getBlue() >= 234 && c.getBlue() < 150)//checks if the current pixel is a coastline pixel
				{
					outputImage.setRGB(j, i, 0);
				}
				else if(cadj0neg1.getBlue() >= 234 && c.getBlue() < 150)
				{
					outputImage.setRGB(j, i, 0);
				}
				else if(cadjneg10.getBlue() < 200 && cadjneg10.getBlue() > 0 && c.getBlue() >= 234)//checks if current pixel is an ocean pixel
				{
					outputImage.setRGB(j, i, 0);
				}
				else if(cadj0neg1.getBlue() < 200 && cadj0neg1.getBlue() > 0 && c.getBlue() >= 234)
				{
					outputImage.setRGB(j, i, 0);
				}
				
			}
		}
		return outputImage;
	}
	public static void main(String[] args)
	{
		BufferedImage image = loadMap(args[0]);
		image = continentize(image);
		image = coastlineMaker(image);
		outputImage(image);
	}
}
