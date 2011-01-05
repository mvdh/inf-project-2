import java.awt.Point;
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
        setTowerData(towerDataIn);
        init();
    }

    public void init()
    {
        super.init();

        Point[] locations =
        { new Point(20, 20), new Point(85, 20), new Point(20, 85), new Point(85, 85) };
        int[] possibilities = new int[4];
        int amountFound = 0;

        TowerData data = getTowerData();

        for (int i = 0; i < data.getTowerData().length; i++)
        {
            if (data.getPreviousCaseNumber(i) == -1)
            {
                possibilities[amountFound] = i;
                amountFound++;
            }
        }

        BufferedImage bf = null;
        URL url = null;
        CLabel lbl = null;

        for (int i = 0; i < 4; i++)
        {
            url = getClass().getResource("images/tower" + data.getTowerImageName(possibilities[i]) + ".png");
            try
            {
                bf = ImageIO.read(url);
            }
            catch (Exception e)
            {}
            
            lbl = new CLabel(bf, data.getCaseNumber(possibilities[i]));
            lbl.setLocation(locations[i]);
            lbl.addMouseListener(new CMouseAdapter());
            add(lbl);
        }
    }
}
