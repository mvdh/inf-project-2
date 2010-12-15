

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author averaart
 */
public class SpriteList {

    ArrayList<Sprite> tweenList = new ArrayList<Sprite>();
    int marge = 2;

    public void stepAll() {
        for (Sprite t : tweenList) {
            t.step();
        }
    }

    public void checkCollision() {
        Collections.sort(tweenList, new CompareByX()); // zet alle Tweens op volgorde van positie x
        // using traditional for loop
        for (int i = 0; i < tweenList.size(); i++) {
            tweenList.get(i).o.getPosition().getX();
        }
    }
}
