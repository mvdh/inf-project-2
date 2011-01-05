import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class Field extends Component
{

    private boolean walkable;
    private boolean flyable;
    private boolean buildable;
    private BufferedImage bf = null;

    public Field(BufferedImage bg)
    {
        setSize(40, 40);

        randomChange(bg);

        walkable = true;
        flyable = true;
        buildable = true;
    }
    
    public void randomChange(BufferedImage bg)
    {
        Dimension size = getSize();
        bf = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bf.createGraphics();

        g.drawImage(bg, 0, 0, bg.getWidth(), bg.getHeight(), this);
        
        double random = Math.random();
        if (random < 0.5)
        {
            upSideDown((Graphics2D) g, size);
        }

        double random2 = Math.random();
        if (random2 < 0.5)
        {
            mirrored((Graphics2D) g, size);
        }
        
        paint(g);
    }

    /**
     * Is it possible to build at this Field?
     * 
     * @return buildable
     */
    public boolean isBuildable()
    {
        return buildable;
    }

    /**
     * @param buildable
     */
    public void setBuildable(boolean buildable)
    {
        this.buildable = buildable;
    }

    /**
     * Is it possible to fly over this Field?
     * 
     * @return flyable
     */
    public boolean isFlyable()
    {
        return flyable;
    }

    /**
     * @param flyable
     */
    public void setFlyable(boolean flyable)
    {
        this.flyable = flyable;
    }

    /**
     * Is it possible to walk over this Field?
     * 
     * @return walkable
     */
    public boolean isWalkable()
    {
        return walkable;
    }

    /**
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

        g.drawImage(bf, 0, 0, size.width, size.height, 0, 0, bf.getWidth(null), bf.getHeight(null), null);
    }

    public void paintPath()
    {
        Graphics g = getGraphics();
        Dimension size = getSize();

        g.setColor(new Color(255, 255, 0));
        g.fillOval(0, 0, size.width, size.height);
    }

    /**
     * Turns the graphics up size down
     * 
     * @param g Graphics2D
     * @param size Dimension
     */
    public void upSideDown(Graphics2D g, Dimension size)
    {
        // It works the same as to convert matrices (not our Matrix objects)
        g.scale(1, -1);
        g.translate(0, -size.height);
    }

    /**
     * @param g Graphics2D
     * @param size Dimension
     */
    public void mirrored(Graphics2D g, Dimension size)
    {
        g.scale(-1, 1);
        g.translate(-size.width, 0);
    }

    /**
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
