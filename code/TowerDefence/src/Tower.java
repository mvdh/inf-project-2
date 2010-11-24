import java.awt.Graphics;


public class Tower extends Field
{
	private int range;
	private int hitPoints;
	
	public Tower()
	{
		super();
	}
	
	public void setRange(int rangeIn)
	{
		range = rangeIn;
	}
	
	public void setHitPoints(int hp)
	{
		hitPoints = hp;
	}
	
	public int getRange()
	{
		return range;
	}
	
	public int getHitPoints()
	{
		return hitPoints;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}
