
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

    /**
     * Constructor. Here is all the information of the towers added to towerData
     */
    public TowerData() {
        towerData = new int[7][12];
        towerImages = new String[7];

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
        towerData[2][1] = 275;      //costs
        towerData[2][2] = 100;      //hitPoints
        towerData[2][3] = 100;      //range
        towerData[2][4] = 1;        //previousCaseNumber
        towerData[2][5] = 2;        //missleDamage
        towerData[2][6] = 2;        //missleImage
        towerData[2][7] = 20;       //missleSpeed
        towerData[2][8] = 2;        //towerImage
        towerData[2][9] = 20;       //missleRange
        towerData[2][10] = 120;     //towerRange
        towerData[2][11] = 10;      //towerAttackSpeed

        towerData[3][0] = 3;        //caseNumber
        towerData[3][1] = 275;      //costs
        towerData[3][2] = 100;      //hitPoints
        towerData[3][3] = 100;      //range
        towerData[3][4] = 1;        //previousCaseNumber
        towerData[3][5] = 2;        //missleDamage
        towerData[3][6] = 2;        //missleImage
        towerData[3][7] = 20;       //missleSpeed
        towerData[3][8] = 3;        //towerImage
        towerData[3][9] = 20;       //missleRange
        towerData[3][10] = 120;     //towerRange
        towerData[3][11] = 10;      //towerAttackSpeed

        towerData[4][0] = 4;        //caseNumber
        towerData[4][1] = 275;      //costs
        towerData[4][2] = 100;      //hitPoints
        towerData[4][3] = 100;      //range
        towerData[4][4] = 1;        //previousCaseNumber
        towerData[4][5] = 2;        //missleDamage
        towerData[4][6] = 2;        //missleImage
        towerData[4][7] = 20;       //missleSpeed
        towerData[4][8] = 4;        //towerImage
        towerData[4][9] = 20;       //missleRange
        towerData[4][10] = 120;     //towerRange
        towerData[4][11] = 10;      //towerAttackSpeed

        towerData[5][0] = 0;        //caseNumber
        towerData[5][1] = 200;      //costs
        towerData[5][2] = 100;      //hitPoints
        towerData[5][3] = 100;      //range
        towerData[5][4] = -1;       //previousCaseNumber
        towerData[5][5] = 2;        //missleDamage
        towerData[5][6] = 1;        //missleImage
        towerData[5][7] = 10;       //missleSpeed
        towerData[5][8] = 0;        //towerImage
        towerData[5][9] = 20;       //missleRange
        towerData[5][10] = 100;     //towerRange
        towerData[5][11] = 10;      //towerAttackSpeed

        towerData[6][0] = 1;        //caseNumber
        towerData[6][1] = 250;      //costs
        towerData[6][2] = 100;      //hitPoints
        towerData[6][3] = 100;      //range
        towerData[6][4] = -1;       //previousCaseNumber
        towerData[6][5] = 2;        //missleDamage
        towerData[6][6] = 1;        //missleImage
        towerData[6][7] = 15;       //missleSpeed
        towerData[6][8] = 1;        //towerImage
        towerData[6][9] = 20;       //missleRange
        towerData[6][10] = 110;     //towerRange
        towerData[6][11] = 10;      //towerAttackSpeed


        towerImages[0] = "A1";
        towerImages[1] = "B1";
        towerImages[2] = "B2";
        towerImages[3] = "B3";
        towerImages[4] = "B4";
        towerImages[5] = "C1";
        towerImages[6] = "D1";

    }

    /**
     * Return the towerData. towerData contains the information of all towers.
     *
     * @return the towerData.
     */
    public int[][] getTowerData() {
        return this.towerData;
    }

    public String getTowerImageName(int caseNumber) {
        return towerImages[this.getTowerImage(caseNumber)];
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
        int[][] data = this.getTowerData();

        /*
         * Determine if there is a tower with the caseNumber as
         * previousCaseNumber. If a tower has that previousCaseNumber, add it to
         * the list. The tower then is an upgradable of the current tower.
         */
        for (int i = 0; i < data.length; i++) {
            if (data[i][4] == caseNumber) {
                result.add(data[i][0]);
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
