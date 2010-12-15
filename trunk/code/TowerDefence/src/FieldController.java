import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Maarten van den Hoek
 */
public class FieldController extends JPanel 
{
    public FieldController() 
    {
        init();
    }    

    public void init()
    {
        setLayout(null);
        setSize(680, 180);
        
        FCPanel p = new FCPanel();
        p.setLocation(20, 20);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        
        add(p);
        
        p = new FCPanel();
        p.setLocation(20, 85);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        
        add(p);
        
        p = new FCPanel();
        p.setLocation(85, 20);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        
        add(p);
        
        p = new FCPanel();
        p.setLocation(85, 85);
        p.setSize(60, 60);
        p.addMouseListener(new FCMouseAdapter());
        
        add(p);
    }
    
    class FCPanel extends JPanel
    {
        public void paint(Graphics g) 
        {        
            g.setColor(Color.red);
            g.fillRect(0, 0, 60, 60);
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
            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
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
