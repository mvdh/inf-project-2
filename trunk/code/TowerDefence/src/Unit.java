
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Slayer
 */
public class Unit extends Sprite {

    private int hitPoints;
    private boolean aviation;
    private Field[] path;
    private int pathCounter;
    private double speed;
    private int caseNumber;

    private BufferedImage bf1 = null;
    private BufferedImage bf2 = null;
    private int number = 0;

    public Unit(double speed) {
        super(speed);
        aviation = false;
        pathCounter = 0;
        caseNumber = 0;
        
        URL url = getClass().getResource("images/mario1.png");
        try {
            bf1 = ImageIO.read(url);
        } catch (Exception e) {}
        
        url = getClass().getResource("images/mario2.png");
        try {
            bf2 = ImageIO.read(url);
        } catch (Exception e) {}
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Field[] getPath() {
        return path;
    }

    public void setPath(Field[] path) {
        if (path != null) {
            this.path = path;
        }
    }

    @Override
    public void endMove() {
        if (hasNextPath()) {
            Point newEnd = getNextPath().getLocation();
            //System.out.println(newEnd);
            //newEnd.x += 20;
            newEnd.x += (this.getWidth() / 2);
            newEnd.y += 20;
            newEnd.y += (this.getHeight() / 2);
            //System.out.println(newEnd);
            setNewDestination(newEnd);
        }
    }

    public boolean pathContains(Field field) {
        boolean res = false;
        for (int i = this.getPathCounter(); i < path.length; i++) {
            if (path[i].equals(field)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public Field getNextPath() {
        return path[pathCounter++];
    }

    public boolean hasNextPath() {
        return (pathCounter < path.length);
    }

    public int getPathCounter() {
        return pathCounter;
    }

    public void setPathCounter(int pathCounter) {
        this.pathCounter = pathCounter;
    }

    public boolean getAviation() {
        return aviation;
    }

    public void setAviation(boolean aviation) {
        this.aviation = aviation;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    public void paint(Graphics g)
    {        
        if (number < 5)
        {
            setImage(bf1);
        }
        else
        {
            setImage(bf2);
        }
        
        number = (number + 1) % 10;
        
        super.paint(g);
    }
}
