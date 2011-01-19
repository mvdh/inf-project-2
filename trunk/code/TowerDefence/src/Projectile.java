import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author averaart
 */
public class Projectile extends Sprite
{

	private int damage;
	private int range;

	private BufferedImage bf;

	public Projectile(int d, int img, double speed, int range, Point destination, Point start, GameStats data)
	{
		super(speed);
		damage = d;
		this.range = range;
		this.d = destination;
		this.c = start;
		angle = Math.atan2(destination.getY() - c.getY(), destination.getX() - c.getX());
		standardDis = Math.min(distance(start, destination), speed);

		bf = data.projectileImages[img];
		setImage(bf);

		int size = Math.max(bf.getHeight(), bf.getWidth());
		setSize(size, size);
		setLocation(start);
	}

	public void endMove()
	{
		// System.out.println("BOEM!!!\nIk doe nu " + damage +
		// " schade aan m'n doelwit");
		try
		{
			this.finalize();
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
	}

	public int getDamage()
	{
		return damage;
	}

	public int getRange()
	{
		return range;
	}
}
