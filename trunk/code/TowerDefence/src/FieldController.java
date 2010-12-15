import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
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
     * @param g
     */
    @Override
    public void paint(Graphics g) 
    {
        g.setColor(Color.red);
        g.fillRect(0, 0, 60, 60);
    }
    
    class FCMouseAdapter implements MouseListener
    {

        public void mouseClicked(MouseEvent arg0)
        {
            // Do nothing
        }

        public void mouseEntered(MouseEvent arg0)
        {
            // Do nothing
        }

        public void mouseExited(MouseEvent arg0)
        {
            // Do nothing
        }

        public void mousePressed(MouseEvent arg0)
        {
            // Do nothing
        }

        public void mouseReleased(MouseEvent arg0)
        {
            // Do nothing
        }        
    }
}
