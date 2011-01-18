import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * @author Slayer
 */
public class Unit extends Sprite
{
	private int maxHP;
	private int hitPoints;
	private boolean aviation;
	private Vector path;
	private int pathCounter;
	private double speed;
	private int caseNumber;
        private int reward;

	private BufferedImage[] bf = null;
	private int animationSpeed = 10;
	private int number = 0;

	public Unit(double speed, int hp, String img, int reward)
	{
		super(speed);
		setHitPoints(hp);
		maxHP = hp;
		aviation = false;
		pathCounter = 0;
		caseNumber = 0;
                this.reward = reward;

		/**
         *
         */

		bf = new BufferedImage[3];

		URL url = getClass().getResource("images/" + img + "1.png");
		try
		{
			bf[0] = ImageIO.read(url);
		}
		catch (Exception e)
		{}

		url = getClass().getResource("images/" + img + "2.png");
		try
		{
			bf[1] = ImageIO.read(url);
		}
		catch (Exception e)
		{}

		url = getClass().getResource("images/" + img + "3.png");
		try
		{
			bf[2] = ImageIO.read(url);
		}
		catch (Exception e)
		{}

		int size = Math.max(bf[0].getWidth(), bf[0].getHeight());

		setSize(size, size + 5);
		setLocation(-(int) getWidth(), 200 - getHeight() / 2);
	}

	public Unit(double speed, int hp)
	{
		super(speed);
		setHitPoints(hp);
		maxHP = hp;
		aviation = false;
		pathCounter = 0;
		caseNumber = 0;

		/**
		 * 
		 bf = new BufferedImage[3];
		 * URL url = getClass().getResource("images/unit-mario3-f1.png");
		 * try
		 * {
		 * bf[0] = ImageIO.read(url);
		 * }
		 * catch (Exception e)
		 * {}
		 * url = getClass().getResource("images/unit-mario3-f2.png");
		 * try
		 * {
		 * bf[1] = ImageIO.read(url);
		 * }
		 * catch (Exception e)
		 * {}
		 * url = getClass().getResource("images/unit-mario3-f3.png");
		 * try
		 * {
		 * bf[2] = ImageIO.read(url);
		 * }
		 * catch (Exception e)
		 * {}
		 * int size = Math.max(bf[0].getWidth(), bf[0].getHeight());
		 * setSize(size, size + 5);
		 * setLocation(-(int) getWidth(), 200 - getHeight() / 2);
		 */
	}

	public int getCaseNumber()
	{
		return caseNumber;
	}


	public void setCaseNumber(int caseNumber)
	{
		this.caseNumber = caseNumber;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public void setAnimationSpeed(int speed)
	{
		animationSpeed = speed;
	}

	public int getAnimationSpeed()
	{
		return animationSpeed;
	}

	public Vector getPath()
	{
		return path;
	}

	public void setPath(Vector path)
	{
		if (path != null)
		{
			this.path = path;
		}
	}

	public void endMove()
	{
		if (hasNextPath())
		{
			Point newEnd = getNextPath().getLocation();
			// newEnd.x += 20;
			newEnd.x += 20 - (this.getWidth() / 2);
			newEnd.y += 40 - (this.getHeight() / 2);
			// System.out.println(newEnd);
			setNewDestination(newEnd);
		}
	}

	public void step()
	{
		super.step();

		number = (number + 1) % animationSpeed;
	}

	public boolean pathContains(Field field)
	{
		boolean res = false;
		for (int i = this.getPathCounter(); i < path.size(); i++)
		{
			if (path.get(i).equals(field))
			{
				res = true;
				break;
			}
		}
		return res;
	}

	public Field getNextPath()
	{
		return path.get(pathCounter++);
	}

	public boolean hasNextPath()
	{
		return (pathCounter < path.size());
	}

	public int getPathCounter()
	{
		return pathCounter;
	}

	public int findIndexOfNearestNextPath()
	{
		int res = -1;
		double distance = 10000;
		for (int i = pathCounter; i < path.size(); i++)
		{
			if (distance(this.getLocation(), path.get(i).getLocation()) < distance)
			{
				distance = distance(this.getLocation(), path.get(i).getLocation());
				res = i - 1;
			}
		}
		return res;
	}

	public void setPathCounter(int pathCounter)
	{
		this.pathCounter = pathCounter;
	}

	public boolean getAviation()
	{
		return aviation;
	}

	public void setAviation(boolean aviation)
	{
		this.aviation = aviation;
	}

	public int getHitPoints()
	{
		return hitPoints;
	}

	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}

	public void paint(Graphics g)
	{
		for (int i = 0; i < bf.length; i++)
		{
			if (number < animationSpeed * (i + 1) / bf.length && number >= animationSpeed * i / bf.length)
			{
				setImage(bf[i]);
			}
		}

		g.translate(getWidth() / 2 - bf[0].getWidth() / 2, 5);
		super.paint(g);
		g.translate(-(getWidth() / 2 - bf[0].getWidth() / 2), -5);
		double percentage = ((double) getHitPoints()) / ((double) maxHP);
		double widthGreen = (getWidth() - 2) * percentage;

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), 7);
		g.setColor(Color.red);
		g.fillRect(1, 1, getWidth() - 2, 5);
		g.setColor(Color.green);
		g.fillRect(1, 1, (int) widthGreen, 5);
	}

    /**
     * @return the reward
     */
    public int getReward() {
        return reward;
    }

    /**
     * @param reward the reward to set
     */
    public void setReward(int reward) {
        this.reward = reward;
    }
}
