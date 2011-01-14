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
				new Point(20, 20), new Point(85, 20), new Point(20, 85), new Point(85, 85)
		};

		URL url = getClass().getResource("images/boom.png");
		try
		{
			img = ImageIO.read(url);
		}
		catch (Exception e)
		{}

		CLabel label = new CLabel(img, -1);
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
				url = getClass().getResource("images/tower" + this.getGameStats().getTowerData().getTowerImageName(upgradables.get(i - 1)) + ".png");
				try
				{
					img = ImageIO.read(url);
				}
				catch (Exception e)
				{}

				label = new CLabel(img, upgradables.get(i - 1));
				label.addMouseListener(new CMouseAdapter());
				label.setLocation(locations[i]);
				add(label);
			}
		}
	}
}
