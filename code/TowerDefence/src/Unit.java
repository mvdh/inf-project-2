import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * @author Slayer
 */
public class Unit extends Sprite
{

    private int hitPoints;
    private boolean aviation;
    private Vector path;
    private int pathCounter;
    private double speed;
    private int caseNumber;

    private BufferedImage[] bf = null;
    private int animationSpeed = 10;
    private int number = 0;

    public Unit(double speed)
    {
        super(speed);
        aviation = false;
        pathCounter = 0;
        caseNumber = 0;

        /**
         * 
         */

        bf = new BufferedImage[2];
        
        URL url = getClass().getResource("images/mario1.png");
        try
        {
            bf[0] = ImageIO.read(url);
        }
        catch (Exception e)
        {}

        url = getClass().getResource("images/mario2.png");
        try
        {
            bf[1] = ImageIO.read(url);
        }
        catch (Exception e)
        {}
    }

    public int getCaseNumber()
    {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber)
    {
        this.caseNumber = caseNumber;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public void setAnimationSpeed(int speed)
    {
        animationSpeed = speed;
    }

    public int getAnimationSpeed()
    {
        return animationSpeed;
    }

    public Vector getPath()
    {
        return path;
    }

    public void setPath(Vector path)
    {
        if (path != null)
        {
            this.path = path;
        }
    }

    @Override
    public void endMove()
    {
        if (hasNextPath())
        {
            Point newEnd = getNextPath().getLocation();
            // newEnd.x += 20;
            newEnd.x += (this.getWidth() / 2);
            newEnd.y += 20; // * Math.random() ???
            newEnd.y += (this.getHeight() / 2);
            // System.out.println(newEnd);
            setNewDestination(newEnd);
        }
    }
    
    public void step()
    {
        super.step();

        number = (number + 1) % animationSpeed;
    }

    public boolean pathContains(Field field)
    {
        boolean res = false;
        for (int i = this.getPathCounter(); i < path.size(); i++)
        {
            if (path.get(i).equals(field))
            {
                res = true;
                break;
            }
        }
        return res;
    }

    public Field getNextPath()
    {
        return path.get(pathCounter++);
    }

    public boolean hasNextPath()
    {
        return (pathCounter < path.size());
    }

    public int getPathCounter()
    {
        return pathCounter;
    }

    public int findIndexOfNearestNextPath(){
        int res = -1;
        double distance = 10000;
        for(int i = pathCounter; i < path.size(); i++){
            if(distance(this.getLocation(), path.get(i).getLocation()) < distance){
                distance = distance(this.getLocation(), path.get(i).getLocation());
                res = i - 1;
            }
        }
        return res;
    }

    public void setPathCounter(int pathCounter)
    {
        this.pathCounter = pathCounter;
    }

    public boolean getAviation()
    {
        return aviation;
    }

    public void setAviation(boolean aviation)
    {
        this.aviation = aviation;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public void paint(Graphics g)
    {
        if (number < animationSpeed / 2)
        {
            setImage(bf[0]);
        }
        else
        {
            setImage(bf[1]);
        }

        super.paint(g);
    }
}
