
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
                // Just to set the size of the object
                super();
                // Load an image
                loadImage();
        }

        public void paint(Graphics g)
        {
                super.paint(g);

                Dimension size = getSize();
                upSideDown((Graphics2D) g, size);
                g.drawImage(img, 0, 0, size.width, size.height, 0, 0, img.getWidth(null), img.getHeight(null), null);
        }

        /**
         *
         * Turns the graphics up size down
         * @param g Graphics2D
         * @param size Dimension
         */
        public void upSideDown(Graphics2D g, Dimension size)
        {
                // It works the same as to convert matrices (not our Matrix objects)
                g.rotate(Math.PI);
                g.translate(-size.width, -size.height);
        }

        public void loadImage()
        {
                // Get the full url of the image (C:\Users\...\images\boom.gif)
                URL url = Boom.class.getResource("images/boom.gif");
                try
                {
                        // Load the image at location url
                        img = ImageIO.read(url);
                } catch (Exception e)
                {
                }
        }
}
