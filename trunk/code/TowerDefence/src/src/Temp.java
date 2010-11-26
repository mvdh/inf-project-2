/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 0839339
 */
public class Temp {
    /* aStar in Game
     * NOG TOEVOEGEN
     * Unit
        public boolean getAviation();
        health, attack, speed, path? etc...
     * Matrix
        public Cord getCord(Field field);
     * Field
        public void setFlyable();
        public boolean flyable();
        public void setWalkable();
        public boolean walkable();
        public void setBuildable();
        public boolean buildable();
     * Missle
        attack, speed
        kan ook met final ints die naar plaatjes verwijzen, bijv:
        public Missle(attack, missleSpeed, Missle.ARROW)
     * Tower
        health, attack, missleSpeed,attack speed, cummulative price, price?
            public Missle fire();
        return (new Missle(attack, missleSpeed, Missle.ARROW));
     * Game
        Andra's attack-path algoritme? (uitrekenen locatie targeted unit en dan dat field als target doorsturen naar aStar
     */

    private Field[] aStar(Unit puppet, Field target) {
        Path path = new Path();
        Point start = m.getPoint(puppet);
        Point end = m.getPoint(target);
        PathNode temp = new PathNode(start.x(), start.y(), 0);
        path.add(temp);
        PathNode[] tempL = new PathNode[4];
        int j = 0;
        while (!path.contains(end)) {
            temp = path.next();
            tempL[0] = new PathNode(temp.getX() + 1, temp.getY(), temp.getCount() + 1);
            tempL[1] = new PathNode(temp.getX(), temp.getY() + 1, temp.getCount() + 1);
            tempL[2] = new PathNode(temp.getX() - 1, temp.getY(), temp.getCount() + 1);
            tempL[3] = new PathNode(temp.getX(), temp.getY() - 1, temp.getCount() + 1);
            for (int k = 0; k < tempL.length; k++) {
                if (puppet.getAviation()) {
                    if (!m.get(tempL[k].getX(), tempL[k].getY()).flyable()) {
                        tempL[k] = null;
                    } else if (!m.get(tempL[k].getX(), tempL[k].getY()).walkable()) {
                        tempL[k] = null;
                    }
                }
                if (tempL[k] != null && !path.containsLower(tempL[k])) {
                    path.add(tempL[k]);
                }
            }
            j++;
            if (j > 150) {
                return null;
            }
        }
        PathNode[] res = new PathNode[temp.getCount() + 2];
        res[res.length - 1] = new PathNode(end.x(), end.y(), temp.getCount() + 1);
        for (int i = res.length - 1; i > 0; i--) {
            res[i - 1] = path.findNext(res[i]);
        }
        Field[] fRes = new Field[res.length];
        for (int i = 0; i < res.length; i++) {
            fRes[i] = m.get(res[i].getX(), res[i].getY());
        }
        return fRes;
    }
}
