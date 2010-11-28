
import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Path {
    private ArrayList<PathNode> path;
    private int counter;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Path(){
        path = new ArrayList<PathNode>();
        counter = 0;
    }

    public boolean hasNext(){
        return (counter < path.size());
    }

    public PathNode next(){
        PathNode res = path.get(counter);
        counter++;
        return res;
    }

    public void add(PathNode a){
        path.add(a);
    }

    /*public void remove(Cord a){
        PathNode b;
        for(int i = 0; i < path.size(); i++){
            b = path.get(i);
            if(b.getX() == a.getX() && b.getY() == a.getY()){
                path.remove(b);
                break;
            }
        }
        if(counter > 0)
            counter--;
    }*/

    public boolean contains(Point a){
        boolean res = false;
        PathNode b;
        for(int i = 0; i < path.size(); i++){
            b = path.get(i);
            if(b.getX() == a.x && b.getY() == a.y)
                res = true;
        }
        return res;
    }

    public boolean containsLower(PathNode a){
        boolean res = false;
        PathNode b;
        for(int i = 0; i < path.size(); i++){
            b = path.get(i);
            if(b.getX() == a.getX() && b.getY() == a.getY() && b.getCount() <= a.getCount())
                res = true;
        }
        return res;
    }

    /*public String toString(){
        String res = "";
        String a = "\n";
        for(PathNode b : path){
            res += b.toString() + a;
        }
        return res;
    }*/

    public PathNode findNext(PathNode end){
        PathNode res = null;
        PathNode b;
        for(int i = 0; i < path.size(); i++){
            b = path.get(i);
            if(b.adjacent(end)){
                res = b;
                break;
            }
        }
        return res;
    }
}