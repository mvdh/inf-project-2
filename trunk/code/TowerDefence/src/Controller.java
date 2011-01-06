import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
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
    private TowerData towerData;
    
    protected Point[] locations = { new Point(20, 20), new Point(85, 20), new Point(20, 85), new Point(85, 85) };
    
    private JPanel description = new JPanel(new GridLayout(5, 1));
    private JLabel priceLbl = new JLabel("  Price: ");
    private JLabel hitPointsLbl = new JLabel("  Hitpoints: ");
    private JLabel rangeLbl = new JLabel("  Range: ");
    private JLabel speedLbl = new JLabel("  Attack speed: ");
    private JLabel damageLbl = new JLabel("  Damage: ");

    public void init()
    {
        setLayout(null);
        setSize(680, 180);
        
        description.setLocation(165, 20);
        description.setSize(495, 125);
        
        description.add(priceLbl);
        description.add(hitPointsLbl);
        description.add(rangeLbl);
        description.add(speedLbl);
        description.add(damageLbl);
        
        add(description);
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
    public TowerData getTowerData()
    {
        return towerData;
    }

    /**
     * @param towerData
     */
    public void setTowerData(TowerData towerDataIn)
    {
        towerData = towerDataIn;
    }

    class CLabel extends JLabel
    {
        private BufferedImage bf = null;
        private int type = -1;

        /**
         * @param bfi
         */
        public CLabel(BufferedImage bfi)
        {
            setSize(60, 60);
            bf = bfi;
        }

        /**
         * @param bfi
         * @param type
         */
        public CLabel(BufferedImage bfi, int type)
        {
            setSize(60, 60);
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
            g.setColor(new Color(200, 200, 200));
            g.fillRect(0, 0, 60, 60);
            if (bf != null)
            {
                g.drawImage(bf, 0, 0, 60, 60, 0, 0, bf.getWidth(null), bf.getHeight(null), null);
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
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(Color.white);
            g.fillRect(0, 0, 60, 60);

            if (((CLabel) me.getComponent()).getType() != -1)
            {
                priceLbl.setText("  Price: " + towerData.getCosts(((CLabel) me.getComponent()).getType()));
                hitPointsLbl.setText("  Hitpoints: " + towerData.getHitpoints(((CLabel) me.getComponent()).getType()));
                rangeLbl.setText("  Range: " + towerData.getRange(((CLabel) me.getComponent()).getType()));
                speedLbl.setText("  Attack speed: " + towerData.getAttackSpeed(((CLabel) me.getComponent()).getType()));
                damageLbl.setText("  Damage: " + towerData.getMissleDamage(((CLabel) me.getComponent()).getType()));
            }
        }

        public void mouseExited(MouseEvent me)
        {
            priceLbl.setText("");
            hitPointsLbl.setText("");
            rangeLbl.setText("");
            speedLbl.setText("");
            damageLbl.setText("");
            
            paint(getGraphics());
        }

        public void mousePressed(MouseEvent me)
        {
            setTakeAction(true);
            setType(((CLabel) me.getComponent()).getType());
            
            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(Color.blue);
            g.fillRect(0, 0, 60, 60);
        }

        public void mouseReleased(MouseEvent me)
        {
            paint(getGraphics());

            Graphics2D g = (Graphics2D) me.getComponent().getGraphics();
            float alpha = .5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(Color.white);
            g.fillRect(0, 0, 60, 60);
        }
    }
}
