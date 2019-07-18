package ProjectWaterman;

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
	public static void main(String[] args)
	{
		BufferedImage image = loadMap(args[0]);
		outputImage(image);
	}
}
