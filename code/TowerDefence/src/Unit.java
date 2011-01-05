/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Slayer
 */
public class Unit extends Sprite
{

    private int hitPoints;
    private boolean aviation;
    private Field[] path;
    private int pathCounter;
    private double speed;

    public Unit(){
        super();
        aviation = false;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public Field[] getPath()
    {
        return path;
    }

    public void setPath(Field[] path)
    {
        if (path != null)
        {
            this.path = path;
        }
    }

    public boolean pathContains(Field field)
    {
        boolean res = false;
        for (int i = this.getPathCounter(); i < path.length; i++)
        {
            if (path[i].equals(field))
            {
                res = true;
                break;
            }
        }
        return res;
    }

    public Field getNextPath()
    {
        return path[pathCounter++];
    }

    public boolean hasNextPath()
    {
        return (pathCounter < path.length);
    }

    public int getPathCounter()
    {
        return pathCounter;
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
}
