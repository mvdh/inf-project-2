import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends Component
{
	public Field()
	{
		setSize(40, 40);
		this.addMouseListener(new FieldMouseAdapter());
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
	
	class FieldMouseAdapter implements MouseListener
	{
		public void mouseClicked(MouseEvent me) 
		{
			// Do nothing
		}

		public void mouseEntered(MouseEvent me) 
		{
			Graphics2D g2 = (Graphics2D) getGraphics();
			Dimension size = getSize();
			g2.setColor(new Color(0, 0, 255));
			g2.fillRect(0, 0, size.width, size.height);
			g2.setColor(new Color(0, 0, 0));
			g2.drawRect(0, 0, size.width, size.height);
		}

		public void mouseExited(MouseEvent me) 
		{
			repaint();
		}

		public void mousePressed(MouseEvent me) 
		{
			// Do nothing
		}

		public void mouseReleased(MouseEvent me) 
		{
			// Do nothing
		}
	}
}
