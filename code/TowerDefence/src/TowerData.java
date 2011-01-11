
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
     */
    final private int[][] towerData;
    final private String[] towerImages;
    //final ArrayList<Integer> sortedList;

    /**
     * Constructor. Here is all the information of the towers added to towerData
     */
    public TowerData() {
        towerData = new int[16][12];
        towerImages = new String[16];
        //sortedList = new ArrayList<Integer>();

        towerData[0][0] = 0;        //caseNumber
        towerData[0][1] = 200;      //costs
        towerData[0][2] = 100;      //hitPoints
        towerData[0][3] = 100;      //range
        towerData[0][4] = -1;       //previousCaseNumber
        towerData[0][5] = 2;        //missleDamage
        towerData[0][6] = 1;        //missleImage
        towerData[0][7] = 10;       //missleSpeed
        towerData[0][8] = 0;        //towerImage
        towerData[0][9] = 20;       //missleRange
        towerData[0][10] = 100;     //towerRange
        towerData[0][11] = 10;      //towerAttackSpeed

        towerData[1][0] = 1;        //caseNumber
        towerData[1][1] = 250;      //costs
        towerData[1][2] = 100;      //hitPoints
        towerData[1][3] = 100;      //range
        towerData[1][4] = -1;       //previousCaseNumber
        towerData[1][5] = 2;        //missleDamage
        towerData[1][6] = 1;        //missleImage
        towerData[1][7] = 15;       //missleSpeed
        towerData[1][8] = 1;        //towerImage
        towerData[1][9] = 20;       //missleRange
        towerData[1][10] = 110;     //towerRange
        towerData[1][11] = 10;      //towerAttackSpeed

        towerData[2][0] = 2;        //caseNumber
        towerData[2][1] = 200;      //costs
        towerData[2][2] = 100;      //hitPoints
        towerData[2][3] = 100;      //range
        towerData[2][4] = -1;       //previousCaseNumber
        towerData[2][5] = 2;        //missleDamage
        towerData[2][6] = 1;        //missleImage
        towerData[2][7] = 10;       //missleSpeed
        towerData[2][8] = 0;        //towerImage
        towerData[2][9] = 20;       //missleRange
        towerData[2][10] = 100;     //towerRange
        towerData[2][11] = 10;      //towerAttackSpeed

        towerData[3][0] = 3;        //caseNumber
        towerData[3][1] = 250;      //costs
        towerData[3][2] = 100;      //hitPoints
        towerData[3][3] = 100;      //range
        towerData[3][4] = -1;       //previousCaseNumber
        towerData[3][5] = 2;        //missleDamage
        towerData[3][6] = 1;        //missleImage
        towerData[3][7] = 15;       //missleSpeed
        towerData[3][8] = 1;        //towerImage
        towerData[3][9] = 20;       //missleRange
        towerData[3][10] = 110;     //towerRange
        towerData[3][11] = 10;      //towerAttackSpeed

        towerData[4][0] = 4;        //caseNumber
        towerData[4][1] = 275;      //costs
        towerData[4][2] = 100;      //hitPoints
        towerData[4][3] = 100;      //range
        towerData[4][4] = 0;        //previousCaseNumber
        towerData[4][5] = 2;        //missleDamage
        towerData[4][6] = 2;        //missleImage
        towerData[4][7] = 20;       //missleSpeed
        towerData[4][8] = 2;        //towerImage
        towerData[4][9] = 20;       //missleRange
        towerData[4][10] = 120;     //towerRange
        towerData[4][11] = 10;      //towerAttackSpeed

        towerData[5][0] = 5;        //caseNumber
        towerData[5][1] = 275;      //costs
        towerData[5][2] = 100;      //hitPoints
        towerData[5][3] = 100;      //range
        towerData[5][4] = 0;        //previousCaseNumber
        towerData[5][5] = 2;        //missleDamage
        towerData[5][6] = 2;        //missleImage
        towerData[5][7] = 20;       //missleSpeed
        towerData[5][8] = 3;        //towerImage
        towerData[5][9] = 20;       //missleRange
        towerData[5][10] = 120;     //towerRange
        towerData[5][11] = 10;      //towerAttackSpeed

        towerData[6][0] = 6;        //caseNumber
        towerData[6][1] = 275;      //costs
        towerData[6][2] = 100;      //hitPoints
        towerData[6][3] = 100;      //range
        towerData[6][4] = 0;        //previousCaseNumber
        towerData[6][5] = 2;        //missleDamage
        towerData[6][6] = 2;        //missleImage
        towerData[6][7] = 20;       //missleSpeed
        towerData[6][8] = 4;        //towerImage
        towerData[6][9] = 20;       //missleRange
        towerData[6][10] = 120;     //towerRange
        towerData[6][11] = 10;      //towerAttackSpeed

        towerData[7][0] = 7;        //caseNumber
        towerData[7][1] = 275;      //costs
        towerData[7][2] = 100;      //hitPoints
        towerData[7][3] = 100;      //range
        towerData[7][4] = 1;        //previousCaseNumber
        towerData[7][5] = 2;        //missleDamage
        towerData[7][6] = 2;        //missleImage
        towerData[7][7] = 20;       //missleSpeed
        towerData[7][8] = 2;        //towerImage
        towerData[7][9] = 20;       //missleRange
        towerData[7][10] = 120;     //towerRange
        towerData[7][11] = 10;      //towerAttackSpeed

        towerData[8][0] = 8;        //caseNumber
        towerData[8][1] = 275;      //costs
        towerData[8][2] = 100;      //hitPoints
        towerData[8][3] = 100;      //range
        towerData[8][4] = 1;        //previousCaseNumber
        towerData[8][5] = 2;        //missleDamage
        towerData[8][6] = 2;        //missleImage
        towerData[8][7] = 20;       //missleSpeed
        towerData[8][8] = 3;        //towerImage
        towerData[8][9] = 20;       //missleRange
        towerData[8][10] = 120;     //towerRange
        towerData[8][11] = 10;      //towerAttackSpeed

        towerData[9][0] = 9;        //caseNumber
        towerData[9][1] = 275;      //costs
        towerData[9][2] = 100;      //hitPoints
        towerData[9][3] = 100;      //range
        towerData[9][4] = 1;        //previousCaseNumber
        towerData[9][5] = 2;        //missleDamage
        towerData[9][6] = 2;        //missleImage
        towerData[9][7] = 20;       //missleSpeed
        towerData[9][8] = 4;        //towerImage
        towerData[9][9] = 20;       //missleRange
        towerData[9][10] = 120;     //towerRange
        towerData[9][11] = 10;      //towerAttackSpeed

        towerData[10][0] = 10;        //caseNumber
        towerData[10][1] = 275;      //costs
        towerData[10][2] = 100;      //hitPoints
        towerData[10][3] = 100;      //range
        towerData[10][4] = 2;        //previousCaseNumber
        towerData[10][5] = 2;        //missleDamage
        towerData[10][6] = 2;        //missleImage
        towerData[10][7] = 20;       //missleSpeed
        towerData[10][8] = 2;        //towerImage
        towerData[10][9] = 20;       //missleRange
        towerData[10][10] = 120;     //towerRange
        towerData[10][11] = 10;      //towerAttackSpeed

        towerData[11][0] = 11;        //caseNumber
        towerData[11][1] = 275;      //costs
        towerData[11][2] = 100;      //hitPoints
        towerData[11][3] = 100;      //range
        towerData[11][4] = 2;        //previousCaseNumber
        towerData[11][5] = 2;        //missleDamage
        towerData[11][6] = 2;        //missleImage
        towerData[11][7] = 20;       //missleSpeed
        towerData[11][8] = 3;        //towerImage
        towerData[11][9] = 20;       //missleRange
        towerData[11][10] = 120;     //towerRange
        towerData[11][11] = 10;      //towerAttackSpeed

        towerData[12][0] = 12;        //caseNumber
        towerData[12][1] = 275;      //costs
        towerData[12][2] = 100;      //hitPoints
        towerData[12][3] = 100;      //range
        towerData[12][4] = 2;        //previousCaseNumber
        towerData[12][5] = 2;        //missleDamage
        towerData[12][6] = 2;        //missleImage
        towerData[12][7] = 20;       //missleSpeed
        towerData[12][8] = 4;        //towerImage
        towerData[12][9] = 20;       //missleRange
        towerData[12][10] = 120;     //towerRange
        towerData[12][11] = 10;      //towerAttackSpeed

        towerData[13][0] = 13;        //caseNumber
        towerData[13][1] = 275;      //costs
        towerData[13][2] = 100;      //hitPoints
        towerData[13][3] = 100;      //range
        towerData[13][4] = 3;        //previousCaseNumber
        towerData[13][5] = 2;        //missleDamage
        towerData[13][6] = 2;        //missleImage
        towerData[13][7] = 20;       //missleSpeed
        towerData[13][8] = 2;        //towerImage
        towerData[13][9] = 20;       //missleRange
        towerData[13][10] = 120;     //towerRange
        towerData[13][11] = 10;      //towerAttackSpeed

        towerData[14][0] = 14;        //caseNumber
        towerData[14][1] = 275;      //costs
        towerData[14][2] = 100;      //hitPoints
        towerData[14][3] = 100;      //range
        towerData[14][4] = 3;        //previousCaseNumber
        towerData[14][5] = 2;        //missleDamage
        towerData[14][6] = 2;        //missleImage
        towerData[14][7] = 20;       //missleSpeed
        towerData[14][8] = 3;        //towerImage
        towerData[14][9] = 20;       //missleRange
        towerData[14][10] = 120;     //towerRange
        towerData[14][11] = 10;      //towerAttackSpeed

        towerData[15][0] = 15;        //caseNumber
        towerData[15][1] = 275;      //costs
        towerData[15][2] = 100;      //hitPoints
        towerData[15][3] = 100;      //range
        towerData[15][4] = 3;        //previousCaseNumber
        towerData[15][5] = 2;        //missleDamage
        towerData[15][6] = 2;        //missleImage
        towerData[15][7] = 20;       //missleSpeed
        towerData[15][8] = 4;        //towerImage
        towerData[15][9] = 20;       //missleRange
        towerData[15][10] = 120;     //towerRange
        towerData[15][11] = 10;      //towerAttackSpeed

        towerImages[0] = "A1";
        towerImages[1] = "B1";
        towerImages[2] = "C1";
        towerImages[3] = "D1";
        towerImages[4] = "A2";
        towerImages[5] = "A3";
        towerImages[6] = "A4";
        towerImages[7] = "B2";
        towerImages[8] = "B3";
        towerImages[9] = "B4";
        towerImages[10] = "C2";
        towerImages[11] = "C3";
        towerImages[12] = "C4";
        towerImages[13] = "D2";
        towerImages[14] = "D3";
        towerImages[15] = "D4";
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

    public String getTowerImageName(int caseNumber) {
        return towerImages[caseNumber];
    }

    /**
     * Finds the upgradables of the tower that was selected.
     *
     * @param caseNumber The case number of the current tower.
     * @return An ArrayList with the caseNumber of the upgradables. If there is
     * no upgradable, the ArrayList has one value: -1.
     */
    public ArrayList<Integer> getUpgradables(int caseNumber, int currency) {
        ArrayList<Integer> result = new ArrayList();

        /*
         * Determine if there is a tower with the caseNumber as
         * previousCaseNumber. If a tower has that previousCaseNumber, add it to
         * the list. The tower then is an upgradable of the current tower.
         */
        for (int i = 0; i < this.getTowerData().length; i++) {
            if (this.getTowerData()[i][4] == caseNumber) {
                result.add(this.getTowerData()[i][0]);
                System.out.println(this.getTowerData()[i][0]);
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
     * Returns the costs of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the costs of a tower
     */
    public int getCosts(int caseNumber) {
        return towerData[caseNumber][1];
    }

    /**
     * Returns the hitpoints of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the hitpoints of a tower
     */
    public int getHitpoints(int caseNumber) {
        return towerData[caseNumber][2];
    }

    /**
     * Returns the range of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the range of a tower
     */
    public int getRange(int caseNumber) {
        return towerData[caseNumber][3];
    }

    /**
     * Returns the previous caseNumber of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the previous casenumber of a tower
     */
    public int getPreviousCaseNumber(int caseNumber) {
        return towerData[caseNumber][4];
    }

    /**
     * Returns the missle damage of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle damage of a tower
     */
    public int getMissleDamage(int caseNumber) {
        return towerData[caseNumber][5];
    }

    /**
     * Returns the image number of the towers missle where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the image number of a missle
     */
    public int getMissleImage(int caseNumber) {
        return towerData[caseNumber][6];
    }

    /**
     * Returns the missle speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle speed of a missle
     */
    public int getMissleSpeed(int caseNumber) {
        return towerData[caseNumber][7];
    }

    /**
     * Returns the towerimage number where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the towerimage number
     */
    public int getTowerImage(int caseNumber) {
        return towerData[caseNumber][8];
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

    /**
     * Returns the attack speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the attack speed of the tower
     */
    public int getAttackSpeed(int caseNumber) {
        return towerData[caseNumber][11];
    }
}
