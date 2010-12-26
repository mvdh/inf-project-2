import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Game extends JFrame
{
    private Matrix m;
    private TowerData towerData;
    private SpriteList spriteList;
    private ControlPanel controlPanel;
    private Field selected = null;

    private int amountOfTowers = 0;
    private int maximumLengthOfPath = 0;
    private Vector path;
    private Vector firstPath;

    private Timer actionTimer = null;

    public Game()
    {
        init();
        initHeartbeat();

        m = new Matrix();

        Vector v;
        for (int i = 0; i < 7; i++)
        {
            v = new Vector();
            v.add(new Tree());
            v.add(new Tower());
            // amountOfTowers++;
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

        setTowerData(towerData);
        spriteList = new SpriteList();
        Tower field = new Tower();
        controlPanel = new ControlPanel(getTowerData(), field);
        add(controlPanel);

        System.out.println(m.toString());
        paintAll(getGraphics());

        path = calcPath(new Point(2, 3), new Point(15, 3));
        firstPath = new Vector();
        for (int i = 0; i < path.size(); i++)
        {
            firstPath.add(path.get(i));
        }
    }

    // Properties of the JFrame
    public void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        setLayout(null);

        JButton testButton = new JButton("calc");
        testButton.setSize(150, 25);
        testButton.setLocation(25, 600);
        testButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                path = calcPath(new Point(2, 3), new Point(15, 3));
            }
        });
        add(testButton);
    }

    public void setTowerData(TowerData towerData)
    {
        this.towerData = towerData;
    }

    public TowerData getTowerData()
    {
        return towerData;
    }

    /**
     * Changes the Object type from Field to Tower
     * 
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

            amountOfTowers++;
            if (path != null && path.contains(f))
            {
                m.remove(f);
                boolean added = m.add(t, p.x / 40, p.y / 40);
                if (!added)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
                }

                // remove the Field from the JFrame and add the Tower to it
                remove(f);
                add(t);

                int firstTower = 0;
                for (int i = 0; i < firstPath.size(); i++)
                {
                    Field someField = firstPath.get(i);
                    Point someLoc = m.getPoint(someField);
                    if (m.get(someLoc.y, someLoc.x) instanceof Tower)
                    {
                        firstTower = i;
                        break;
                    }
                }

                int lastTower = 0;
                for (int i = firstPath.size() - 1; i > 0; i--)
                {
                    Field someField = firstPath.get(i);
                    Point someLoc = m.getPoint(someField);
                    if (m.get(someLoc.y, someLoc.x) instanceof Tower)
                    {
                        lastTower = i;
                        break;
                    }
                }

                Field firstField = firstPath.get(firstTower - 1);
                Field lastField = firstPath.get(lastTower + 1);

                Point first = firstField.getLocation();
                Point last = lastField.getLocation();

                Point start = new Point(first.x / 40, first.y / 40);
                Point end = new Point(last.x / 40, last.y / 40);

                Vector[] firstPart = firstPath.split(firstPath.get(firstTower));
                Vector lastPart = firstPart[1];
                if (amountOfTowers > 1 && firstTower != lastTower)
                {
                    lastPart = firstPart[1].split(firstPath.get(lastTower))[1];
                }
                Vector newPart = calcPath(start, end);
                path = firstPart[0].mergeAndCleanUp(newPart).mergeAndCleanUp(lastPart);

                // for (int i = 0; i < firstPath.size(); i++)
                // {
                // Field check = firstPath.get(i);
                // Point loc = m.getPoint(check);
                // if (m.get(loc.y, loc.x) instanceof Tower && i > 0 && i + 1 <
                // firstPath.size())
                // {
                // int x = 1;
                // Field lastField = firstPath.get(i - x);
                // Point lastLoc = m.getPoint(lastField);
                // while (m.get(lastLoc.y, lastLoc.x) instanceof Tower)
                // {
                // x--;
                // lastField = firstPath.get(i + x);
                // lastLoc = m.getPoint(lastField);
                // }
                // x = 1;
                // Field nextField = firstPath.get(i + x);
                // Point nextLoc = m.getPoint(nextField);
                // while (m.get(nextLoc.y, nextLoc.x) instanceof Tower)
                // {
                // x++;
                // nextField = firstPath.get(i + x);
                // nextLoc = m.getPoint(nextField);
                // }
                // Point last = lastField.getLocation();
                // Point next = nextField.getLocation();
                // Point start = new Point(last.x / 40, last.y / 40);
                // Point end = new Point(next.x / 40, next.y / 40);
                //    
                // Vector[] oldPath = firstPath.split(check);
                // Vector newPath = calcPath(start, end);
                // path = oldPath[0].mergeAndCleanUp(newPath).mergeAndCleanUp(
                // oldPath[1]);
                // }
                // }
            }
            else
            {
                m.remove(f);
                boolean added = m.add(t, p.x / 40, p.y / 40);
                if (!added)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
                }

                // remove the Field from the JFrame and add the Tower to it
                remove(f);
                add(t);
            }

            t.repaint();
            System.out.println(m.toString());
            System.out.println(path.print());
        }
    }

    /**
     * Changes the Object type from Tower to Field
     * 
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
            m.remove(t);
            boolean added = m.add(f, p.x / 40, p.y / 40);
            if (!added)
            {
                JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
            }

            // remove the Tower from the JFrame and add the Field to it
            remove(t);
            add(f);
            amountOfTowers--;
            f.repaint();
            System.out.println(m.toString());
        }
    }

    public void initHeartbeat()
    {
        Timer t = new Timer(40, new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO
            }
        });
        t.start();
    }

    /**
     * 
     */

    public Vector calcPath(Point start, Point end)
    {
        Vector result = null;

        if (start != end)
        {
            maximumLengthOfPath = Math.abs(end.x - start.x + end.y - start.y) + (int) Math.round(((double) amountOfTowers) / 2) * 2;
            result = next(new Vector(), start, end);
            if (result == null)
            {
                System.out.println("No shortest path");
            }
        }

        return result;
    }

    public Vector next(Vector passed, Point current, Point end)
    {
        Vector result = null;
        Field f = m.get(current.y, current.x);
        Vector passedNew = new Vector();
        for (int i = 0; i < passed.size(); i++)
        {
            passedNew.add(passed.get(i));
        }
        passedNew.add(f);
        int distance = Math.abs(end.x - current.x + end.y - current.y);

        if (!m.get(end.y, end.x).equals(f) && passed.size() + distance <= maximumLengthOfPath)
        {
            Point p;

            if (current.x < 15)
            {
                p = new Point(current.x + 1, current.y);
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    Vector all = next(passedNew, p, end);
                    if (all != null)
                    {
                        result = all;
                    }
                }
            }

            if (current.x > 0)
            {
                p = new Point(current.x - 1, current.y);
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    Vector all = next(passedNew, p, end);
                    if (all != null)
                    {
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }

            if (current.y > 0)
            {
                p = new Point(current.x, current.y - 1);
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    Vector all = next(passedNew, p, end);
                    if (all != null)
                    {
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }

            if (current.y < 6)
            {
                p = new Point(current.x, current.y + 1);
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    Vector all = next(passedNew, p, end);
                    if (all != null)
                    {
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }
        }
        else if (m.get(end.y, end.x).equals(f))
        {
            result = passedNew;
        }

        return result;
    }

    /**
     * 
     */

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
            select((Field) me.getComponent());
        }

        // OnMouseOut
        public void mouseExited(MouseEvent me)
        {
            if (((Field) me.getComponent()) instanceof Tree || actionTimer == null || !actionTimer.isRunning() || actionTimer.isRunning() && controlPanel.getField() != ((Field) me.getComponent()))
            {
                me.getComponent().repaint();
            }
        }

        // OnClick
        public void mousePressed(MouseEvent me)
        {
            if (selected != null)
            {
                selected.paint(selected.getGraphics());
            }
            // Remove the selected Field from the Matrix
            final Field f = (Field) me.getSource();
            selected = f;
            // Checks if a Field was removed, which also was part of the JFrame
            if (f != null && f.getParent() != null && !(f instanceof Tree))
            {
                select(f);
                remove(controlPanel);
                controlPanel = new ControlPanel(getTowerData(), f);
                add(controlPanel);
                controlPanel.repaint();

                if (actionTimer != null && actionTimer.isRunning())
                {
                    actionTimer.stop();
                }

                actionTimer = new Timer(100, new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        if (controlPanel.hasController() && controlPanel.getController().getTakeAction())
                        {
                            selected = null;
                            if (f instanceof Tower)
                            {
                                TowerToField(f);
                            }
                            else if (!(f instanceof Tree))
                            {
                                FieldToTower(f);
                                controlPanel.getController().setTakeAction(false);
                                System.out.println(controlPanel.getController().getType());
                                remove(controlPanel);
                                repaint();
                            }
                            actionTimer.stop();
                        }
                    }
                });
                actionTimer.start();
            }
        }

        public void mouseReleased(MouseEvent me)
        {
            // Do nothing
        }

        public void select(Field f)
        {
            // Get the Graphics of the hovered Component and it's size
            Graphics g = f.getGraphics();
            Dimension size = f.getSize();
            // Set the Color to draw in
            g.setColor(new Color(0, 0, 255));
            // Draw a rectangle and fill it with the set Color
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(new Color(0, 0, 0));
            // Draw a rectangle, but don't fill it with a Color (border only)
            g.drawRect(0, 0, size.width, size.height);
        }
    }

    /*
     * @param Unit puppet
     * 
     * @param Field target
     * 
     * @return calculates the path from the puppet to the target
     * 
     * @return null if no path can be found
     */
    private Field[] findPath(Unit puppet, Field target)
    {
        // PathNode queue with some of the funcionality
        Path path = new Path();
        Point start = m.getPoint(puppet.getPath()[puppet.getPathCounter() - 1]);
        Point end = m.getPoint(target);
        // PathNode that will be called in the while loop
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
                    }
                    else if (!f.isWalkable())
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

    /*
     * Unit / Sprite getFieldPercentage(); SpriteList getPoint(Unit s);
     */
    public Point fireMissle(Tower shooter, Unit target)
    {
        Point res = null;
        double speedMissle = towerData.getMissleSpeed(shooter.getCaseNumber());
        double speedTarget = target.getSpeed();
        Field[] path = target.getPath();
        double count = 0.56; // = target.getFieldPercentage(); // =
        // target.getFieldPixels() / 40; // = (delta x +
        // delta y) / 40 ;
        Point tower = m.getPoint(shooter);
        Point unit; // = spriteList.getPoint(target);
        for (int i = target.getPathCounter() + 1; i < path.length; i++)
        {
            unit = m.getPoint(path[i]); // = spriteList.getPoint(target);
            if (speedMissle >= (Math.sqrt(Math.pow(Math.abs(tower.getX() - unit.getX()), 2) + Math.pow(Math.abs(tower.getY() - unit.getY()), 2)) / ((1.0 / speedTarget) * count)))
            {
                res = unit;
                break;
            }
            count++;
            /*
             * unit = m.getPoint(path[i]); unit.x *= 40; unit.x += 20; unit.y *=
             * 40; unit.y += 20;
             */
        }
        return res;
    }

    public void heartbeat()
    {
        // step
        spriteList.step();
        // ophogen tower counters
        Vector temp = m.getTowers();
        ArrayList<Unit> unitList = spriteList.getUnitList();
        Tower t;
        Point a;
        Point b;
        for (int i = 0; i < temp.size(); i++)
        {
            t = (Tower) temp.get(i);
            t.count();
            if (t.getCounter() == towerData.getMissleSpeed(t.getCaseNumber()))
            {
                // range check
                a = m.getPoint(t);
                a.x *= 40;
                a.x += 20;
                a.y *= 40;
                a.y += 20;

                for (Unit u : unitList)
                {
                    b = u.getLocation();
                    if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow(a.getY() - b.getY(), 2.0)) <= towerData.getRange(t.getCaseNumber()))
                    {
                        // spriteList.add(new Missle( Missle constructor ,
                        // fireMissle(t, u));
                    }
                }
            }
        }
        // check missle hits
        ArrayList<Projectile> projectileList = spriteList.getProjectileList();
        for (Projectile m : projectileList)
        {
            if (m.getLocation().equals(m.getEnd()))
            {
                for (Unit u : unitList)
                {
                    a = u.getLocation();
                    b = m.getEnd();

                    // check if unit is on the field of destruction!
                    if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow(a.getY() - b.getY(), 2.0)) <= towerData.getRange(m.getRange()))
                    {
                        u.setHitPoints(u.getHitPoints() - m.getDamage());
                        if (u.getHitPoints() <= 0)
                        {
                            // award reward
                            spriteList.remove(u);
                        }
                    }
                }
                m.endMove();
            }
        }
    }
}
