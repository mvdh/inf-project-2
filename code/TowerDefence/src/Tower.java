
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Tower extends Field {

    private int counter;
    private int caseNumber;

    public Tower()
    {
        // Just to set the size of the object
        super();
        this.setCaseNumber(0);
        this.setCounter(0);
    }

    public Tower(int tower) {
        super();
        this.setCaseNumber(tower);
        this.setCounter(0);
    }

    /**
     * 
     * @return
     */
    public int getCaseNumber() {
        return caseNumber;
    }

    /**
     *
     * @param caseNumber
     */
    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    /**
     * 
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // Execute the paint function as if it was a Field object
        super.paint(g);

        Dimension size = getSize();
        // Add a filled rectangle to the graphics
        g.setColor(new Color(0, 255, 0));
        g.fillRect(size.width / 4, size.height / 4, size.width / 2, size.height / 2);
    }

    public int getCounter()
    {
        return counter;
    }

    public void count()
    {
        this.counter++;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }
}
