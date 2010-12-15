import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 *
 * @author Maarten van den Hoek
 */
public class TowerController extends Component {

    private int caseNumber;
    private boolean[] upgradables;

    public TowerController(Tower t) {
        setLocation(0, 0);
        setSize(680, 180);
        setVisible(true);
        //System.out.println("test construct");

        setCaseNumber(t.getCaseNumber());
        setUpgradables(findUpgradables());
        System.out.println(getCaseNumber());
    }

    /**
     * Gets the caseNumber of the tower
     * @return the caseNumber
     */
    public int getCaseNumber() {
        return caseNumber;
    }

    /**
     * @return the upGradables
     */
    public boolean[] isUpgradables() {
        return upgradables;
    }

    /**
     * Sets the caseNumber of the tower
     * @param caseNumber the caseNumber to set
     */
    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    /**
     * @param upGradables the upGradables to set
     */
    public void setUpgradables(boolean[] upgradables) {
        this.upgradables = upgradables;
    }

    /**
     * Checks which towers the current tower can upgrade to. The towers can not
     * be downgraded, so the previous cases are excluded
     * @return a list of upgradables in booleans.
     */
    public boolean[] findUpgradables() {
        boolean[] upgr = new boolean[20];
        int currency = 0;

        //Based on currency
        //Based on current caseNumber (caseNumbers begins with 1 and ends with 20)
        for (int i = getCaseNumber(); i < currency; i++) {
            upgr[i] = true;
        }

        return upgr;
    }

    /**
     * 
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        System.out.println("before");
        for (int i = 0; i < 20; i++) {
            if(isUpgradables()[i] == true){
                System.out.println("true");
                g.setColor(new Color(0, 255, 0));
            } else {
                System.out.println("false");
                g.setColor(Color.red);
            }
            g.fillRect(20, 20, 60, 60);
        }
        
        g.setColor(Color.red);
        System.out.println("how many?");
    }
}
