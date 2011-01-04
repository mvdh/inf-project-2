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

    public Tower()
    {
        // Just to set the size of the object
        super();
        this.setCaseNumber(0);
        this.setCounter(0);
    }

    public Tower(int tower)
    {
        super();
        this.setCaseNumber(tower);
        this.setCounter(0);
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
        BufferedImage bf = null;
        URL url = null;

        // If a CaseNumber is selected
        if (number == 1)
        {
            url = getClass().getResource("images/towerA1.png");
        }
        else if (number == 2)
        {
            url = getClass().getResource("images/towerA2.png");
        }
        else if (number == 3)
        {
            url = getClass().getResource("images/towerA3.png");
        }
        else if (number == 4)
        {
            url = getClass().getResource("images/towerA4.png");
        }

        if (url == null)
        {
            // Add a filled rectangle to the graphics
            g.setColor(new Color(0, 255, 0));
            g.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
        }
        else
        {
            // Add the selected image to the graphics
            try
            {
                bf = ImageIO.read(url);
                g.drawImage(bf, 1, 1, size.width, size.height, 0, 0, bf.getWidth(null), bf.getHeight(null), null);
            }
            catch (Exception e)
            {}
        }
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
