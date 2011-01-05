
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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

    private Timer actionTimer = null;
    private BufferedImage bf = null;

    public Game()
    {
        init();
        initHeartbeat();

        m = new Matrix();

        URL url = getClass().getResource("images/grass.png");
        try
        {
            bf = ImageIO.read(url);
        }
        catch (Exception e)
        {}
        
        Vector v;
        for (int i = 0; i < 7; i++)
        {
            v = new Vector();
            v.add(new Tree(bf));
            v.add(new Tower(bf));
            // amountOfTowers++;
            for (int j = 0; j < 15; j++)
            {
                v.add(new Field(bf));
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
        Tower field = new Tower(bf);
        controlPanel = new ControlPanel(getTowerData(), field);
        add(controlPanel);

        //System.out.println(m.toString());
        paintAll(getGraphics());

        path = new Vector();
        for (int i = 2; i < 17; i++)
        {
            path.add(m.get(3, i));
        }
    }

    // Properties of the JFrame
    public void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        setLayout(null);

        JButton testButton = new JButton("ShowPath");
        testButton.setSize(150, 25);
        testButton.setLocation(25, 600);
        testButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //path = calcPath(new Point(2, 3), new Point(15, 3));
                
                for (int i = 0; i < path.size(); i++)
                {
                    path.get(i).paintPath();
                }
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
    public void FieldToTower(Field f, int type)
    {
        if (f != null)
        {
            Point p = f.getLocation();
            Tower t = new Tower(bf, type);
            t.setLocation(p);
            t.addMouseListener(new GameMouseAdapter());
            t.setWalkable(false);
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

                /*
                 *  Path finding
                 *  First find the first and last Tower objects which are placed in the ideal route.
                 *  Then get the Field object before the first Tower and the Field object after the
                 *  last Tower, split the ideal path at those points. Calculate the shortest path 
                 *  between those 2 Field objects and merge the 3 parts together (before first Tower,
                 *  new calculated path and after last Tower.
                 */ 
                
                Field firstField = m.get(3, 2);
                Point first = firstField.getLocation();

                Unit unit = new Unit();
                unit.setLocation(first);
                
                Field[] fields = findPath(unit, m.get(3, 16));
                Vector newPart = new Vector();
                for(int i = 0; i < fields.length; i++){
                    newPart.add(fields[i]);
                }

                path = newPart;
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
            Field f = new Field(bf);
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
         //   System.out.println(m.toString());
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
     * @param start Point
     * @param end Point
     * @return 
     * Shortest path in a Vector
     */
    public Vector calcPath(Point start, Point end)
    {
        Vector result = null;

        // If the start isn't equal to the end, it is necessary to calculate a path
        if (start != end)
        {
            // The maximum length of the path is the ideal path + 2 * (the amount of Tower objects divided by 2,
            // rounded (1/2 = 1, 2/2 = 1, 3/2 = 2, 4/2 = 2, etc.))
            maximumLengthOfPath = Math.abs(end.x - start.x + end.y - start.y) + (int) Math.round(((double) amountOfTowers) / 2) * 2;
            result = next(new Vector(), start, end);
            // If no path is found
            if (result == null)
            {
                System.out.println("No shortest path");
            }
        }

        return result;
    }

    /**
     * 
     * @param passed Vector
     * @param current Point
     * @param end Point
     * @return
     * The shortest path from the current Point to the end Point
     */
    public Vector next(Vector passed, Point current, Point end)
    {
        Vector result = null;
        // Get the current Field
        Field f = m.get(current.y, current.x);
        // At the current Field to the Field objects which have been passed, needs to be a new object
        Vector passedNew = new Vector();
        for (int i = 0; i < passed.size(); i++)
        {
            passedNew.add(passed.get(i));
        }
        passedNew.add(f);
        // Calculate the distance between the current Point and the end Point
        int distance = Math.abs(end.x - current.x + end.y - current.y);

        // If the end Point isn't reached yet and the amount of passed Field objects + the distance to the end
        // is smaller then the maximum length of the path
        if (!m.get(end.y, end.x).equals(f) && passed.size() + distance <= maximumLengthOfPath)
        {
            Point p;

            // If it's possible to go West
            if (current.x < 15)
            {
                p = new Point(current.x + 1, current.y);
                // If p isn't a Tower and the Vector of passed objects doesn't contain the Field
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    // Look further
                    Vector all = next(passedNew, p, end);
                    
                    // all isn't equals to null if the end Point is reached
                    if (all != null)
                    {
                        result = all;
                    }
                }
            }

            // If it's possible to go East
            if (current.x > 0)
            {
                p = new Point(current.x - 1, current.y);
                // If p isn't a Tower and the Vector of passed objects doesn't contain the Field
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    // Look further
                    Vector all = next(passedNew, p, end);

                    // all isn't equals to null if the end Point is reached
                    if (all != null)
                    {
                        // If there is no path found, or the path found is shorter
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }

            // If it's possible to go South
            if (current.y > 0)
            {
                p = new Point(current.x, current.y - 1);
                // If p isn't a Tower and the Vector of passed objects doesn't contain the Field
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    // Look further
                    Vector all = next(passedNew, p, end);

                    // all isn't equals to null if the end Point is reached
                    if (all != null)
                    {
                        // If there is no path found, or the path found is shorter
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }

            // If it's possible to go North
            if (current.y < 6)
            {
                p = new Point(current.x, current.y + 1);
                // If p isn't a Tower and the Vector of passed objects doesn't contain the Field
                if (!(m.get(p.y, p.x) instanceof Tower) && !passedNew.contains(m.get(p.y, p.x)))
                {
                    // Look further
                    Vector all = next(passedNew, p, end);

                    // all isn't equals to null if the end Point is reached
                    if (all != null)
                    {
                        // If there is no path found, or the path found is shorter
                        if (result == null || result != null && result.size() > all.size())
                        {
                            result = all;
                        }
                    }
                }
            }
        }
        // If the current Field equals the end Field, result = all passed Field objects
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
            // In case a second Field gets selected, the previous Field has to be repainted
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

                // Checks if the Timer is running, if so, it has to stop
                if (actionTimer != null && actionTimer.isRunning())
                {
                    actionTimer.stop();
                }

                // Create Timer to check if a Tower is selected in the controlPanel
                actionTimer = new Timer(100, new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        // If a Tower is selected -> take action
                        if (controlPanel.hasController() && controlPanel.getController().getTakeAction())
                        {
                            selected = null;
                            if (f instanceof Tower)
                            {
                                TowerToField(f);
                            }
                            else if (!(f instanceof Tree))
                            {
                                int type = controlPanel.getController().getType();
                                FieldToTower(f, type);
                                controlPanel.getController().setTakeAction(false);
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
        //System.out.println(System.currentTimeMillis());
        // PathNode queue with some of the funcionality
        Path path = new Path();
        Point start = puppet.getLocation();
        start.x /= 40;//m.getPoint(puppet.getPath()[puppet.getPathCounter() - 1]);
        start.y /= 40;
        Point end = target.getLocation();
        end.x /= 40;
        end.y /= 40;
        // PathNode that will be called in the while loop
        PathNode temp = new PathNode(start.x, start.y, 0);
        path.add(temp);
        PathNode[] tempL = new PathNode[4];
        Field f = null;
        while (!path.contains(end))
        {
            //System.out.println("pathfind "  +  temp.getCount() + "\n" + m.get(temp.getY(), temp.getX()).isFlyable());
            temp = path.next();
            tempL[0] = new PathNode(temp.getX() + 1, temp.getY(), temp.getCount() + 1);
            tempL[1] = new PathNode(temp.getX(), temp.getY() + 1, temp.getCount() + 1);
            tempL[2] = new PathNode(temp.getX() - 1, temp.getY(), temp.getCount() + 1);
            tempL[3] = new PathNode(temp.getX(), temp.getY() - 1, temp.getCount() + 1);
            for (int k = 0; k < 4; k++)
            {
                f = m.get(tempL[k].getY(), tempL[k].getX());
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
            fieldResultList[i] = m.get(endNode.getY(), endNode.getX());
            endNode = path.findNext(endNode);
        }
        //System.out.println(System.currentTimeMillis());
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
        ArrayList<Sprite> cleanUp = new ArrayList<Sprite>();
        Tower t;
        Point a;
        Point b;
        for (int i = 0; i < temp.size(); i++)
        {
            t = (Tower) temp.get(i);
            t.count();
            if (t != null)
            {
                if (t.getCounter() == towerData.getMissleSpeed(t.getCaseNumber()))
                {
                    // range check
                    a = t.getLocation();
                    a.x += 20;
                    a.y += 20;
                    for (Unit u : spriteList.getUnitList())
                    {
                        b = u.getLocation();
                        if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0)
                                + Math.pow(a.getY() - b.getY(), 2.0)) <= towerData.getRange(t.getCaseNumber()))
                        {
                            Projectile tempP = new Projectile(towerData.getMissleDamage(t.getCaseNumber()), towerData.getMissleImage(t.getCaseNumber()), (double) towerData.getMissleSpeed(t.getCaseNumber()), towerData.getMissleRange(t.getCaseNumber()), fireMissle(t, u), a);
                            spriteList.add(tempP);
                            add(tempP);
                            break;
                        }
                    }
                }
            }
        }
        // check missle hits
        for (Projectile m : spriteList.getProjectileList())
        {
            if (m.getLocation().equals(m.getEnd()))
            {
                for (Unit u : spriteList.getUnitList())
                {
                    a = u.getLocation();
                    b = m.getEnd();

                    // check if unit is on the field of destruction!
                    if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow(a.getY() - b.getY(), 2.0)) <= towerData.getRange(m.getRange()))
                    {
                        u.setHitPoints(u.getHitPoints() - m.getDamage());
                        if (u.getHitPoints() <= 0)
                        {
                            //award reward
                            remove(u);
                            spriteList.remove(u);
                            cleanUp.add(u);
                        }
                    }
                }
                m.endMove();
                remove(m);
                spriteList.remove(m);
                cleanUp.add(m);
            }
        }
        for (Sprite s : cleanUp)
        {
            spriteList.remove(s);
        }
    }
}

