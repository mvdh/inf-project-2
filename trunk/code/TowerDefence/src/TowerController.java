import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * aertyuj
 * @author Maarten van den Hoek
 */
public class TowerController extends Controller {

    private int caseNumber;
    private TowerData towerData;

    public TowerController(TowerData td, Tower t) {
        setTowerData(td);
        //setCaseNumber(t.getCaseNumber());
        setCaseNumber(1);
        init();
    }

    /**
     * Gets the caseNumber of the tower
     * @return the caseNumber
     */
    public int getCaseNumber() {
        return caseNumber;
    }

    /**
     * 
     * @return
     */
    public TowerData getTowerData() {
        return towerData;
    }

    /**
     * Sets the caseNumber of the tower
     * @param caseNumber the caseNumber to set
     */
    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    /**
     * 
     * @param towerData
     */
    public void setTowerData(TowerData towerData) {
        this.towerData = towerData;
    }

    /**
     * Checks which towers the current tower can upgrade to. The towers can not
     * be downgraded, so the previous cases are excluded
     * @return The possible upgradables
     */
    public ArrayList<Integer> findUpgradables() {
        int currentCase = getCaseNumber();
        int currency = 100;

        //The upgradables based on case number en currency
        ArrayList<Integer> upgradables = this.getTowerData().getUpgradables(caseNumber, currency);

        return upgradables;
    }

    @Override
    public void init() {
        super.init();
        BufferedImage img = null;
        Point[] locations =
        { new Point(20, 20), new Point(85, 20), new Point(20, 85), new Point(85, 85) };


        //check welke upgradables er zijn, en aan de hand daarvan moeten plaatjes worden geladen
        ArrayList<Integer> upgradables = this.getTowerData().getUpgradables(this.getCaseNumber(), 0);
        if (upgradables.get(0) != -1) {
            System.out.println("Some upgradables");

            URL url = getClass().getResource("images/boom.png");
            try {
                img = ImageIO.read(url);
            } catch (Exception e) {
            }

            CLabel label = new CLabel(img);
            label.addMouseListener(new CMouseAdapter());
            label.setLocation(20, 20);
            add(label);

            for (int i = 1; i <= upgradables.size(); i++) {
                url = getClass().getResource("images/tower" + this.getTowerData().getTowerImageName(upgradables.get(i-1)) + ".png");
                try {
                    img = ImageIO.read(url);
                } catch (Exception e) {
                }

                label = new CLabel(img);
                label.addMouseListener(new CMouseAdapter());
                label.setLocation(locations[i]);
                add(label);
            }
        }
    }
}
