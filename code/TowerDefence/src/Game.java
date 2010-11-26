import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame
{
	private Matrix m; 
	
	public Game()
	{
		init();
		
		m = new Matrix();
		
		Vector v;
		for (int i = 0; i < 7; i++)
		{
			v = new Vector();
			v.add(new Boom());
			v.add(new Tower());
			for (int j = 0; j < 15; j++)
			{
				v.add(new Field());
			}
			m.add(v);
		}

		Field f;
		for (int i = 0; i < m.size(); i++)
		{
			for (int j = 0; j < m.get(i).size(); j++)
			{
				f = m.get(i, j);
				f.setLocation(j * 40, i * 40);
				f.addMouseListener(new GameMouseAdapter());
				if (f != null)
				{
					add(f);
					System.out.println(m.get(m.getPoint(f).y, m.getPoint(f).x));
				}
			}
		}
		System.out.println(m.toString());
		paintAll(getGraphics());
	}
	
	public void init()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
		setLayout(null);
	}
	
	/**
	 * Changes the Object type from Field to Tower
	 * @param f Field
	 */
	public void FieldToTower(Field f)
	{
		if (f != null)
		{
			Point p = f.getLocation();
			Tower t = new Tower();
			t.setLocation(p);
			t.addMouseListener(new GameMouseAdapter());
			boolean added = m.add(t, p.x / 40, p.y / 40);
			if (!added)
			{
				JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
			}
			remove(f);
			add(t);
			paintAll(getGraphics());
		}
	}
	
	/**
	 * Changes the Object type from Tower to Field
	 * @param t Field
	 */
	public void TowerToField(Field t)
	{
		if (t != null)
		{
			Point p = t.getLocation();
			Field f = new Field();
			f.setLocation(p);
			f.addMouseListener(new GameMouseAdapter());
			boolean added = m.add(f, p.x / 40, p.y / 40);
			if (!added)
			{
				JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
			}
			remove(t);
			add(f);
			paintAll(getGraphics());
		}
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
	class GameMouseAdapter implements MouseListener
	{
		public void mouseClicked(MouseEvent me) 
		{
			Field f = m.remove((Field) me.getSource());
			if (f != null && f.getParent() != null)
			{
				if (f instanceof Tower)
				{
					TowerToField(f);
				}
				else if (f instanceof Boom)
				{
					Point p = f.getLocation();
					boolean added = m.add(f, p.x / 40, p.y / 40);
					if (!added)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Something went terribly wrong!");
					}
				}
				else
				{
					FieldToTower(f);
				}
			}
			
			System.out.println(m.toString());
		}

		public void mouseEntered(MouseEvent me) 
		{
			// Do nothing
		}

		public void mouseExited(MouseEvent me) 
		{
			// Do nothing
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
