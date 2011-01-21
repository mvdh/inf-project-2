import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Sprite extends Component
{

	public Point c;// = new Point(); // Start Positie
	public Point d;// = new Point(300, 200); // Bestemming
	private double speed;// = 2.1;
	private int stepCounter;// = 0;
	private double lastDis = Math.pow(2, 32) - 1; // Maximum value of an int

	private BufferedImage bf;
	public double angle;
	public double standardDis;

	public Sprite()
	{
	// ImageIcon img = new
	// ImageIcon(getClass().getResource("spriteDefault.png"));
	// setIcon(img);
	// setSize(img.getIconWidth(), img.getIconHeight());
	// int x = (int) (Math.random() * 480);
	// int y = (int) (Math.random() * 280);
	// setLocation(x, y);
	// c = getLocation();
	}

	public Sprite(double s)
	{
		// this();
		speed = s;
		stepCounter = 0;
	}

	public void step()
	{
		if (!(this instanceof Projectile))
		{
			angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX()); // Berekent de hoek waaronder gereisd wordt in radialen
		}

		Point loc;

		int posX = (getLocation().x - 14) % 40;
		int posY = (getLocation().y - 12) % 40;
		Point pos = new Point(getLocation());
		if (this instanceof Unit)
		{
			if (posX != 0)
			{
				pos.setLocation(new Point(Math.round(getLocation().x / 40) * 40 + 14, pos.getLocation().y));
			}

			if (posY != 0)
			{
				pos.setLocation(new Point(pos.getLocation().x, Math.round(getLocation().y / 40) * 40 + 12));
			}
		}

		if ((angle != Math.PI && Math.abs(angle) != Math.PI / 2 && angle != 0) && !pos.equals(getLocation()))
		{
			setLocation(pos);
		}

		if (this instanceof Unit && (angle != Math.PI && Math.abs(angle) != Math.PI / 2 && angle != 0))
		{

			loc = ((Unit) this).getPath().get(0).getLocation();
			((Unit) this).setPathCounter(0);
			loc.x += 70 - (this.getWidth() / 2);
			loc.y += 150 - (this.getHeight() / 2);
			d = loc;
			angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX());

			if (c.equals(d))
			{
				loc = ((Unit) this).getPath().get(1).getLocation();
				((Unit) this).setPathCounter(1);
				loc.x += 70 - (this.getWidth() / 2);
				loc.y += 150 - (this.getHeight() / 2);
				d = loc;
				angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX());
			}
		}

		// Hou hier rekening met een modifier voor vertragende torens.
		Double dis = distance(this.getLocation(), d); // Wordt gebruikt om de te reizen afstand voor deze stap door te geven
		if (dis > speed)
		{ // Op deze manier wordt 'overshoot' beperkt
			dis = speed; // en kan de marge hopelijk kleiner
		}

		int newX = (int) Math.floor(c.getX() + (Math.cos(angle) * dis * stepCounter));
		int newY = (int) Math.floor(c.getY() + (Math.sin(angle) * dis * stepCounter));

		// Om iets zekerder te zijn dat het object niet over het doel heen
		// schiet is er een marge ingebouwd waarop ie 'snapped'
		if (dis < standardDis && this instanceof Projectile || lastDis < dis && this instanceof Unit)
		{
			newX = d.x;
			newY = d.y;
			lastDis = Math.pow(2, 32) - 1;
		}

		this.setLocation(newX, newY);

		// this.repaint();

		if ((newX == d.x) && (newY == d.y) && !(this instanceof Projectile))
		{
			// d.end(); // Wat gebeurt er wanneer een beweging voltooid is
			// this.finalize(); // Is opruimen na de beweging nodig?
			// System.out.println("Beweging voltooid");
			endMove();
		}

		stepCounter++;
		lastDis = dis;
	}

	public void endMove()
	{
	// setNewDestination();
	}

	public void setNewDestination(Point p)
	{
		c = this.getLocation();
		stepCounter = 0;
		d = p;
	}

	public double distance(Point p, Point q)
	{ // Calculates distance between two points
		double dx = Math.abs(p.x - q.x); // horizontal difference
		double dy = Math.abs(p.y - q.y); // vertical difference
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // distance using Pythagoras
		// theorem
		return dist;
	}

	public Point getEnd()
	{
		return d;
	}

	public void paint(Graphics g)
	{
		AffineTransform trans = new AffineTransform();
		if (this instanceof Projectile)
		{
			if (Math.abs(angle) == Math.PI && getWidth() == 24)
			{
				trans.translate(0, -15);
			}
		}
		else if (-angle == Math.PI / 2)
		{
			if (getWidth() == 16)
			{
				trans.translate(2, 0);
			}
			else
			{
				trans.translate(-5, 0);
			}
		}
		else if (angle == Math.PI / 2)
		{
			if (getWidth() == 16)
			{
				trans.translate(-2, 0);
			}
			else
			{
				trans.translate(-10, 0);
			}
		}
		else if (angle == Math.PI)
		{
			if (getWidth() > 16)
			{
				trans.translate(-15, -7);
			}
			else
			{
				trans.translate(0, -7);
			}

			((Graphics2D) g).scale(1, -1);
			((Graphics2D) g).translate(0, -(getHeight() - 8));
		}

		trans.rotate(angle, getWidth() / 2, getHeight() / 2);
		((Graphics2D) g).drawImage(bf, trans, null);

		if (angle == Math.PI)
		{
			((Graphics2D) g).scale(1, -1);
			((Graphics2D) g).translate(0, -(getHeight() - 8));
		}
	}

	public void setImage(BufferedImage bfIn)
	{
		bf = bfIn;
	}
}
