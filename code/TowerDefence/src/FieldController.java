import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Tom Nieuwenhuijs
 */
public class FieldController extends Controller
{    
    public FieldController()
    {
        init();
    }

    public void init()
    {
        super.init();
        
        BufferedImage A1 = null;
        BufferedImage A2 = null;
        BufferedImage A3 = null;
        BufferedImage A4 = null;
        URL urlA1 = getClass().getResource("images/towerA1.png");
        URL urlA2 = getClass().getResource("images/towerA2.png");
        URL urlA3 = getClass().getResource("images/towerA3.png");
        URL urlA4 = getClass().getResource("images/towerA4.png");
        try
        {
            A1 = ImageIO.read(urlA1);
        }
        catch (Exception e)
        {}
        try
        {
            A2 = ImageIO.read(urlA2);
        }
        catch (Exception e)
        {}
        try
        {
            A3 = ImageIO.read(urlA3);
        }
        catch (Exception e)
        {}
        try
        {
            A4 = ImageIO.read(urlA4);
        }
        catch (Exception e)
        {}

        CLabel lbl = new CLabel(A1, 1);
        lbl.setLocation(20, 20);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(A2, 2);
        lbl.setLocation(85, 20);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(A3, 3);
        lbl.setLocation(20, 85);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(A4, 4);
        lbl.setLocation(85, 85);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);
    }
    
    class FCMouseAdapter extends CMouseAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            super.mousePressed(me);
            
        }
    }
}
