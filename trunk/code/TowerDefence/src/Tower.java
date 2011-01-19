import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tower extends Field
{
	private TowerData td;
	private int counter;
	private int caseNumber;
	private int health;
	private int maxHP;
	private boolean showHealth = false;

	public int getHealth()
	{
		return health;
	}

	public void setMaxHP(int health)
	{
		this.maxHP = health;
	}

	public int getMaxHP()
	{
		return maxHP;
	}

	public void setHealth(int health)
	{
		if (health <= this.maxHP)
		{
			this.health = health;
		}
		else
		{
			this.health = maxHP;
		}
	}

	public Tower(BufferedImage bg)
	{
		// Just to set the size of the object
		super(bg);
		this.setCaseNumber(0);
		this.setCounter(0);
	}

	public Tower(BufferedImage bg, int tower, int health, TowerData tdIn)
	{
		super(bg);
		this.setCaseNumber(tower);
		this.setCounter(0);
		setMaxHP(health);
		setHealth(health);
		td = tdIn;
	}

	/**
	 * @return
	 */
	public int getCaseNumber()
	{
		return caseNumber;
	}

	/**
	 * @param caseNumber
	 */
	public void setCaseNumber(int caseNumber)
	{
		this.caseNumber = caseNumber;
	}

	public void setShow(boolean show)
	{
		showHealth = show;
	}

	public boolean getShow()
	{
		return showHealth;
	}

	/**
	 * @param g
	 */
	public void paint(Graphics g)
	{
		// Execute the paint function as if it was a Field object
		super.paint(g);

		int number = getCaseNumber();
		BufferedImage img = null;

		// If a CaseNumber is selected
		img = td.getTowerImage(number);

		g.drawImage(img, 1, 1, 40, 40, 0, 0, img.getWidth(null), img.getHeight(null), null);

		if (showHealth)
		{
			double percentage = ((double) getHealth()) / ((double) maxHP);
			double widthGreen = (getWidth() - 2) * percentage;

			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), 7);
			g.setColor(Color.red);
			g.fillRect(1, 1, getWidth() - 2, 5);
			g.setColor(Color.green);
			g.fillRect(1, 1, (int) widthGreen, 5);
		}
	}

	public int getCounter()
	{
		return counter;
	}

	public void count()
	{
		this.counter++;
	}

	public void setCounter(int counter)
	{
		this.counter = counter;
	}

	public String toString()
	{
		String result = "<Tower:\t" + getLocation() + ">";

		return result;
	}
}
