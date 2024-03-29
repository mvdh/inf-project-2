import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class Brick extends Field
{
	private BufferedImage img = null;

	public Brick(BufferedImage bg)
	{
		// Just to set the size of the object
		super(bg);
		// Load an image
		loadImage();
		setWalkable(false);
		setFlyable(false);
		setBuildable(false);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Dimension size = getSize();
		if (img != null)
		{
			g.drawImage(img, 0, 0, size.width, size.height, 0, 0, img.getWidth(null), img.getHeight(null), null);
		}
	}

	/**
	 * Loads the image for the Tree object
	 */
	public void loadImage()
	{
		// Get the full url of the image (C:\Users\...\images\castle.png)
		URL url = getClass().getResource("images/castle_grey.png");
		MediaTracker tracker = new MediaTracker(this);
		try
		{
			// Load the image at location url
			img = ImageIO.read(url);
			tracker.addImage(img, 0);
		}
		catch (Exception e)
		{}
		
		try
        {
            tracker.waitForAll();
        } 
		catch (InterruptedException ie)
        {}
	}
}
