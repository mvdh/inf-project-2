import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * @author Maarten van den Hoek
 */
public class FieldController extends Container
{
    public FieldController()
    {
        init();
    }

    public void init()
    {
        setLayout(null);
        setSize(680, 180);

        // Get the full url of the image (C:\Users\...\images\boom.gif)
        BufferedImage img = null;
        BufferedImage img2 = null;
        URL url = Tree.class.getResource("images/boom.png");
        URL url2 = Tree.class.getResource("spriteDefault.png");
        try
        {
            // Load the image at location url
            img = ImageIO.read(url);
            img2 = ImageIO.read(url2);
        }
        catch (Exception e)
        {}

        FCLabel p = new FCLabel(img);
        p.setLocation(20, 20);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        add(p);

        p = new FCLabel(img2);
        p.setLocation(20, 85);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        add(p);

        p = new FCLabel(img2);
        p.setLocation(85, 20);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        add(p);

        p = new FCLabel(img);
        p.setLocation(85, 85);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        add(p);
    }

    class FCLabel extends JLabel
    {
        private BufferedImage bf = null;

        public FCLabel(BufferedImage bfi)
        {
            bf = bfi;
        }

        public void paint(Graphics g)
        {
            g.setColor(Color.red);
            g.fillRect(0, 0, 60, 60);
            if (bf != null)
            {
                g.drawImage(bf, 0, 0, 60, 60, 0, 0, bf.getWidth(null), bf
                        .getHeight(null), null);
            }
        }
    }

    class FCMouseAdapter implements MouseListener
    {

        public void mouseClicked(MouseEvent me)
        {
            // Do nothing
        }

        public void mouseEntered(MouseEvent me)
        {
            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    alpha));
            g.setColor(Color.white);
            g.fillRect(0, 0, 60, 60);
        }

        public void mouseExited(MouseEvent me)
        {
            paint(getGraphics());
        }

        public void mousePressed(MouseEvent me)
        {
            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    alpha));
            g.setColor(Color.blue);
            g.fillRect(0, 0, 60, 60);
        }

        public void mouseReleased(MouseEvent me)
        {
            // Do nothing
        }
    }
}
