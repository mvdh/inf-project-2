import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Tom Nieuwenhuijs
 */
public class FieldController extends Controller
{
    public FieldController(TowerData towerDataIn)
    {
        init();
        setTowerData(towerDataIn);
    }

    public void init()
    {
        super.init();

        BufferedImage A1 = null;
        BufferedImage B1 = null;
        BufferedImage C1 = null;
        BufferedImage D1 = null;
        URL urlA1 = getClass().getResource("images/towerA1.png");
        URL urlA2 = getClass().getResource("images/towerB1.png");
        URL urlA3 = getClass().getResource("images/towerC1.png");
        URL urlA4 = getClass().getResource("images/towerD1.png");
        try
        {
            A1 = ImageIO.read(urlA1);
        }
        catch (Exception e)
        {}
        try
        {
            B1 = ImageIO.read(urlA2);
        }
        catch (Exception e)
        {}
        try
        {
            C1 = ImageIO.read(urlA3);
        }
        catch (Exception e)
        {}
        try
        {
            D1 = ImageIO.read(urlA4);
        }
        catch (Exception e)
        {}

        CLabel lbl = new CLabel(A1, 1);
        lbl.setLocation(20, 20);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(B1, 2);
        lbl.setLocation(85, 20);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(C1, 3);
        lbl.setLocation(20, 85);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);

        lbl = new CLabel(D1, 4);
        lbl.setLocation(85, 85);
        lbl.addMouseListener(new CMouseAdapter());
        add(lbl);
    }
}
