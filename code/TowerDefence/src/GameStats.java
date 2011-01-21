import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Maarten van den Hoek
 */
public class GameStats
{

	protected Game game;
	private boolean started;
	private TowerData towerData;
	private int points;
	private int gold;
	private int waveCounter;
	private int waveUnits;
	private int wave;
	private int castleHealth;
	private UnitData unitData;
	protected BufferedImage[] towerImages;
	protected BufferedImage[] unitImages;
	protected BufferedImage[] projectileImages;
	protected BufferedImage[] coinsImages;

	public UnitData getUnitData()
	{
		return unitData;
	}

	public void setUnitData(UnitData unitData)
	{
		this.unitData = unitData;
	}

	public int getWaveUnits()
	{
		return waveUnits;
	}

	public void setWaveUnits(int waveUnits)
	{
		this.waveUnits = waveUnits;
	}

	public int getWave()
	{
		return wave;
	}

	public void setWave(int wave)
	{
		this.wave = wave;
	}

	public int getWaveCounter()
	{
		return waveCounter;
	}

	public void setWaveCounter(int waveCounter)
	{
		this.waveCounter = waveCounter;
	}

	public void raiseWaveCounter()
	{
		waveCounter++;
	}

	public void raiseWaveUnits()
	{
		waveUnits++;
	}

	public void raiseWave()
	{
		wave++;
	}

	public GameStats(Game gameIn)
	{
		game = gameIn;
		started = false;
		points = 0;
		gold = 20;
		waveCounter = -100;
		waveUnits = 0;
		wave = 0;
		castleHealth = 750;
		setTowerData(new TowerData(this));
		unitData = new UnitData(this);

		MediaTracker tracker = new MediaTracker(game);

		URL img1 = getClass().getResource("images/tower1-level1.png");
		URL img2 = getClass().getResource("images/tower2-level1-f1.png");
		URL img3 = getClass().getResource("images/tower3-level1-f1.png");
		URL img4 = getClass().getResource("images/tower4-level1-f1.png");
		URL img5 = getClass().getResource("images/tower1-level2.png");
		URL img6 = getClass().getResource("images/tower2-level2-f1.png");
		URL img7 = getClass().getResource("images/tower3-level2-f1.png");
		URL img8 = getClass().getResource("images/tower4-level2-f1.png");
		URL img9 = getClass().getResource("images/tower1-level3.png");
		URL img10 = getClass().getResource("images/tower2-level3-f1.png");
		URL img11 = getClass().getResource("images/tower3-level3-f1.png");
		URL img12 = getClass().getResource("images/tower4-level3-f1.png");

		URL img13 = getClass().getResource("images/unit-mario1-f1.png");
		URL img14 = getClass().getResource("images/unit-mario1-f2.png");
		URL img15 = getClass().getResource("images/unit-mario1-f3.png");
		URL img16 = getClass().getResource("images/unit-mario2-f1.png");
		URL img17 = getClass().getResource("images/unit-mario2-f2.png");
		URL img18 = getClass().getResource("images/unit-mario2-f3.png");
		URL img19 = getClass().getResource("images/unit-mario3-f1.png");
		URL img20 = getClass().getResource("images/unit-mario3-f2.png");
		URL img21 = getClass().getResource("images/unit-mario3-f3.png");

		URL img22 = getClass().getResource("images/kannonskogel.png");
		URL img23 = getClass().getResource("images/projectile5-f2.png");
		URL img24 = getClass().getResource("images/kogel.png");
		URL img25 = getClass().getResource("spriteDefault.png");

		URL img26 = getClass().getResource("images/coin-f1.png");
		URL img27 = getClass().getResource("images/coin-f2.png");
		URL img28 = getClass().getResource("images/coin-f2.png");
		URL img29 = getClass().getResource("images/coin-f2.png");

		towerImages = new BufferedImage[12];
		towerImages[0] = game.loadImage(img1, tracker, 0);
		towerImages[1] = game.loadImage(img2, tracker, 1);
		towerImages[2] = game.loadImage(img3, tracker, 2);
		towerImages[3] = game.loadImage(img4, tracker, 3);
		towerImages[4] = game.loadImage(img5, tracker, 4);
		towerImages[5] = game.loadImage(img6, tracker, 5);
		towerImages[6] = game.loadImage(img7, tracker, 6);
		towerImages[7] = game.loadImage(img8, tracker, 7);
		towerImages[8] = game.loadImage(img9, tracker, 8);
		towerImages[9] = game.loadImage(img10, tracker, 9);
		towerImages[10] = game.loadImage(img11, tracker, 10);
		towerImages[11] = game.loadImage(img12, tracker, 11);

		unitImages = new BufferedImage[9];
		unitImages[0] = game.loadImage(img13, tracker, 12);
		unitImages[1] = game.loadImage(img14, tracker, 13);
		unitImages[2] = game.loadImage(img15, tracker, 14);
		unitImages[3] = game.loadImage(img16, tracker, 15);
		unitImages[4] = game.loadImage(img17, tracker, 16);
		unitImages[5] = game.loadImage(img18, tracker, 17);
		unitImages[6] = game.loadImage(img19, tracker, 18);
		unitImages[7] = game.loadImage(img20, tracker, 19);
		unitImages[8] = game.loadImage(img21, tracker, 20);
		
		projectileImages = new BufferedImage[4];
		projectileImages[0] = game.loadImage(img22, tracker, 21);
		projectileImages[1] = game.loadImage(img23, tracker, 22);
		projectileImages[2] = game.loadImage(img24, tracker, 23);
		projectileImages[3] = game.loadImage(img25, tracker, 24);
		
		coinsImages = new BufferedImage[4];
		coinsImages[0] = game.loadImage(img26, tracker, 25);
		coinsImages[1] = game.loadImage(img27, tracker, 26);
		coinsImages[2] = game.loadImage(img28, tracker, 27);
		coinsImages[3] = game.loadImage(img29, tracker, 28);
		
//		try
//		{
//			towerImages[0] = ImageIO.read(img1);
//			tracker.addImage(towerImages[0], 0);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[1] = ImageIO.read(img2);
//			tracker.addImage(towerImages[1], 1);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[2] = ImageIO.read(img3);
//			tracker.addImage(towerImages[2], 2);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[3] = ImageIO.read(img4);
//			tracker.addImage(towerImages[3], 3);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[4] = ImageIO.read(img5);
//			tracker.addImage(towerImages[4], 4);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[5] = ImageIO.read(img6);
//			tracker.addImage(towerImages[5], 5);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[6] = ImageIO.read(img7);
//			tracker.addImage(towerImages[6], 6);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[7] = ImageIO.read(img8);
//			tracker.addImage(towerImages[7], 7);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[8] = ImageIO.read(img9);
//			tracker.addImage(towerImages[8], 8);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[9] = ImageIO.read(img10);
//			tracker.addImage(towerImages[9], 9);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[10] = ImageIO.read(img11);
//			tracker.addImage(towerImages[10], 10);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			towerImages[11] = ImageIO.read(img12);
//			tracker.addImage(towerImages[11], 11);
//		}
//		catch (Exception e)
//		{}
//
//		/**
//         *
//         */
//		unitImages = new BufferedImage[9];
//		try
//		{
//			unitImages[0] = ImageIO.read(img13);
//			tracker.addImage(unitImages[0], 12);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[1] = ImageIO.read(img14);
//			tracker.addImage(unitImages[1], 13);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[2] = ImageIO.read(img15);
//			tracker.addImage(unitImages[2], 14);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[3] = ImageIO.read(img16);
//			tracker.addImage(unitImages[3], 15);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[4] = ImageIO.read(img17);
//			tracker.addImage(unitImages[4], 16);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[5] = ImageIO.read(img18);
//			tracker.addImage(unitImages[5], 17);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[6] = ImageIO.read(img19);
//			tracker.addImage(unitImages[6], 18);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[7] = ImageIO.read(img20);
//			tracker.addImage(unitImages[7], 19);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			unitImages[8] = ImageIO.read(img21);
//			tracker.addImage(unitImages[8], 20);
//		}
//		catch (Exception e)
//		{}
//
//		/**
//         *
//         */
//		projectileImages = new BufferedImage[4];
//		try
//		{
//			projectileImages[0] = ImageIO.read(img22);
//			tracker.addImage(projectileImages[0], 21);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			projectileImages[1] = ImageIO.read(img23);
//			tracker.addImage(projectileImages[1], 22);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			projectileImages[2] = ImageIO.read(img24);
//			tracker.addImage(projectileImages[2], 23);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			projectileImages[3] = ImageIO.read(img25);
//			tracker.addImage(projectileImages[3], 24);
//		}
//		catch (Exception e)
//		{}
//
//		/**
//         *
//         */
//		coinsImages = new BufferedImage[4];
//		try
//		{
//			coinsImages[0] = ImageIO.read(img26);
//			tracker.addImage(coinsImages[0], 25);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			coinsImages[1] = ImageIO.read(img27);
//			tracker.addImage(coinsImages[1], 26);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			coinsImages[2] = ImageIO.read(img28);
//			tracker.addImage(coinsImages[2], 27);
//		}
//		catch (Exception e)
//		{}
//		try
//		{
//			coinsImages[3] = ImageIO.read(img29);
//			tracker.addImage(coinsImages[3], 28);
//		}
//		catch (Exception e)
//		{}

		// Wait untill all images are loaded
		try
		{
			tracker.waitForAll();
		}
		catch (InterruptedException ie)
		{}
	}

	public int getGold()
	{
		return gold;
	}

	public void setGold(int gold)
	{
		this.gold = gold;
	}

	public void setTowerData(TowerData td)
	{
		this.towerData = td;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public void updateGold(int dir, int gold)
	{
		if (dir == -1)
		{
			this.gold -= gold;
		}
		else
		{
			this.gold += gold;
		}
	}

	public void updatePoints(int dir, int points)
	{
		if (dir == -1)
		{
			this.points -= points;
		}
		else
		{
			this.points += points;
		}
	}

	public TowerData getTowerData()
	{
		return this.towerData;
	}

	public int getPoints()
	{
		return this.points;
	}

	public boolean isStarted()
	{
		return started;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	public void reset()
	{
		points = 0;
		gold = 20;
		waveCounter = -100;
		waveUnits = 0;
		wave = 0;
		castleHealth = 750;
	}

	public int getCastleHealth()
	{
		return castleHealth;
	}

	public void updateCastleHealth(int x, int amount)
	{
		if (x == -1)
		{
			castleHealth -= amount;
		}
		else
		{
			castleHealth += amount;
		}
	}
}