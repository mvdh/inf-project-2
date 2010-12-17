
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Maarten van den Hoek
 */
public class TowerController extends Controller {

    private int caseNumber;
    private TowerData towerData;

    public TowerController(TowerData td, Tower t) {
        init();
        setCaseNumber(t.getCaseNumber());
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
        URL url = Tree.class.getResource("images/boom.png");

        try {
            img = ImageIO.read(url);
        } catch (Exception e) {
        }

        CLabel label = new CLabel(null);
        label.setSize(60, 60);
        label.addMouseListener(new CMouseAdapter());
        label.setLocation(30, 30);
        add(label);
    }
}
