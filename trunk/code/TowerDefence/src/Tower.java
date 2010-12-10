
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Tower extends Field
{

    private int range;
    private int hitPoints;
    private int caseNumber;
    private int cost;
    private int previousCaseNumber;
    //Missle stats
    private int missleSpeed;
    private int missleDamage;
    private int missleImage;

    public int getMissleDamage()
    {
        return missleDamage;
    }

    public void setMissleDamage(int missleDamage)
    {
        this.missleDamage = missleDamage;
    }

    public int getMissleImage()
    {
        return missleImage;
    }

    public void setMissleImage(int missleImage)
    {
        this.missleImage = missleImage;
    }

    public int getMissleSpeed()
    {
        return missleSpeed;
    }

    public void setMissleSpeed(int missleSpeed)
    {
        this.missleSpeed = missleSpeed;
    }

    public Tower()
    {
        // Just to set the size of the object
        super();
    }

    public Tower(int tower)
    {
        super();
        setCaseNumber(tower);
        switch (tower)
        {
            case 1:
            {
                //setImage(1);
                setHitPoints(250);
                setMissleDamage(10);
                setMissleImage(0);
                setMissleSpeed(20);
                setRange(150);
                setCost(10);
                setPreviousCaseNumber(0);
            }
            case 11:
            {
                //setImage(1);
                setHitPoints(300);
                setMissleDamage(20);
                setMissleImage(0);
                setMissleSpeed(20);
                setRange(175);
                setCost(20);
                setPreviousCaseNumber(1);
            }
        }
    }

    /**
     *
     * @param rangeIn int
     */
    public void setRange(int rangeIn)
    {
        range = rangeIn;
    }

    /**
     *
     * @param hp int
     */
    public void setHitPoints(int hp)
    {
        hitPoints = hp;
    }

    /**
     *
     * @return range
     */
    public int getRange()
    {
        return range;
    }

    /**
     *
     * @return hitpoints
     */
    public int getHitPoints()
    {
        return hitPoints;
    }

    public void paint(Graphics g)
    {
        // Execute the paint function as if it was a Field object
        super.paint(g);

        Dimension size = getSize();
        // Add a filled rectangle to the graphics
        g.setColor(new Color(0, 255, 0));
        g.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
    }

    public int getCaseNumber()
    {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber)
    {
        this.caseNumber = caseNumber;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int getPreviousCaseNumber()
    {
        return previousCaseNumber;
    }

    public void setPreviousCaseNumber(int previousCaseNumber)
    {
        this.previousCaseNumber = previousCaseNumber;
    }
}
