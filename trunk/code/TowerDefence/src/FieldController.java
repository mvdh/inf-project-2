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
        
        BufferedImage img = null;
        BufferedImage img2 = null;
        URL url = Tree.class.getResource("images/boom.png");
        URL url2 = Tree.class.getResource("spriteDefault.png");
        try
        {
            img = ImageIO.read(url);
            img2 = ImageIO.read(url2);
        }
        catch (Exception e)
        {}

        CLabel lbl = new CLabel(img, 1);
        lbl.setLocation(20, 20);
        lbl.addMouseListener(new FCMouseAdapter());
        add(lbl);

        lbl = new CLabel(img2, 2);
        lbl.setLocation(85, 20);
        lbl.addMouseListener(new FCMouseAdapter());
        add(lbl);

        lbl = new CLabel(img2, 3);
        lbl.setLocation(20, 85);
        lbl.addMouseListener(new FCMouseAdapter());
        add(lbl);

        lbl = new CLabel(img, 4);
        lbl.setLocation(85, 85);
        lbl.addMouseListener(new FCMouseAdapter());
        add(lbl);
    }
    
    class FCMouseAdapter extends CMouseAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            super.mousePressed(me);
            
            setTakeAction(true);
            setType(((CLabel) me.getComponent()).getType());
        }
    }
}
