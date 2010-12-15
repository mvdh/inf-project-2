import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maarten van den Hoek
 */
public class Controller extends Container
{
    public void init()
    {
        setLayout(null);
        setSize(680, 180);
    }
    
    class CLabel extends JLabel
    {
        private BufferedImage bf = null;

        public CLabel(BufferedImage bfi)
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
    
    class CMouseAdapter implements MouseListener
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
            paint(getGraphics());
            
            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    alpha));
            g.setColor(Color.white);
            g.fillRect(0, 0, 60, 60);
        }
    }
}
