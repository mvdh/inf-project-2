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
     *
     *      //missle stats
     * 5    missleDamage
     * 6    missleImage
     * 7    missleSpeed
     */
    private int[][] towerData;

    /**
     * 
     */
    public TowerData(){
        towerData = new int[3][8];
        towerData[0][0] = 1;        //caseNumber
        towerData[0][1] = 200;      //costs
        towerData[0][2] = 100;      //hitPoints
        towerData[0][3] = 100;      //range
        towerData[0][4] = 0;        //previousCaseNumber
        towerData[0][5] = 2;        //missleDamage
        towerData[0][6] = 1;        //missleImage
        towerData[0][7] = 10;       //missleSpeed
        towerData[0][8] = 1;        //towerImage

        towerData[1][0] = 2;        //caseNumber
        towerData[1][1] = 250;      //costs
        towerData[1][2] = 100;      //hitPoints
        towerData[1][3] = 100;      //range
        towerData[1][4] = 0;        //previousCaseNumber
        towerData[1][5] = 2;        //missleDamage
        towerData[1][6] = 1;        //missleImage
        towerData[1][7] = 15;       //missleSpeed
        towerData[1][8] = 1;        //towerImage

        towerData[2][0] = 3;        //caseNumber
        towerData[2][1] = 275;      //costs
        towerData[2][2] = 100;      //hitPoints
        towerData[2][3] = 100;      //range
        towerData[2][4] = 0;        //previousCaseNumber
        towerData[2][5] = 2;        //missleDamage
        towerData[2][6] = 2;        //missleImage
        towerData[2][7] = 20;       //missleSpeed
        towerData[2][8] = 1;        //towerImage
    }

    /**
     * Returns the hitpoints of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the costs of a tower
     */
    public int getCaseNumber(int caseNumber){
        return towerData[caseNumber - 1][0];
    }

    /**
     * Returns the costs of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the costs of a tower
     */
    public int getCosts(int caseNumber){
        return towerData[caseNumber - 1][1];
    }

    /**
     * Returns the hitpoints of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the hitpoints of a tower
     */
    public int getHitpoints(int caseNumber){
        return towerData[caseNumber - 1][2];
    }

    /**
     * Returns the range of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the range of a tower
     */
    public int getRange(int caseNumber){
        return towerData[caseNumber - 1][3];
    }

    /**
     * Returns the previous caseNumber of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the previous casenumber of a tower
     */
    public int getPreviousCaseNumber(int caseNumber){
        return towerData[caseNumber - 1][4];
    }

    /**
     * Returns the missle damage of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle damage of a tower
     */
    public int getMissleDamage(int caseNumber){
        return towerData[caseNumber - 1][5];
    }

    /**
     * Returns the imagenumber of the towers missle where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the image number of a missle
     */
    public int getMissleImage(int caseNumber){
        return towerData[caseNumber - 1][6];
    }

    /**
     * Returns the missle speed of the tower where the caseNumber of the tower
     * equals the parameter caseNumber. De caseNumber is used as caseNumber
     * minus one, because the two dimensial array is zero-based.
     *
     * @param caseNumber the caseNumber of the towertype
     * @return the missle speed of a tower
     */
    public int getMissleSpeed(int caseNumber){
        return towerData[caseNumber - 1][7];
    }
}
