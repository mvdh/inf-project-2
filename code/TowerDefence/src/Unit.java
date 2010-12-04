/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Slayer
 */
public class Unit {

    private boolean aviation;
    private Field field;
    private Field[] path;
    private int pathCounter;
    private double speed;

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
        this.path = path;
    }

    public boolean pathContains(Field field) {
        boolean res = false;
        for (int i = this.getPathCounter(); i < path.length; i++) {
                if (path[i].equals(field))
                {
                        res = true;
                        break;
                }
        }
        return res;
    }

    public Field getNextPath(){
        pathCounter++;
        return path[getPathCounter()];
    }

    public boolean hasNextPath(){
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
