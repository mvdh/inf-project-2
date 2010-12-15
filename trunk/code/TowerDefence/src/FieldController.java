import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Maarten van den Hoek
 */
public class FieldController extends Controller
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

        CLabel p = new CLabel(img);
        p.setLocation(20, 20);
        p.setSize(60, 60);
        p.addMouseListener(new CMouseAdapter());
        add(p);

        p = new CLabel(img2);
        p.setLocation(20, 85);
        p.setSize(60, 60);
        p.addMouseListener(new CMouseAdapter());
        add(p);

        p = new CLabel(img2);
        p.setLocation(85, 20);
        p.setSize(60, 60);
        p.addMouseListener(new CMouseAdapter());
        add(p);

        p = new CLabel(img);
        p.setLocation(85, 85);
        p.setSize(60, 60);
        p.addMouseListener(new CMouseAdapter());
        add(p);
    }
}
