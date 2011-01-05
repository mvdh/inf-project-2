
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
public class Path
{

    private ArrayList<PathNode> path;
    private int counter;

    public Path()
    {
        path = new ArrayList<PathNode>();
        counter = 0;
    }

    public boolean hasNext()
    {
        return (counter < path.size());
    }

    public PathNode next()
    {
        return path.get(counter++);
    }

    public void add(PathNode a)
    {
        path.add(a);
    }

    public boolean contains(Point a)
    {
        boolean res = false;
        PathNode b;
        for (int i = 0; i < path.size(); i++)
        {
            b = path.get(i);
            if (b.getX() == a.x && b.getY() == a.y)
            {
                res = true;
                break;
            }
        }
        return res;
    }

    public boolean containsLower(PathNode a)
    {
        boolean res = false;
        PathNode b;
        for (int i = 0; i < path.size(); i++)
        {
            b = path.get(i);
            if (b.getX() == a.getX() && b.getY() == a.getY() && b.getCount() <= a.getCount())
            {
                res = true;
                break;
            }
        }
        return res;
    }

    public PathNode findNext(PathNode end)
    {
        PathNode res = null;
        PathNode b;
        for (int i = 0; i < path.size(); i++)
        {
            b = path.get(i);
            if (b.adjacent(end))
            {
                res = b;
                break;
            }
        }
        return res;
    }
}
