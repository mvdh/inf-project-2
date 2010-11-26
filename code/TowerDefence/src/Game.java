import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
			// Do nothing
		}

		public void mouseEntered(MouseEvent me) 
		{
			Graphics2D g2 = (Graphics2D) me.getComponent().getGraphics();
			Dimension size = getSize();
			g2.setColor(new Color(0, 0, 255));
			g2.fillRect(0, 0, size.width, size.height);
			g2.setColor(new Color(0, 0, 0));
			g2.drawRect(0, 0, size.width, size.height);
		}

		public void mouseExited(MouseEvent me) 
		{
			me.getComponent().repaint();
		}

		public void mousePressed(MouseEvent me) 
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

		public void mouseReleased(MouseEvent me) 
		{
			// Do nothing
		}
	}

        /* NOG TOEVOEGEN
    áá á * Unit extends Field?
    áá á á ápublic boolean getAviation();
    áá á á áhealth, attack, speed, path? etc...
    áá á * Missle extends Field?
    áá á á áattack, speed
    áá á á ákan ook met final ints die naar plaatjes verwijzen, bijv:
    áá á á á á ápublic Missle(attack, missleSpeed, Missle.ARROW)
    áá á * Tower
         * áá á á áhealth, attack, missleSpeed,attack speed, cummulative price, price?,
    áá á á ápublic Missle fire();
    áá á á á á áreturn (new Missle(attack, missleSpeed, Missle.ARROW));
    áá á * Game
    áá á á áAndra's attack-path algoritme? (uitrekenen locatie targeted unit en dan dat field als target doorsturen naar aStar
    áá á */
/*        private Field[] aStar(Unit puppet, Field target)
        {
                Path path = new Path();
                Point start = m.getPoint(puppet);
                Point end = m.getPoint(target);
                PathNode temp = new PathNode(start.x, start.y, 0);
                path.add(temp);
                PathNode[] tempL = new PathNode[4];
                int j = 0;
                while (!path.contains(end))
                {
                        temp = path.next();
                        tempL[0] = new PathNode(temp.getX() + 1, temp.getY(), temp.getCount() + 1);
                        tempL[1] = new PathNode(temp.getX(), temp.getY() + 1, temp.getCount() + 1);
                        tempL[2] = new PathNode(temp.getX() - 1, temp.getY(), temp.getCount() + 1);
                        tempL[3] = new PathNode(temp.getX(), temp.getY() - 1, temp.getCount() + 1);
                        for (int k = 0; k < tempL.length; k++)
                        {
                                if (puppet.getAviation())
                                {
                                        if (!m.get(tempL[k].getX(), tempL[k].getY()).isFlyable())
                                        {
                                                tempL[k] = null;
                                        } else if (!m.get(tempL[k].getX(), tempL[k].getY()).isWalkable())
                                        {
                                                tempL[k] = null;
                                        }
                                }
                                if (tempL[k] != null && !path.containsLower(tempL[k]))
                                {
                                        path.add(tempL[k]);
                                }
                        }
                        j++;
                        if (j > 150)
                        {
                                return null;
                        }
                }
                PathNode[] res = new PathNode[temp.getCount() + 2];
                res[res.length - 1] = new PathNode(end.x, end.y, temp.getCount() + 1);
                for (int i = res.length - 1; i > 0; i--)
                {
                        res[i - 1] = path.findNext(res[i]);
                }
                Field[] fRes = new Field[res.length];
                for (int i = 0; i < res.length; i++)
                {
                        fRes[i] = m.get(res[i].getX(), res[i].getY());
                }
                return fRes;
        }*/
}
