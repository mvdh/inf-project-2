import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Field extends Component
{
	public Field()
	{
		setSize(40, 40);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Dimension size = getSize();
		g2.setColor(new Color(255, 0, 0));
		g2.fillRect(0, 0, size.width, size.height);
		g2.setColor(new Color(0, 0, 0));
		g2.drawRect(0, 0, size.width, size.height);
		
	}
}
