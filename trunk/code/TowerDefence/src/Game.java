
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
                    // Add the Field objects to the JFrame
                    add(f);
                }
            }
        }
        System.out.println(m.toString());
        paintAll(getGraphics());
    }

    // Properties of the JFrame
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

            // remove the Field from the JFrame and add the Tower to it
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

            // remove the Tower from the JFrame and add the Field to it
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

        // OnMouseOver
        public void mouseEntered(MouseEvent me)
        {
            // Get the Graphics of the hovered Component and it's size
            Graphics g = me.getComponent().getGraphics();
            Dimension size = me.getComponent().getSize();
            // Set the Color to draw in
            g.setColor(new Color(0, 0, 255));
            // Draw a rectangle and fill it with the set Color
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(new Color(0, 0, 0));
            // Draw a rectangle, but don't fill it with a Color (border only)
            g.drawRect(0, 0, size.width, size.height);
        }

        // OnMouseOut
        public void mouseExited(MouseEvent me)
        {
            me.getComponent().repaint();
        }

        // OnClick
        public void mousePressed(MouseEvent me)
        {
            // Remove the selected Field from the Matrix
            Field f = m.remove((Field) me.getSource());

            // Checks if a Field was removed, which also was part of the JFrame
            if (f != null && f.getParent() != null)
            {
                // Checks the Class of f
                if (f instanceof Tower)
                {
                    TowerToField(f);
                } else if (f instanceof Boom)
                {
                    // If f has Class Boom, add it again (you can't build on Boom objects)
                    Point p = f.getLocation();
                    boolean added = m.add(f, p.x / 40, p.y / 40);
                    if (!added)
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Something went terribly wrong!");
                    }
                } else
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

    /*
     * @param Unit puppet
     * @param Field target
     * @return calculates the path from the puppet to the target
     * @return null if no path can be found
     */
    private Field[] findPath(Unit puppet, Field target)
    {
        //PathNode queue with some of the funcionality
        Path path = new Path();
        Point start = m.getPoint(puppet.getPath()[puppet.getPathCounter() - 1]);
        Point end = m.getPoint(target);
        //PathNode that will be called in the while loop
        PathNode temp = new PathNode(start.x, start.y, 0);
        path.add(temp);
        PathNode[] tempL = new PathNode[4];
        Field f = null;
        while (!path.contains(end))
        {
            temp = path.next();
            tempL[0] = new PathNode(temp.getX() + 1, temp.getY(), temp.getCount() + 1);
            tempL[1] = new PathNode(temp.getX(), temp.getY() + 1, temp.getCount() + 1);
            tempL[2] = new PathNode(temp.getX() - 1, temp.getY(), temp.getCount() + 1);
            tempL[3] = new PathNode(temp.getX(), temp.getY() - 1, temp.getCount() + 1);
            for (int k = 0; k < 4; k++)
            {
                f = m.get(tempL[k].getX(), tempL[k].getY());
                if (f != null)
                {
                    if (puppet.getAviation() && !f.isFlyable())
                    {
                        tempL[k] = null;
                    } else if (!f.isWalkable())
                    {
                        tempL[k] = null;
                    }
                    if (tempL[k] != null && !path.containsLower(tempL[k]))
                    {
                        path.add(tempL[k]);
                    }
                }
            }
            if (!(path.hasNext()))
            {
                return null;
            }
        }
        PathNode endNode = new PathNode(end.x, end.y, temp.getCount() + 1);
        Field[] fieldResultList = new Field[temp.getCount() + 2];
        for (int i = fieldResultList.length - 1; i > -1; i--)
        {
            fieldResultList[i] = m.get(endNode.getX(), endNode.getY());
            endNode = path.findNext(endNode);
        }
        return fieldResultList;
    }

    /* Tower
     * getMissle();
     */
    public Point fireMissle(Tower shooter, Unit target)
    {
        Point res = null;
        double speedMissle = shooter.getMissleSpeed();
        double speedTarget = target.getSpeed();
        Field[] path = target.getPath();
        double count = 0.56;
        Point tower = m.getPoint(shooter);
        Point unit;
        for (int i = target.getPathCounter() + 1; i < path.length; i++)
        {
            unit = m.getPoint(path[i]); // = spriteList.getPoint(target);
            if (speedMissle
                    >= (Math.sqrt(Math.pow(Math.abs(tower.getX() - unit.getX()), 2)
                    + Math.pow(Math.abs(tower.getY() - unit.getY()), 2))
                    / ((1.0 / speedTarget) * count)))
            {
                res = unit;
                break;
            }
            count++;
        }
        return res;
    }
}