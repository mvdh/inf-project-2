import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 * @author Maarten van den Hoek
 */
public class Controller extends Container
{
	private boolean takeAction = false;
	private int type = -1;
	private GameStats gameStats;

	private JPanel description = new JPanel(new GridLayout(3, 2));
	private JLabel priceLbl = new JLabel();
	private JLabel hitPointsLbl = new JLabel();
	private JLabel rangeLbl = new JLabel();
	private JLabel speedLbl = new JLabel();
	private JLabel damageLbl = new JLabel();
        private Font statsFont = new Font("Arial", Font.BOLD, 16);

	public void init()
	{
		setLayout(null);
		setSize(600, 165);

                priceLbl.setFont(statsFont);
                priceLbl.setForeground(Color.white);
                priceLbl.setLocation(205, 30);
                hitPointsLbl.setFont(statsFont);
                hitPointsLbl.setForeground(Color.white);
                hitPointsLbl.setLocation(205, 30);
                rangeLbl.setFont(statsFont);
                rangeLbl.setForeground(Color.white);
                speedLbl.setFont(statsFont);
                speedLbl.setForeground(Color.white);
                damageLbl.setFont(statsFont);
                damageLbl.setForeground(Color.white);

		//this.setLocation(205, 30);
		//this.setSize(290, 100);
		this.add(damageLbl);
                this.add(hitPointsLbl);
                this.add(rangeLbl);
		this.add(priceLbl);
                this.add(speedLbl);

		//add(description);
		paintAll(getGraphics());
	}

	/**
	 * @param in
	 */
	public void setTakeAction(boolean in)
	{
		takeAction = in;
	}

	/**
	 * @return
	 */
	public boolean getTakeAction()
	{
		return takeAction;
	}

	/**
	 * @param typeIn
	 */
	public void setType(int typeIn)
	{
		type = typeIn;
	}

	/**
	 * @return
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * @return
	 */
	public GameStats getGameStats()
	{
		return this.gameStats;
	}

	/**
	 * @param gameStats
	 */
	public void setGameStats(GameStats gameStats)
	{
		this.gameStats = gameStats;
	}

	class CLabel extends JPanel
	{
		private BufferedImage bf = null;
		private int type = -1;

		/**
		 * @param bfi
		 */
		public CLabel(BufferedImage bfi)
		{
			setSize(50, 50);
			bf = bfi;
		}

		/**
		 * @param bfi
		 * @param type
		 */
		public CLabel(BufferedImage bfi, int type)
		{
			setSize(50, 50);
			bf = bfi;
			setType(type);
		}

		/**
		 * @param typeIn
		 */
		public void setType(int typeIn)
		{
			type = typeIn;
		}

		/**
		 * @return
		 */
		public int getType()
		{
			return type;
		}




		public void paint(Graphics g)
		{
                    	Graphics2D g2d = (Graphics2D) g;
                        
                        float alpha = .4f;
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                        g2d.setColor(new Color(67, 52, 34));
                        g2d.fillRect(0, 0, 50, 50);


                        alpha = 1f;
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

			if (bf != null)
			{
				g2d.drawImage(bf, 5, 5, 40, 40, null);
			}
		}
	}

	class CMouseAdapter implements MouseListener
	{

		public void mouseClicked(MouseEvent me)
		{
		// Do nothing
		}

		public void mouseEntered(MouseEvent me)
		{
			Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
			float alpha = .4f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			int currentType = ((CLabel) me.getComponent()).getType();

			g.setColor(Color.white);
			if (currentType != -1)
			{
				int towerCosts = getGameStats().getTowerData().getCosts(((CLabel) me.getComponent()).getType());
				if (gameStats.getGold() < towerCosts)
				{
					g.setColor(Color.red);
				}
			}

			g.fillRect(0, 0, 50, 50);

			if (((CLabel) me.getComponent()).getType() != -1)
			{
				priceLbl.setText("Price: " + getGameStats().getTowerData().getCosts(((CLabel) me.getComponent()).getType()));
				hitPointsLbl.setText("Hitpoints: " + getGameStats().getTowerData().getHitpoints(((CLabel) me.getComponent()).getType()));
				rangeLbl.setText("Range: " + getGameStats().getTowerData().getRange(((CLabel) me.getComponent()).getType()));
				speedLbl.setText("Speed: " + getGameStats().getTowerData().getTowerAttackSpeed(((CLabel) me.getComponent()).getType()));
				damageLbl.setText("Damage: " + getGameStats().getTowerData().getMissleDamage(((CLabel) me.getComponent()).getType()));
			}
		}

		public void mouseExited(MouseEvent me)
		{
			priceLbl.setText("");
			hitPointsLbl.setText("");
			rangeLbl.setText("");
			speedLbl.setText("");
			damageLbl.setText("");

                        repaint();
			//paint(getGraphics());
		}

		public void mousePressed(MouseEvent me)
		{
			int currentType = ((CLabel) me.getComponent()).getType();
			if (currentType != -1)
			{
				int towerCosts = getGameStats().getTowerData().getCosts(((CLabel) me.getComponent()).getType());
				if (gameStats.getGold() >= towerCosts)
				{
					setTakeAction(true);
				}
			}
			else
			{
				// ?
				setTakeAction(true);
			}
			setType(((CLabel) me.getComponent()).getType());

			Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
			float alpha = .5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g.setColor(Color.blue);
			g.fillRect(0, 0, 50, 50);
		}

		public void mouseReleased(MouseEvent me)
		{
			paint(getGraphics());

			Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
			float alpha = .5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g.setColor(Color.red);
			g.fillRect(0, 0, 50, 50);
		}
	}
}
