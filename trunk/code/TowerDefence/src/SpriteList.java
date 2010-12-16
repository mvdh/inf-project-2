
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Slayer
 */
public class SpriteList {
    private ArrayList<Unit> unitList;
    private ArrayList<Missle> missleList;

    public SpriteList(){
        unitList = new ArrayList<Unit>();
        missleList = new ArrayList<Missle>();
    }

    public void step(){
        for(Unit a : unitList){
            a.step();
        }
        for(Missle a : missleList){
            a.step();
        }
    }

    public ArrayList<Unit> getUnitList(){
        return unitList;
    }

    public ArrayList<Missle> getMissleList(){
        return missleList;
    }

    public void add(Sprite a){
        if(a instanceof Unit){
            unitList.add((Unit) a);
        }
        else if(a instanceof Missle){
            missleList.add((Missle) a);
        }
    }

    public Sprite remove(Sprite a){
        Sprite res = null;
        if(a instanceof Unit){
            res = (Unit) a;
            for(int i = 0; i < unitList.size(); i++){
                if(res == unitList.get(i)){
                    unitList.remove(i);
                    break;
                }
            }
        }
        if(a instanceof Missle){
            res = (Missle) a;
            for(int i = 0; i < missleList.size(); i++){
                if(res == missleList.get(i)){
                    missleList.remove(i);
                    break;
                }
            }
        }
        return res;
    }
}
