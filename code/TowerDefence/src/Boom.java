import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Boom extends Field
{	
	private BufferedImage img = null;
	
	public Boom()
	{
		super();
		loadImage();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Dimension size = getSize();
		g2.drawImage(img, 0, 0, size.width, size.height, 0, 0, img.getWidth(null), img.getHeight(null), null);
	}
	
	public void loadImage()
	{
		URL url = Boom.class.getResource("images/boom.gif");
        try 
        {
                img =  ImageIO.read(url);
        } 
        catch (Exception e) {}
	}
}
