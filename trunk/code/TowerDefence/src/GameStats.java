import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Maarten van den Hoek
 */
public class GameStats
{

    private boolean started;
    private TowerData towerData;
    private int points;
    private int gold;
    private int waveCounter;
    private int waveUnits;
    private int wave;
    private UnitData unitData;
	protected BufferedImage[] towerImages;
	protected BufferedImage[] unitImages;

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

    public GameStats(Game game)
    {
        started = true;
        points = 0;
        gold = 20;
        waveCounter = -75;
        waveUnits = 0;
        wave = 0;
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
		URL img16 = getClass().getResource("images/unit-mario1-f1.png");
		URL img17 = getClass().getResource("images/unit-mario1-f2.png");
		URL img18 = getClass().getResource("images/unit-mario1-f3.png");
		URL img19 = getClass().getResource("images/unit-mario3-f1.png");
		URL img20 = getClass().getResource("images/unit-mario3-f2.png");
		URL img21 = getClass().getResource("images/unit-mario3-f3.png");
		
		towerImages = new BufferedImage[12];
		try
		{
			towerImages[0] = ImageIO.read(img1);
			tracker.addImage(towerImages[0], 0);
			System.out.println("");
		}
		catch (Exception e) {}
		try
		{
			towerImages[1] = ImageIO.read(img2);
			tracker.addImage(towerImages[1], 1);
		}
		catch (Exception e) {}
		try
		{
			towerImages[2] = ImageIO.read(img3);
			tracker.addImage(towerImages[2], 2);
		}
		catch (Exception e) {}
		try
		{
			towerImages[3] = ImageIO.read(img4);
			tracker.addImage(towerImages[3], 3);
		}
		catch (Exception e) {}
		try
		{
			towerImages[4] = ImageIO.read(img5);
			tracker.addImage(towerImages[4], 4);
		}
		catch (Exception e) {}
		try
		{
			towerImages[5] = ImageIO.read(img6);
			tracker.addImage(towerImages[5], 5);
		}
		catch (Exception e) {}
		try
		{
			towerImages[6] = ImageIO.read(img7);
			tracker.addImage(towerImages[6], 6);
		}
		catch (Exception e) {}
		try
		{
			towerImages[7] = ImageIO.read(img8);
			tracker.addImage(towerImages[7], 7);
		}
		catch (Exception e) {}
		try
		{
			towerImages[8] = ImageIO.read(img9);
			tracker.addImage(towerImages[8], 8);
		}
		catch (Exception e) {}
		try
		{
			towerImages[9] = ImageIO.read(img10);
			tracker.addImage(towerImages[9], 9);
		}
		catch (Exception e) {}
		try
		{
			towerImages[10] = ImageIO.read(img11);
			tracker.addImage(towerImages[10], 10);
		}
		catch (Exception e) {}
		try
		{
			towerImages[11] = ImageIO.read(img12);
			tracker.addImage(towerImages[11], 11);
		}
		catch (Exception e) {}

		unitImages = new BufferedImage[9];
		try
		{
			unitImages[0] = ImageIO.read(img13);
			tracker.addImage(unitImages[0], 0);
		}
		catch (Exception e) {}
		try
		{
			unitImages[1] = ImageIO.read(img14);
			tracker.addImage(unitImages[1], 1);
		}
		catch (Exception e) {}
		try
		{
			unitImages[2] = ImageIO.read(img15);
			tracker.addImage(unitImages[2], 2);
		}
		catch (Exception e) {}
		try
		{
			unitImages[3] = ImageIO.read(img16);
			tracker.addImage(unitImages[3], 3);
		}
		catch (Exception e) {}
		try
		{
			unitImages[4] = ImageIO.read(img17);
			tracker.addImage(unitImages[4], 4);
		}
		catch (Exception e) {}
		try
		{
			unitImages[5] = ImageIO.read(img18);
			tracker.addImage(unitImages[5], 5);
		}
		catch (Exception e) {}
		try
		{
			unitImages[6] = ImageIO.read(img19);
			tracker.addImage(unitImages[6], 6);
		}
		catch (Exception e) {}
		try
		{
			unitImages[7] = ImageIO.read(img20);
			tracker.addImage(unitImages[7], 7);
		}
		catch (Exception e) {}
		try
		{
			unitImages[8] = ImageIO.read(img21);
			tracker.addImage(unitImages[8], 8);
		}
		catch (Exception e) {}
	
		try 
        {
            tracker.waitForAll();
            System.out.println("Gelukt:D");
        } 
        catch (InterruptedException ie) {
        	
        	System.out.println("SUKKEL, het is mislukt");
        }
		
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
        } else
        {
            this.gold += gold;
        }
    }

    public void updatePoints(int dir, int points)
    {
        if (dir == -1)
        {
            this.points -= points;
        } else
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
}
