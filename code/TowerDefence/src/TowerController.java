import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * aertyuj
 * 
 * @author Maarten van den Hoek
 */
public class TowerController extends Controller
{

	private int caseNumber;

	public TowerController(GameStats gameStats, Tower t)
	{
		setGameStats(gameStats);
		setCaseNumber(t.getCaseNumber());
		init();
	}

	/**
	 * Gets the caseNumber of the tower
	 * 
	 * @return the caseNumber
	 */
	public int getCaseNumber()
	{
		return caseNumber;
	}

	/**
	 * Sets the caseNumber of the tower
	 * 
	 * @param caseNumber
	 *            the caseNumber to set
	 */
	public void setCaseNumber(int caseNumber)
	{
		this.caseNumber = caseNumber;
	}

	@Override
	public void init()
	{
		super.init();
		BufferedImage img = null;
		Point[] locations =
		{
				new Point(50, 25), new Point(110, 25), new Point(50, 85), new Point(110, 85)
		};

		CLabel label = new CLabel(null, -1);
		label.addMouseListener(new CMouseAdapter());
		label.setLocation(locations[0]);
		add(label);

		// check welke upgradables er zijn, en aan de hand daarvan moeten
		// plaatjes worden geladen
		ArrayList<Integer> upgradables = this.getGameStats().getTowerData().getUpgradables(this.getCaseNumber());
		if (upgradables.get(0) != -1)
		{
			for (int i = 1; i <= upgradables.size(); i++)
			{
				img = getGameStats().getTowerData().getTowerImage(upgradables.get(i - 1));
				
				label = new CLabel(img, upgradables.get(i - 1));
				label.addMouseListener(new CMouseAdapter());
				label.setLocation(locations[i]);
				add(label);
			}
		}
	}
}
