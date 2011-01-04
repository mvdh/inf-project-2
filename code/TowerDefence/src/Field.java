
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class Field extends Component
{

    private boolean walkable;
    private boolean flyable;
    private boolean buildable;

    public Field()
    {
        setSize(40, 40);
    }

    /**
     *
     * Is it possible to build at this Field?
     * @return buildable
     */
    public boolean isBuildable()
    {
        return buildable;
    }

    /**
     *
     * @param buildable
     */
    public void setBuildable(boolean buildable)
    {
        this.buildable = buildable;
    }

    /**
     *
     * Is it possible to fly over this Field?
     * @return flyable
     */
    public boolean isFlyable()
    {
        return flyable;
    }

    /**
     *
     * @param flyable
     */
    public void setFlyable(boolean flyable)
    {
        this.flyable = flyable;
    }

    /**
     *
     * Is it possible to walk over this Field?
     * @return walkable
     */
    public boolean isWalkable()
    {
        return walkable;
    }

    /**
     *
     * @param walkable
     */
    public void setWalkable(boolean walkable)
    {
        this.walkable = walkable;
    }

    public void paint(Graphics g)
    {
        // Get the size of the Field object
        Dimension size = getSize();
        // Set the Color to draw in
        g.setColor(new Color(200, 100, 0));
        // Draw a rectangle and fill it with the set Color
        g.fillRect(0, 0, size.width, size.height);
        g.setColor(new Color(0, 0, 0));
        // Draw a rectangle, but don't fill it with a Color (border only)
        g.drawRect(0, 0, size.width, size.height);
    }
    
    public void paintPath()
    {
        Graphics g = getGraphics();
        Dimension size = getSize();
        
        g.setColor(new Color(255, 255, 0));
        g.fillOval(0, 0, size.width, size.height);
    }

    /**
     *
     * @param f Field
     * @return this.equals(f)
     */
    public boolean equals(Field f)
    {
        return f != null && f.getLocation().equals(getLocation());
    }
    
    public String toString()
    {
        String result = "<Field:\t" + getLocation() + ">";
        
        return result;
    }
}
