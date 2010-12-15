import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Tower extends Field
{

    private int caseNumber;

    public Tower()
    {
        // Just to set the size of the object
        super();
    }

    public Tower(int tower)
    {
        super();
        setCaseNumber(tower);
    }


    /**
     * 
     * @return
     */
    public int getCaseNumber()
    {
        return caseNumber;
    }

    /**
     *
     * @param caseNumber
     */
    public void setCaseNumber(int caseNumber)
    {
        this.caseNumber = caseNumber;
    }

    /**
     * 
     * @param g
     */
    public void paint(Graphics g)
    {
        // Execute the paint function as if it was a Field object
        super.paint(g);

        Dimension size = getSize();
        // Add a filled rectangle to the graphics
        g.setColor(new Color(0, 255, 0));
        g.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
    }
}
