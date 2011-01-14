
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class Tower extends Field
{

    private int counter;
    private int caseNumber;
    private int health;
    private int maxHP;

    public int getHealth()
    {
        return health;
    }

    public void setMaxHP(int health)
    {
        this.maxHP = health;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public void setHealth(int health)
    {
        if (health <= this.maxHP)
        {
            this.health = health;
        } else
        {
            this.health = maxHP;
        }
    }

    public Tower(BufferedImage bg)
    {
        // Just to set the size of the object
        super(bg);
        this.setCaseNumber(0);
        this.setCounter(0);
    }

    public Tower(BufferedImage bg, int tower, int health)
    {
        super(bg);
        this.setCaseNumber(tower);
        this.setCounter(0);
        setHealth(health);
        setMaxHP(health);
    }

    /**
     * @return
     */
    public int getCaseNumber()
    {
        return caseNumber;
    }

    /**
     * @param caseNumber
     */
    public void setCaseNumber(int caseNumber)
    {
        this.caseNumber = caseNumber;
    }

    /**
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        // Execute the paint function as if it was a Field object
        super.paint(g);

        Dimension size = getSize();

        int number = getCaseNumber();
        BufferedImage img = null;
        URL url = null;

        TowerData td = new TowerData();
        // If a CaseNumber is selected
        url = getClass().getResource("images/tower" + td.getTowerImageName(number) + ".png");

        if (url == null)
        {
            // Add a filled rectangle to the graphics
            g.setColor(new Color(0, 255, 0));
            g.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
        } else
        {
            // Add the selected image to the graphics
            try
            {
                img = ImageIO.read(url);
                g.drawImage(img, 1, 1, 40, 40, 0, 0, img.getWidth(null), img.getHeight(null), null);
            } catch (Exception e)
            {
            }
        }

        double percentage = ((double) getHealth()) / ((double) maxHP);
        double widthGreen = (getWidth() - 2) * percentage;

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), 7);
        g.setColor(Color.red);
        g.fillRect(1, 1, getWidth() - 2, 5);
        g.setColor(Color.green);
        g.fillRect(1, 1, (int) widthGreen, 5);
    }

    public int getCounter()
    {
        return counter;
    }

    public void count()
    {
        this.counter++;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public String toString()
    {
        String result = "<Tower:\t" + getLocation() + ">";

        return result;
    }
}
