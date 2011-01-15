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
	private int marge;// = 2;

	private BufferedImage bf;
	private double angle;

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
		marge = (int) Math.ceil(s) + 1;
		stepCounter = 0;
	}

	public void step()
	{

		// Hou hier rekening met een modifier voor vertragende torens.
		Double dis = distance(this.getLocation(), d); // Wordt gebruikt om de te reizen afstand voor deze stap door te geven
		if (dis > speed)
		{ // Op deze manier wordt 'overshoot' beperkt
			dis = speed; // en kan de marge hopelijk kleiner
		}

		angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX()); // Berekent de hoek waaronder gereisd wordt in radialen

		int newX = (int) Math.floor(c.getX() + (Math.cos(angle) * (dis * stepCounter)));
		int newY = (int) Math.floor(c.getY() + (Math.sin(angle) * (dis * stepCounter)));

		// Om iets zekerder te zijn dat het object niet over het doel heen
		// schiet is er een marge ingebouwd waarop ie 'snapped'
		if ((newX > d.getX() - marge) && (newX < d.getX() + marge))
		{
			newX = (int) d.getX();
		}

		if ((newY > d.getY() - marge) && (newY < d.getY() + marge))
		{
			newY = (int) d.getY();
		}

		this.setLocation(newX, newY);
		// this.repaint();

		if ((newX == (int) d.getX()) && (newY == (int) d.getY()) && !(this instanceof Projectile))
		{
			// d.end(); // Wat gebeurt er wanneer een beweging voltooid is
			// this.finalize(); // Is opruimen na de beweging nodig?
			// System.out.println("Beweging voltooid");
			this.endMove();
		}
		stepCounter++;

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
		// System.out.println(c);
		// System.out.println(d);
	}

	public double distance(Point p, Point q)
	{ // Calculates distance between two points
		double dx = p.x - q.x; // horizontal difference
		double dy = p.y - q.y; // vertical difference
		double dist = Math.sqrt(dx * dx + dy * dy); // distance using Pythagoras
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
		if (-angle == Math.PI / 2)
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
		trans.rotate(angle, getWidth() / 2, getHeight() / 2);
		((Graphics2D) g).drawImage(bf, trans, null);
		setVisible(true);
	}

	public void setImage(BufferedImage bfIn)
	{
		bf = bfIn;
	}
}
