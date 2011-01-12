import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame
{

    private Matrix m;
    private UnitData unitData;
    private SpriteList spriteList;
    private ControlPanel controlPanel;
    private JPanel fieldPanel = new JPanel();
    private JPanel statsPanel = new JPanel();
    private JLabel goldLbl = new JLabel();
    private JLabel pointsLbl = new JLabel();
    private JLabel healthLbl = new JLabel();
    private Field selected = null;
    private Vector path;
    private Timer actionTimer = null;
    // Start values
    private int gold = 0;
    private int castleHealth = 600;
    // Field background image
    private BufferedImage bf = null;
    private GameStats gameStats = new GameStats();

    public Game()
    {
        init();

        m = new Matrix();

        URL url = getClass().getResource("images/grass.png");
        try
        {
            bf = ImageIO.read(url);
        }
        catch (Exception e)
        {}

        statsPanel.setLayout(new GridLayout(1, 3));
        statsPanel.setSize(670, 20);
        statsPanel.setLocation(10, 0);

        statsPanel.add(goldLbl);
        statsPanel.add(pointsLbl);
        statsPanel.add(healthLbl);

        fieldPanel.setLayout(null);
        fieldPanel.setSize(680, 360);
        fieldPanel.setLocation(0, 20);

        Vector v;
        for (int i = 0; i < 9; i++)
        {
            v = new Vector();
            if (i != 4)
            {
                v.add(new Tree(bf));
            }
            else
            {
                Field toBeAdded = new Field(bf);
                toBeAdded.setBuildable(false);
                v.add(toBeAdded);
            }

            for (int j = 0; j < 13; j++)
            {
                v.add(new Field(bf));
            }

            if (i != 4)
            {
                v.add(new Brick(bf));
            }
            else
            {
                Field toBeAdded = new Field(bf);
                toBeAdded.setBuildable(false);
                v.add(toBeAdded);
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
                if (f.isBuildable())
                {
                    f.addMouseListener(new GameMouseAdapter());
                }
                // Add the Field objects to the JFrame
                fieldPanel.add(f);
                // System.out.println(f.getLocation());
            }
        }

        spriteList = new SpriteList();
        unitData = new UnitData();
        add(statsPanel);
        updateStats();
        add(fieldPanel);

        // System.out.println(m.toString());
        paintAll(getGraphics());

        path = new Vector();
        for (int i = 0; i < 15; i++)
        {
            path.add(m.get(4, i));
        }

        Timer t = new Timer(2000, new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                Unit a = new Unit(1.5, 20);
                // a.setSize(20, 20);
                // a.setLocation(new Point(-(int) a.getSize().getWidth(), 190));
                a.setNewDestination(a.getLocation());
                a.setPath(path);
                // a.setVisible(true);
                getLayeredPane().add(a, JLayeredPane.PALETTE_LAYER);
                spriteList.add(a);

            }
        });
        t.start();

        initHeartbeat();
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
                // path = calcPath(new Point(2, 3), new Point(15, 3));

                for (int i = 0; i < path.size(); i++)
                {
                    path.get(i).paintPath();
                }
            }
        });
        add(testButton);
    }

    public void updateStats()
    {
        goldLbl.setText("Gold: " + gold);
        pointsLbl.setText("Points: " + gameStats.getPoints());
        healthLbl.setText("Health: " + castleHealth);
    }

    /**
     * Changes the Object type from Field to Tower
     * 
     * @param f Field
     */
    public void FieldToTower(Field f, int type)
    {
        if (f != null && f.isBuildable())
        {
            Point p = f.getLocation();
            Tower t = new Tower(bf, type);
            t.setLocation(p);
            t.addMouseListener(new GameMouseAdapter());
            t.setWalkable(false);
            m.remove(f);
            boolean added = m.add(t, p.x / 40, p.y / 40);
            if (!added)
            {
                JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
            } else {
                this.gameStats.updatePoints(-1, this.gameStats.getTowerData().getCosts(type));
            }
            fieldPanel.remove(f);
            fieldPanel.add(t);
            checkPath(f);
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

            fieldPanel.remove(t);
            if(added){

                int lastCaseNumber = ((Tower) t).getCaseNumber();
                int totalCosts = 0;
                
                while(lastCaseNumber >= 0){
                    totalCosts += gameStats.getTowerData().getCosts(lastCaseNumber);
                    lastCaseNumber = gameStats.getTowerData().getPreviousCaseNumber(lastCaseNumber);
                }

                totalCosts = (totalCosts/10)*7;
                gameStats.updatePoints(1, totalCosts);
                //check which tower there also where
            }
            fieldPanel.add(f);
            checkPath(f);
        }
    }

    public void upgradeTower(Tower t, int newCase)
    {
        if (t != null)
        {
            Point p = t.getLocation();
            Tower nT = new Tower(bf, newCase);
            nT.setLocation(p);
            nT.addMouseListener(new GameMouseAdapter());
            nT.setWalkable(false);
            m.remove(t);
            boolean added = m.add(nT, p.x / 40, p.y / 40);
            if (!added)
            {
                JOptionPane.showMessageDialog(new JFrame(), "The selected index wasn't empty. Something went wrong!");
            } else {
                this.gameStats.updatePoints(-1, this.gameStats.getTowerData().getCosts(newCase));
            }

            fieldPanel.remove(t);
            fieldPanel.add(nT);
            checkPath(nT);
        }
    }

    public void initHeartbeat()
    {
        Timer t = new Timer(40, new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {
                heartbeat();
                updateStats();
            }
        });
        t.start();
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
    private Vector findPath(Unit puppet, Field target)
    {
        // System.out.println(System.currentTimeMillis());
        // PathNode queue with some of the funcionality
        Path path = new Path();
        Point start = puppet.getLocation();
        start.x /= 40;// m.getPoint(puppet.getPath()[puppet.getPathCounter() -
        // 1]);
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
            // System.out.println("pathfind " + temp.getCount() + "\n" +
            // m.get(temp.getY(), temp.getX()).isFlyable());
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
        // System.out.println(System.currentTimeMillis());
        return new Vector(fieldResultList);
    }

    /*
     * Unit / Sprite getFieldPercentage(); SpriteList getPoint(Unit s);
     */
    public Point fireMissle(Tower shooter, Unit target)
    {
        Point res = null;
        double speedMissle = gameStats.getTowerData().getMissleSpeed(shooter.getCaseNumber());
        double speedTarget = target.getSpeed();
        Vector path = target.getPath();
        double count = 0; // = target.getFieldPercentage(); // =
        // target.getFieldPixels() / 40; // = (delta x +
        // delta y) / 40 ;
        Point tower = m.getPoint(shooter);
        Point unit; // = spriteList.getPoint(target);
        for (int i = target.getPathCounter() + 1; i < path.size(); i++)
        {
            unit = m.getPoint(path.get(i)); // = spriteList.getPoint(target);
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
        for (Unit u : spriteList.getUnitList())
        {
            a = u.getLocation();
            a.x /= 40;
            a.y /= 40;
            if (m.get(a.y, a.x) instanceof Tower)
            {

                t = (Tower) m.get(a.y, a.x);
                if ((!t.isFlyable() && u.getAviation()) || (!t.isWalkable() && !u.getAviation()))
                {
                    cleanUp.add(u);
                    t.setHealth(t.getHealth() - 100);
                }
                // Tower healthcheck <= 0
                // TowerToField
            }
            if (a.x == 14 && a.y == 4)
            {
                cleanUp.add(u);
                castleHealth -= (u.getCaseNumber() + 1) * 2;
            }
        }
        for (int i = 0; i < temp.size(); i++)
        {
            t = (Tower) temp.get(i);
            t.count();
            if (t != null)
            {
                if (t.getCounter() == gameStats.getTowerData().getMissleSpeed(t.getCaseNumber()))
                {
                    // range check
                    a = t.getLocation();
                    a.x += 20;
                    a.y += 20;
                    for (Unit u : spriteList.getUnitList())
                    {
                        b = u.getLocation();
                        b.x += u.getWidth();
                        b.y += u.getHeight();
                        if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow(a.getY() - b.getY(), 2.0)) <= gameStats.getTowerData().getRange(t.getCaseNumber()))
                        {
                            Projectile tempP = new Projectile(gameStats.getTowerData().getMissleDamage(t.getCaseNumber()), gameStats.getTowerData().getMissleImage(t.getCaseNumber()), (double) gameStats.getTowerData().getMissleSpeed(t
                                    .getCaseNumber()), gameStats.getTowerData().getMissleRange(t.getCaseNumber()), b, t.getLocation());
                            getLayeredPane().add(tempP, JLayeredPane.PALETTE_LAYER);
                            spriteList.add(tempP);
                            // System.out.println(gameStats.getTowerData().getMissleDamage(t.getCaseNumber()));
                            break;
                        }
                    }
                    t.setCounter(0);
                }
            }
        }
        // check missle hits
        for (Projectile pr : spriteList.getProjectileList())
        {
            if (pr.getLocation().equals(pr.getEnd()))
            {
                for (Unit u : spriteList.getUnitList())
                {
                    a = u.getLocation();
                    a.x += u.getWidth() / 2;
                    a.y += u.getHeight() / 2;
                    b = pr.getEnd();
                    // System.out.println(pr.getDamage());
                    // check if unit is on the field of destruction!
                    if (Math.sqrt(Math.pow((a.getX() - b.getX()), 2.0) + Math.pow(a.getY() - b.getY(), 2.0)) <= pr.getRange())
                    {
                        u.setHitPoints(u.getHitPoints() - pr.getDamage());
                        System.out.println(u.getHitPoints());
                        if (u.getHitPoints() <= 0)
                        {
                            gold += unitData.getReward(u.getCaseNumber());
                            //points += unitData.getReward(u.getCaseNumber()) * 5;
                            gameStats.updatePoints(1, unitData.getReward(u.getCaseNumber()) * 5);
                            cleanUp.add(u);
                        }
                    }
                }
                pr.endMove();
                cleanUp.add(pr);
            }
        }
        for (Sprite s : cleanUp)
        {
            s.setVisible(false);
            remove(s);
            spriteList.remove(s);
        }
    }

    public void checkPath(Field f)
    {
        Field firstField = m.get(4, 0);
        Point first = firstField.getLocation();

        Unit unit = new Unit(0.0, 0);
        unit.setLocation(first);

        Vector fields = findPath(unit, m.get(4, 14));
        if (fields != null)
        {
            path = fields;
        }
        for (Unit u : spriteList.getUnitList())
        {
            // u.setPath(path.mergeAndCleanUp(u.getPath()));
            u.setPath(findPath(u, m.get(4, 14)));
            // u.setPathCounter(u.findIndexOfNearestNextPath());
            u.setPathCounter(1);
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
            Field hov = (Field) me.getComponent();
            if (!hov.equals(selected))
            {
                hov.setHovered(true);
                hov.paint(hov.getGraphics());
            }
        }

        // OnMouseOut
        public void mouseExited(MouseEvent me)
        {
            Field hov = (Field) me.getComponent();
            hov.setHovered(false);
            hov.paint(hov.getGraphics());
        }

        // OnClick
        public void mousePressed(MouseEvent me)
        {
            // In case a second Field gets selected, the previous Field has to
            // be repainted
            if (selected != null)
            {
                selected.setSelected(false);
                selected.paint(selected.getGraphics());
            }
            // Remove the selected Field from the Matrix
            final Field f = (Field) me.getSource();
            // Checks if a Field was removed, which also was part of the JFrame
            if (f != null && f.getParent() != null && !(f instanceof Tree))
            {
                selected = f;
                f.setSelected(true);
                f.paint(f.getGraphics());
                if (controlPanel != null)
                {
                    remove(controlPanel);
                }
                controlPanel = new ControlPanel(gameStats, f);
                add(controlPanel);
                controlPanel.repaint();

                // Checks if the Timer is running, if so, it has to stop
                if (actionTimer != null && actionTimer.isRunning())
                {
                    actionTimer.stop();
                }

                // Create Timer to check if a Tower is selected in the
                // controlPanel
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
                                int type = controlPanel.getController().getType();
                                if (type == -1)
                                {
                                    TowerToField(f);
                                }
                                else
                                {
                                    upgradeTower((Tower) f, type);
                                }
                                controlPanel.getController().setTakeAction(false);
                                remove(controlPanel);
                                repaint();
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
    }
}
