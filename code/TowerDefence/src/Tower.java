import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tower extends Field
{
	private int range;
	private int hitPoints;
	
	public Tower()
	{
		// Just to set the size of the object
		super();
	}
	
	/**
	 * 
	 * @param rangeIn int
	 */
	public void setRange(int rangeIn)
	{
		range = rangeIn;
	}
	
	/**
	 * 
	 * @param hp int
	 */
	public void setHitPoints(int hp)
	{
		hitPoints = hp;
	}
	
	/**
	 * 
	 * @return range
	 */
	public int getRange()
	{
		return range;
	}
	
	/**
	 * 
	 * @return hitpoints
	 */
	public int getHitPoints()
	{
		return hitPoints;
	}
	
	public void paint(Graphics g)
	{
		// Execute the paint function as if it was a Field object
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Dimension size = getSize();
		// Add a filled rectangle to the graphics
		g2.setColor(new Color(0, 255, 0));
		g2.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
	}
}
