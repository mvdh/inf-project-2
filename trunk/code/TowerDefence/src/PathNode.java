/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class PathNode {
    private int x;
    private int y;
    private int count;

    public PathNode(int x, int y, int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public boolean adjacent(PathNode a){
        return ((x == a.getX() - 1 || x == a.getX() ||x == a.getX() + 1) && (y == a.getY() - 1 || y == a.getY() ||y == a.getY() + 1) && count == (a.getCount() - 1));
    }

    public String toString(){
        return ("(" + x + ", " + y + ", " + count + ")");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
