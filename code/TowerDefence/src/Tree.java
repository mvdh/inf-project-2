import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class Tree extends Field
{
    private BufferedImage img = null;

    public Tree()
    {
        // Just to set the size of the object
        super();
        // Load an image
        loadImage();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Dimension size = getSize();
        // upSideDown((Graphics2D) g, size);
        // mirrored((Graphics2D) g, size);
        if (img != null)
        {
            g.drawImage(img, 0, 0, size.width, size.height, 0, 0, img
                    .getWidth(null), img.getHeight(null), null);
        }
    }

    /**
     * Turns the graphics up size down
     * 
     * @param g Graphics2D
     * @param size Dimension
     */
    public void upSideDown(Graphics2D g, Dimension size)
    {
        // It works the same as to convert matrices (not our Matrix objects)
        g.scale(1, -1);
        g.translate(0, -size.height);
    }

    /**
     * @param g Graphics2D
     * @param size Dimension
     */
    public void mirrored(Graphics2D g, Dimension size)
    {
        g.scale(-1, 1);
        g.translate(-size.width, 0);
    }

    /**
     * Loads the image for the Tree object
     */
    public void loadImage()
    {
        // Get the full url of the image (C:\Users\...\images\boom.png)
        URL url = Tree.class.getResource("images/boom.png");
        try
        {
            // Load the image at location url
            img = ImageIO.read(url);
        }
        catch (Exception e)
        {}
    }
}
