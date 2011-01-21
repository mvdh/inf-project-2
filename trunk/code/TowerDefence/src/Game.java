import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JApplet
{

	private Matrix m;
	// private UnitData unitData;
	private SpriteList spriteList;
	private ControlPanel controlPanel;
	private JPanel fieldPanel = new JPanel();
	private StatsBar statsPanel = new StatsBar();
	private LeftSide leftSide = new LeftSide();
	private JLabel goldLbl = new JLabel();
	private JLabel scoreLbl = new JLabel();
	private JLabel healthLbl = new JLabel();
	private JLabel waveLbl = new JLabel();
	private Field selected = null;
	private Vector path;
	private Timer actionTimer = null;
	// Start values
	// Field background image
	private GameStats gameStats;
	private BufferedImage bf = null;
	private BufferedImage background = null;
	private BufferedImage statsBarImage = null;
	private BufferedImage leftSideImage = null;
	private Panel panel;
	private Font statsFont = new Font("Arial", Font.BOLD, 18);

	public Game()
	{
		gameStats = new GameStats(this);

		panel = new StartPanel(gameStats);
		getLayeredPane().add(panel, JLayeredPane.POPUP_LAYER);

		init();
		loadImages();
		// panel = new StartPanel(gameStats);
		// getLayeredPane().add(panel, JLayeredPane.PALETTE_LAYER);

		generateMatrix();

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
		getLayeredPane().add(statsPanel, JLayeredPane.POPUP_LAYER);
		getLayeredPane().add(leftSide, JLayeredPane.POPUP_LAYER);
		getLayeredPane().add(fieldPanel, JLayeredPane.PALETTE_LAYER);

		// System.out.println(m.toString());
		paintAll(getGraphics());

		path = new Vector();
		for (int i = 0; i < 15; i++)
		{
			path.add(m.get(4, i));
		}

		initHeartbeat();
	}

	public void generateMatrix()
	{
		m = new Matrix();
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
				Field toBeAdded;
				toBeAdded = new Field(bf);
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
	}

	// Properties of the JFrame
	public void init()
	{
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
		setLayout(null);

		getLayeredPane().add(new BackGround(), 1);

		statsPanel.setLayout(new GridLayout(1, 4));
		statsPanel.setSize(600, 40);
		statsPanel.setLocation(50, 50);
		statsPanel.setOpaque(false);
		statsPanel.paintComponents(statsPanel.getGraphics());

		goldLbl.setFont(statsFont);
		goldLbl.setForeground(Color.white);
		scoreLbl.setFont(statsFont);
		scoreLbl.setForeground(Color.white);
		healthLbl.setFont(statsFont);
		healthLbl.setForeground(Color.white);
		waveLbl.setFont(statsFont);
		waveLbl.setForeground(Color.white);

		statsPanel.add(waveLbl);
		statsPanel.add(goldLbl);
		statsPanel.add(scoreLbl);
		statsPanel.add(healthLbl);

		fieldPanel.setLayout(null);
		fieldPanel.setSize(680, 360);
		fieldPanel.setLocation(50, 90);
		fieldPanel.setOpaque(false);
	}

	public void loadImages()
	{
		URL backgroundURL = getClass().getResource("images/background.png");
		URL grassURL = getClass().getResource("images/grass.png");
		URL statsBarURL = getClass().getResource("images/statsbar.png");
		URL leftSideURL = getClass().getResource("images/leftside.png");

		try
		{
			bf = ImageIO.read(grassURL);
		}
		catch (Exception e) 
		{}

		try
		{
			background = ImageIO.read(backgroundURL);
		}
		catch (Exception e)
		{}

		try
		{
			statsBarImage = ImageIO.read(statsBarURL);
		}
		catch (Exception e)
		{}

		try
		{
			leftSideImage = ImageIO.read(leftSideURL);
		}
		catch (Exception e)
		{}
	}

	public void updateStats()
	{
		goldLbl.setText(gameStats.getGold() + "");
		goldLbl.setIcon(new ImageIcon(gameStats.coinsImages[0]));
		scoreLbl.setText("    Score: " + gameStats.getPoints());
		healthLbl.setText("    Health: " + gameStats.getCastleHealth());
		waveLbl.setText("    Wave: " + (gameStats.getWave() + 1));
	}

	/**
	 * Changes the Object type from Field to Tower
	 *
	 * @param f
	 *            Field
	 */
	public void FieldToTower(Field f, int type)
	{
		if (f != null && f.isBuildable())
		{
			Point p = f.getLocation();
			Tower t = new Tower(bf, type, gameStats.getTowerData().getHitpoints(type), gameStats.getTowerData());
			t.setLocation(p);
			t.addMouseListener(new GameMouseAdapter());
			t.setWalkable(false);
			m.remove(f);
			boolean added = m.add(t, p.x / 40, p.y / 40);
			if (added)
			{
				this.gameStats.updateGold(-1, this.gameStats.getTowerData().getCosts(type));
			}
			fieldPanel.remove(f);
			f = null;
			fieldPanel.add(t);
			checkPath(t);
		}
	}

	/**
	 * Changes the Object type from Tower to Field
	 *
	 * @param t
	 *            Field
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

			if (added)
			{

				int lastCaseNumber = ((Tower) t).getCaseNumber();
				double totalCosts = 0;

				while (lastCaseNumber >= 0)
				{
					totalCosts += gameStats.getTowerData().getCosts(lastCaseNumber);
					lastCaseNumber = gameStats.getTowerData().getPreviousCaseNumber(lastCaseNumber);
				}

				totalCosts = (totalCosts / 10) * 7;
				gameStats.updateGold(1, (int) totalCosts);
				// check which tower there also where
			}
			fieldPanel.remove(t);
			t = null;
			fieldPanel.add(f);
			checkPath(f);
		}
	}

	public void upgradeTower(Tower t, int newCase)
	{
		if (t != null)
		{
			Point p = t.getLocation();
			Tower nT = new Tower(bf, newCase, gameStats.getTowerData().getHitpoints(newCase), gameStats.getTowerData());
			nT.setLocation(p);
			nT.addMouseListener(new GameMouseAdapter());
			nT.setWalkable(false);
			m.remove(t);
			boolean added = m.add(nT, p.x / 40, p.y / 40);
			if (added)
			{
				this.gameStats.updateGold(-1, this.gameStats.getTowerData().getCosts(newCase));
			}
			fieldPanel.remove(t);
			t = null;
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
	 * @param Field target
	 * @return calculates the path from the puppet to the target
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
		if (end.x == start.x && end.y == start.y)
		{
			Vector v = new Vector();
			v.add(target);
			return v;
		}
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
		if (gameStats.isStarted())
		{
			// long testTime = System.currentTimeMillis();
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
				a.x -= 50;
				a.x /= 40;
				a.y -= 70;
				a.y /= 40;
				if (m.get(a.y, a.x) instanceof Tower)
				{

					t = (Tower) m.get(a.y, a.x);
					if ((!t.isFlyable() && u.getAviation()) || (!t.isWalkable() && !u.getAviation()))
					{
						cleanUp.add(u);
						if (path.contains(t))
						{
							t.setHealth(0);
						}
						{
							t.setHealth(t.getHealth() - gameStats.getUnitData().getDamage());
						}
						t.setShow(true);
						t.repaint();
						if (t.getHealth() <= 0)
						{
							TowerToField(t);
							repaint();
						}
					}
				}
				if (a.x == 14 && a.y == 4)
				{
					cleanUp.add(u);
<<<<<<< .mine
					gameStats.updateCastleHealth(1, ((u.getCaseNumber() + 1) * 2));
=======
					castleHealth -= 50;
>>>>>>> .r358
				}
			}
			for (int i = 0; i < temp.size(); i++)
			{
				t = (Tower) temp.get(i);
				t.count();
				if (t != null)
				{
					if (t.getCounter() >= gameStats.getTowerData().getTowerAttackSpeed(t.getCaseNumber()))
					{
						// range check
						a = t.getLocation();
						a.x += (t.getWidth() / 4) + 50;
						a.y += 130 - (t.getHeight() / 2);
						for (Unit u : spriteList.getUnitList())
						{
							b = u.getLocation();
							b.x += (u.getWidth() / 2);
							b.y += (u.getHeight() / 2);
							if (u.distance(a, b) <= gameStats.getTowerData().getRange(t.getCaseNumber()))
							{
								Projectile tempP = new Projectile(gameStats.getTowerData().getMissleDamage(t.getCaseNumber()), gameStats.getTowerData().getMissleImage(t.getCaseNumber()),
										(double) gameStats.getTowerData().getMissleSpeed(t.getCaseNumber()), gameStats.getTowerData().getMissleRange(t.getCaseNumber()), b, a, gameStats);
								getLayeredPane().add(tempP, JLayeredPane.POPUP_LAYER);
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
						a.x += (u.getWidth() / 2);
						a.y += (u.getHeight() / 2);
						b = pr.getEnd();
						// System.out.println(pr.getDamage());
						// check if unit is on the field of destruction!
						if (u.distance(a, b) <= pr.getRange())
						{
							// points += unitData.getReward(u.getCaseNumber()) *
							// 5;
							u.setHitPoints(u.getHitPoints() - pr.getDamage());
							// System.out.println(u.getHitPoints());
							if (u.getHitPoints() <= 0)
							{
								// points += unitData.getReward(u.getCaseNumber()) *
								// 5;
								gameStats.updatePoints(1, u.getReward() * 5);
								gameStats.updateGold(1, u.getReward());
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
				s = null;
			}
			gameStats.raiseWaveCounter();
			if (gameStats.getWaveCounter() >= 50)
			{
				Unit u = gameStats.getUnitData().getNewUnit(gameStats.getWave());
				u.setNewDestination(u.getLocation());
				u.setPath(path);
				getLayeredPane().add(u, JLayeredPane.POPUP_LAYER);
				spriteList.add(u);
				gameStats.setWaveCounter(0);
				if (gameStats.getWaveUnits() == 0)
				{
					gameStats.updateGold(1, (gameStats.getWave() * 3));
				}
				gameStats.raiseWaveUnits();
				if (gameStats.getWaveUnits() == 15)
				{
					gameStats.setWaveCounter(-100);
					gameStats.setWaveUnits(0);
					gameStats.raiseWave();
				}
			}
		}
		if (gameStats.getCastleHealth() <= 0)
		{
			endGame();
		}
		// checkPath(m.get(4, 14));
		// System.out.println(System.currentTimeMillis() - testTime);
	}

	public void checkPath(Field f)
	{
		Field firstField = m.get(4, 0);
		Point first = firstField.getLocation();

		Unit unit = new Unit(0.0, 0);
		unit.setLocation(first);

		Vector fields = findPath(unit, m.get(4, 14));
		Vector temp;
		if (fields != null)
		{
			path = fields;
		}
		boolean hasF;
		Field nearest;
		Point loc;
		Unit testUnit;
		for (Unit u : spriteList.getUnitList())
		{
			hasF = false;
			for (int i = u.getPathCounter(); i < u.getPath().size(); i++)
			{
				if (f.equals(u.getPath().get(i)))
				{
					hasF = true;
					break;
				}
			}
			if (hasF && fields != null)
			{
				nearest = path.get(0);
				loc = u.getLocation();
				loc.y -= 100;

				int j = 0;
				for (int i = 1; i < path.size(); i++)
				{
					if (Math.ceil(u.distance(nearest.getLocation(), loc) / 40) >= Math.ceil(u.distance(path.get(i).getLocation(), loc) / 40))
					{
						nearest = path.get(i);
						j = i;
					}
				}
				testUnit = new Unit(u.getSpeed(), u.getHitPoints());
				loc.x -= 50;
				testUnit.setLocation(loc);
				temp = findPath(testUnit, nearest);
				if (temp != null)
				{
					for (int i = j + 1; i < path.size(); i++)
					{
						temp.add(null);
						temp.add(path.get(i));
					}
					u.setPath(temp);
					u.setPathCounter(2);
				}
				else
				{
					u.setPathCounter(j + 1);
					u.setPath(path);
				}
			}
			/*
			 * fields = findPath(u, m.get(4, 14));
			 * u.setPath(fields);
			 * if (fields != null)
			 * {
			 * u.setPathCounter(1);
			 * }
			 */
		}
	}

	public void endGame()
	{
		panel = new EndPanel(gameStats);
		gameStats.setStarted(false);

		ArrayList<Sprite> cleanup = new ArrayList<Sprite>();
		for (Unit u : spriteList.getUnitList())
		{
			cleanup.add(u);
		}
		for (Projectile u : spriteList.getProjectileList())
		{
			cleanup.add(u);
		}
		Vector v = m.getTowers();
		for (int i = 0; i < v.size(); i++)
		{
			TowerToField(v.get(i));
		}
		for (Sprite s : cleanup)
		{
			s.setVisible(false);
			remove(s);
			spriteList.remove(s);
			s = null;
		}
		gameStats.reset();
		getLayeredPane().remove(leftSide);
		getLayeredPane().remove(statsPanel);
		getLayeredPane().add(panel, JLayeredPane.POPUP_LAYER);
		getLayeredPane().add(leftSide, JLayeredPane.POPUP_LAYER);
		getLayeredPane().add(statsPanel, JLayeredPane.POPUP_LAYER);
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
				hov.repaint();
			}

			if (hov instanceof Tower)
			{
				Tower t = (Tower) hov;
				t.setShow(true);
				hov.repaint();
			}
		}

		// OnMouseOut
		public void mouseExited(MouseEvent me)
		{
			Field hov = (Field) me.getComponent();
			hov.setHovered(false);

			if (hov instanceof Tower)
			{
				Tower t = (Tower) hov;
				t.setShow(false);
			}
			hov.repaint();
		}

		// OnClick
		public void mousePressed(MouseEvent me)
		{
			// In case a second Field gets selected, the previous Field has to
			// be repainted
			if (selected != null)
			{
				selected.setSelected(false);
				selected.repaint();
			}
			// Remove the selected Field from the Matrix
			final Field f = (Field) me.getSource();
			// Checks if a Field was removed, which also was part of the JFrame
			if (f != null && f.getParent() != null && !(f instanceof Tree))
			{
				selected = f;
				f.setSelected(true);
				f.repaint();
				if (controlPanel != null)
				{
					getLayeredPane().remove(controlPanel);
				}
				controlPanel = new ControlPanel(gameStats, f);
				getLayeredPane().add(controlPanel, JLayeredPane.POPUP_LAYER);
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
								getLayeredPane().remove(controlPanel);
								repaint();
							}
							else if (!(f instanceof Tree))
							{
								int type = controlPanel.getController().getType();
								FieldToTower(f, type);
								controlPanel.getController().setTakeAction(false);
								getLayeredPane().remove(controlPanel);
								repaint();
							}
							actionTimer.stop();
						}
					}
				});
				actionTimer.setCoalesce(true);
				actionTimer.start();
			}
		}

		public void mouseReleased(MouseEvent me)
		{
		// Do nothing
		}
	}

	class BackGround extends Component
	{

		public BackGround()
		{
			setSize(700, 700);
			setLocation(0, 0);
		}

		public void paint(Graphics g)
		{
			g.drawImage(background, 0, 0, 700, 700, null);
		}
	}

	class StatsBar extends JPanel
	{

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(statsBarImage, 0, 0, 600, 40, null);
		}
	}

	class LeftSide extends Component
	{

		public LeftSide()
		{
			setSize(40, 360);
			setLocation(50, 90);
		}

		public void paint(Graphics g)
		{
			g.drawImage(leftSideImage, 0, 0, 40, 360, null);
		}
	}
}