/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maarten van den Hoek
 */
public class GameStats {

    private TowerData towerData;
    private int points;
    private int gold;

    public GameStats(){
        points = 0;
        gold = 20;
        this.setTowerData(new TowerData());
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setTowerData(TowerData td){
        this.towerData = td;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void updatePoints(int dir, int points){
        if(dir == -1){
            this.points -= points;
        } else {
            this.points += points;
        }
    }

    public TowerData getTowerData(){
        return this.towerData;
    }

    public int getPoints(){
        return this.points;
    }
}
