import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Field extends Component
{

	private boolean walkable;
	private boolean flyable;
	private boolean buildable;
	private BufferedImage bf = null;
	private double random;
	private double random2;

	// Voor de graphics
	private boolean selected = false;
	private boolean hovered = false;

	public Field(BufferedImage bg)
	{
		setSize(40, 40);
//		random = Math.random();
//		random2 = Math.random();

		bf = bg;

		walkable = true;
		flyable = true;
		buildable = true;
	}

	/**
	 * Is it possible to build at this Field?
	 * 
	 * @return buildable
	 */
	public boolean isBuildable()
	{
		return buildable;
	}

	/**
	 * @param buildable
	 */
	public void setBuildable(boolean buildable)
	{
		this.buildable = buildable;
	}

	/**
	 * Is it possible to fly over this Field?
	 * 
	 * @return flyable
	 */
	public boolean isFlyable()
	{
		return flyable;
	}

	/**
	 * @param flyable
	 */
	public void setFlyable(boolean flyable)
	{
		this.flyable = flyable;
	}

	/**
	 * Is it possible to walk over this Field?
	 * 
	 * @return walkable
	 */
	public boolean isWalkable()
	{
		return walkable;
	}

	/**
	 * @param walkable
	 */
	public void setWalkable(boolean walkable)
	{
		this.walkable = walkable;
	}

	public void setSelected(boolean sel)
	{
		selected = sel;
	}

	public boolean getSelected()
	{
		return selected;
	}

	public void setHovered(boolean hov)
	{
		hovered = hov;
	}

	public boolean getHovered()
	{
		return hovered;
	}

	public void paint(Graphics g)
	{
		// Get the size of the Field object
		Dimension size = getSize();

//		if (random < 0.5)
//		{
//			upSideDown((Graphics2D) g, size);
//		}
//
//		if (random2 < 0.5)
//		{
//			mirrored((Graphics2D) g, size);
//		}

		g.drawImage(bf, 0, 0, size.width, size.height, 0, 0, bf.getWidth(null), bf.getHeight(null), null);

//		if (random < 0.5)
//		{
//			upSideDown((Graphics2D) g, size);
//		}
//
//		if (random2 < 0.5)
//		{
//			mirrored((Graphics2D) g, size);
//		}

		if (getSelected())
		{
			g.setColor(new Color(200, 200, 200));
			g.drawRect(0, 0, size.width - 1, size.height - 1);
		}

		if (getHovered() && !getSelected())
		{
			Graphics2D g2D = (Graphics2D) g;
			float alpha = .3f;
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2D.setColor(Color.white);
			g2D.fillRect(0, 0, 40, 40);
			alpha = 1f;
			g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		}
	}

	public void paintPath()
	{
		Graphics g = getGraphics();
		Dimension size = getSize();

		g.setColor(new Color(255, 255, 0));
		g.fillOval(0, 0, size.width, size.height);
	}

	/**
	 * Turns the graphics up size down
	 * 
	 * @param g
	 *            Graphics2D
	 * @param size
	 *            Dimension
	 */
	public void upSideDown(Graphics2D g, Dimension size)
	{
		// It works the same as to convert matrices (not our Matrix objects)
		g.scale(1, -1);
		g.translate(0, -size.height);
	}

	/**
	 * @param g
	 *            Graphics2D
	 * @param size
	 *            Dimension
	 */
	public void mirrored(Graphics2D g, Dimension size)
	{
		g.scale(-1, 1);
		g.translate(-size.width, 0);
	}

	/**
	 * @param f
	 *            Field
	 * @return this.equals(f)
	 */
	public boolean equals(Field f)
	{
		return f != null && f.getLocation().equals(getLocation());
	}

	public String toString()
	{
		String result = "<Field:\t" + getLocation() + ">";

		return result;
	}
}
