import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Maarten van den Hoek
 */
public class FieldController extends Component 
{
    public FieldController(Point p) 
    {
        setSize(60, 60);
        setLocation(p);
        addMouseListener(new FCMouseAdapter());
    }    

    /**
     * 
     * @param g Graphics
     */
    public void paint(Graphics g) 
    {
        g.setColor(Color.red);
        g.fillRect(0, 0, 60, 60);
    }
    
    class FCMouseAdapter implements MouseListener
    {

        public void mouseClicked(MouseEvent me)
        {
            // Do nothing
        }

        public void mouseEntered(MouseEvent me)
        {
            Graphics2D g = (Graphics2D) getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(Color.white);
            g.fillRect(0, 0, 60, 60);
        }

        public void mouseExited(MouseEvent me)
        {
            paint(getGraphics());
        }

        public void mousePressed(MouseEvent me)
        {
            Graphics2D g = (Graphics2D) getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(Color.blue);
            g.fillRect(0, 0, 60, 60);
        }

        public void mouseReleased(MouseEvent me)
        {
            // Do nothing
        }        
    }
}
