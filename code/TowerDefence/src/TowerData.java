
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Maarten van den Hoek
 */
public class TowerData {

    /*
     * 0    caseNumber
     * 1    costs;
     * 2    hitPoints
     * 3    range
     * 4    previousCaseNumber
     * 5    missleDamage
     * 6    missleImage
     * 7    missleSpeed
     * 8    towerImage
     * 9    missleRange
     * 10   towerRange
     * 11   towerAttackSpeed
     */
    final private int[][] towerData;
    final private int[] towerImages;
    private GameStats stats;
    //final ArrayList<Integer> sortedList;

    /**
     * Constructor. Here is all the information of the towers added to towerData
     */
    public TowerData(GameStats statsIn) {
    	stats = statsIn;
        towerData = new int[12][12];
        towerImages = new int[12];

        /*
         * Tower A1
         */
        towerData[0][0] = 0;        //caseNumber
        towerData[0][1] = -1;       //previousCaseNumber
        towerData[0][2] = 6;        //costs
        towerData[0][3] = 100;      //hitPoints
        towerData[0][4] = 0;        //missleImage
        towerData[0][5] = 60;       //towerRange
        towerData[0][6] = 20;       //towerAttackSpeed
        towerData[0][7] = 22;       //missleDamage
        towerData[0][8] = 10;       //missleSpeed
        towerData[0][9] = 25;       //missleRange

        /*
         * Tower B1
         */
        towerData[1][0] = 1;        //caseNumber
        towerData[1][1] = -1;       //previousCaseNumber
        towerData[1][2] = 16;        //costs
        towerData[1][3] = 125;      //hitPoints
        towerData[1][4] = 1;        //missleImage
        towerData[1][5] = 60;      //range
        towerData[1][6] = 30;        //towerAttackSpeed
        towerData[1][7] = 30;        //missleDamage
        towerData[1][8] = 20;       //missleSpeed
        towerData[1][9] = 25;       //missleRange

        /*
         * Tower C1
         */
        towerData[2][0] = 2;        //caseNumber
        towerData[2][1] = -1;       //previousCaseNumber
        towerData[2][2] = 30;       //costs
        towerData[2][3] = 150;      //hitPoints
        towerData[2][4] = 1;        //missleImage
        towerData[2][5] = 100;      //towerRange
        towerData[2][6] = 20;       //towerAttackSpeed
        towerData[2][7] = 70;        //missleDamage
        towerData[2][8] = 20;       //missleSpeed
        towerData[2][9] = 20;       //missleRange

        /*
         * Tower D1
         */
        towerData[3][0] = 3;        //caseNumber
        towerData[3][1] = -1;       //previousCaseNumber
        towerData[3][2] = 50;        //costs
        towerData[3][3] = 200;      //hitPoints
        towerData[3][4] = 0;        //missleImage
        towerData[3][5] = 80;      //towerRange
        towerData[3][6] = 20;       //towerAttackSpeed
        towerData[3][7] = 130;        //missleDamage
        towerData[3][8] = 20;       //missleSpeed
        towerData[3][9] = 30;       //missleRange

        /*
         * Tower A2
         */
        towerData[4][0] = 4;        //caseNumber
        towerData[4][1] = 0;       //previousCaseNumber
        towerData[4][2] = 20;        //costs
        towerData[4][3] = 200;      //hitPoints
        towerData[4][4] = 0;        //missleImage
        towerData[4][5] = 60;       //towerRange
        towerData[4][6] = 20;       //towerAttackSpeed
        towerData[4][7] = 36;       //missleDamage
        towerData[4][8] = 20;       //missleSpeed
        towerData[4][9] = 30;       //missleRange

        /*
         * Tower B2
         */
        towerData[5][0] = 5;        //caseNumber
        towerData[5][1] = 1;       //previousCaseNumber
        towerData[5][2] = 50;        //costs
        towerData[5][3] = 100;      //hitPoints
        towerData[5][4] = 1;        //missleImage
        towerData[5][5] = 100;      //range
        towerData[5][6] = 24;        //towerAttackSpeed
        towerData[5][7] = 50;        //missleDamage
        towerData[5][8] = 20;       //missleSpeed
        towerData[5][9] = 25;       //missleRange

        /*
         * Tower C2
         */
        towerData[6][0] = 6;        //caseNumber
        towerData[6][1] = 2;       //previousCaseNumber
        towerData[6][2] = 75;       //costs
        towerData[6][3] = 150;      //hitPoints
        towerData[6][4] = 1;        //missleImage
        towerData[6][5] = 60;      //towerRange
        towerData[6][6] = 16;       //towerAttackSpeed
        towerData[6][7] = 70;        //missleDamage
        towerData[6][8] = 10;       //missleSpeed
        towerData[6][9] = 20;       //missleRange

        /*
         * Tower D2
         */
        towerData[7][0] = 7;        //caseNumber
        towerData[7][1] = 175;      //costs
        towerData[7][2] = 100;      //hitPoints
        towerData[7][3] = 100;      //range
        towerData[7][4] = 3;        //previousCaseNumber
        towerData[7][5] = 2;        //missleDamage
        towerData[7][6] = 0;        //missleImage
        towerData[7][7] = 20;       //missleSpeed
        towerData[7][8] = 2;        //towerImage
        towerData[7][9] = 20;       //missleRange
        towerData[7][10] = 120;     //towerRange
        towerData[7][11] = 10;      //towerAttackSpeed

        /*
         * Tower A3
         */
        towerData[8][0] = 8;        //caseNumber
        towerData[8][1] = 4;       //previousCaseNumber
        towerData[8][2] = 40;        //costs
        towerData[8][3] = 150;      //hitPoints
        towerData[8][4] = 0;        //missleImage
        towerData[8][5] = 80;       //towerRange
        towerData[8][6] = 30;       //towerAttackSpeed
        towerData[8][7] = 40;       //missleDamage
        towerData[8][8] = 15;       //missleSpeed
        towerData[8][9] = 25;       //missleRange

        /*
         * Tower B3
         */
        towerData[9][0] = 9;        //caseNumber
        towerData[9][1] = 60;       //previousCaseNumber
        towerData[9][2] = 30;        //costs
        towerData[9][3] = 100;      //hitPoints
        towerData[9][4] = 1;        //missleImage
        towerData[9][5] = 60;      //range
        towerData[9][6] = 13;        //towerAttackSpeed
        towerData[9][7] = 40;        //missleDamage
        towerData[9][8] = 20;       //missleSpeed
        towerData[9][9] = 25;       //missleRange

        /*
         * Tower C3
         */
        towerData[10][0] = 10;        //caseNumber
        towerData[10][1] = 275;      //costs
        towerData[10][2] = 100;      //hitPoints
        towerData[10][3] = 100;      //range
        towerData[10][4] = 6;        //previousCaseNumber
        towerData[10][5] = 2;        //missleDamage
        towerData[10][6] = 1;        //missleImage
        towerData[10][7] = 20;       //missleSpeed
        towerData[10][8] = 2;        //towerImage
        towerData[10][9] = 20;       //missleRange
        towerData[10][10] = 120;     //towerRange
        towerData[10][11] = 10;      //towerAttackSpeed

        /*
         * Tower D3
         */
        towerData[11][0] = 11;        //caseNumber
        towerData[11][1] = 300;      //costs
        towerData[11][2] = 100;      //hitPoints
        towerData[11][3] = 100;      //range
        towerData[11][4] = 7;        //previousCaseNumber
        towerData[11][5] = 2;        //missleDamage
        towerData[11][6] = 1;        //missleImage
        towerData[11][7] = 20;       //missleSpeed
        towerData[11][8] = 3;        //towerImage
        towerData[11][9] = 20;       //missleRange
        towerData[11][10] = 120;     //towerRange
        towerData[11][11] = 10;      //towerAttackSpeed
    }

//    Volgende functie is nog niet af, het is een functie die we op dit moment
//    niet nodig hebben.
//    public ArrayList<Integer> sortTowersByPreviousCaseNumber() {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        int[][] tTD = this.getTowerData();
//        //lijst met de volgorde van previouscasenumber en dan op casenumber
//
//        list.add(this.getTowerData()[0][4]);
//
//        for (int i = 1; i < this.getTowerData().length; i++) {
//            for (int j = 0; j < list.size(); j++) { // voor elke arraylistitem
//                if (this.getPreviousCaseNumber(i) > list.get(j)) {
//                    continue;
//                } else if (this.getPreviousCaseNumber(i) < list.get(j)) {
//                    list.add(i, j);
//                    break;
//                } else {
//                    int k = j;
//                    while (list.get(k) == this.getTowerData()[i][4]) {
//                        if()
//                        k++;
//                    }
//                }
//
//            }
//        }
//
//        return new ArrayList<Integer>();
//    }
    /**
     * Return the towerData. towerData contains the information of all towers.
     *
     * @return the towerData.
     */
    public int[][] getTowerData() {
        return this.towerData;
    }

    public BufferedImage getTowerImage(int caseNumber) {
        return stats.towerImages[caseNumber];
    }

    /**
     * Finds the upgradables of the tower that was selected.
     *
     * @param caseNumber The case number of the current tower.
     * @return An ArrayList with the caseNumber of the upgradables. If there is
     * no upgradable, the ArrayList has one value: -1.
     */
    public ArrayList<Integer> getUpgradables(int caseNumber) {
        ArrayList<Integer> result = new ArrayList();

        /*
         * Determine if there is a tower with the caseNumber as
         * previousCaseNumber. If a tower has that previousCaseNumber, add it to
         * the list. The tower then is an upgradable of the current tower.
         */
        for (int i = 0; i < this.getTowerData().length; i++) {
            if (this.getPreviousCaseNumber(i) == caseNumber) {
                result.add(this.getTowerData()[i][0]);
            }
        }

        /*
         * If the current tower has no upgradables, add the value '-1' to the
         * list. If this value is passed to the list, it is clear that there is
         * no upgradable.
         */
        if (result.isEmpty()) {
            result.add(-1);
        }

        return result;
    }

    /**
     * Returns the hitpoints of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the costs of a tower
     */
    public int getCaseNumber(int caseNumber) {
        return towerData[caseNumber][0];
    }

    /**
     * Returns the previous caseNumber of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the previous casenumber of a tower
     */
    public int getPreviousCaseNumber(int caseNumber) {
        return towerData[caseNumber][1];
    }

    /**
     * Returns the costs of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the costs of a tower
     */
    public int getCosts(int caseNumber) {
        return towerData[caseNumber][2];
    }

    /**
     * Returns the hitpoints of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the hitpoints of a tower
     */
    public int getHitpoints(int caseNumber) {
        return towerData[caseNumber][3];
    }

    /**
     * Returns the image number of the towers missle where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the image number of a missle
     */
    public int getMissleImage(int caseNumber) {
        return towerData[caseNumber][4];
    }

    /**
     * Returns the range of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the range of a tower
     */
    public int getRange(int caseNumber) {
        return towerData[caseNumber][5];
    }

    /**
     * Returns the attack speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the attack speed of the tower
     */
    //public int getAttackSpeed(int caseNumber) {
     //   return towerData[caseNumber][6];
    //}

    /**
     * Returns the missle damage of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle damage of a tower
     */
    public int getMissleDamage(int caseNumber) {
        return towerData[caseNumber][7];
    }

    /**
     * Returns the missle speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle speed of a missle
     */
    public int getMissleSpeed(int caseNumber) {
        return towerData[caseNumber][8];
    }


    /**
     * Returns the attack speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the attack speed of the tower
     */
    
    public int getTowerAttackSpeed(int caseNumber) {
        return towerData[caseNumber][6];
    }

    /**
     * Returns the missle range where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle range
     */
    public int getMissleRange(int caseNumber) {
        return towerData[caseNumber][9];
    }

    /**
     * Returns the tower range where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the tower range
     */
    public int getTowerRange(int caseNumber) {
        return towerData[caseNumber][10];
    }
}
