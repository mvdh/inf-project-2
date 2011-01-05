/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author averaart
 */
public class UnitData {
    /*
     * 0    reward
     * 1    hitPoints
     * 2    Damage
     * 3    Image
     * 4    Speed
     */
    final private int[][] unitData;
    final private String[] unitImages;

    /**
     * Constructor. Here is all the information of the towers added to unitData
     */
    public UnitData() {
        unitData = new int[7][5];
        unitImages = new String[7];

        unitData[0][0] = 0;        //reward
        unitData[0][1] = 200;      //hitPoints
        unitData[0][2] = 100;      //Damage
        unitData[0][3] = 100;      //Image
        unitData[0][4] = -1;       //Speed

        unitData[1][0] = 0;        //reward
        unitData[1][1] = 200;      //hitPoints
        unitData[1][2] = 100;      //Damage
        unitData[1][3] = 100;      //Image
        unitData[1][4] = -1;       //Speed

        unitData[2][0] = 0;        //reward
        unitData[2][1] = 200;      //hitPoints
        unitData[2][2] = 100;      //Damage
        unitData[2][3] = 100;      //Image
        unitData[2][4] = -1;       //Speed

        unitData[3][0] = 0;        //reward
        unitData[3][1] = 200;      //hitPoints
        unitData[3][2] = 100;      //Damage
        unitData[3][3] = 100;      //Image
        unitData[3][4] = -1;       //Speed

        unitData[4][0] = 0;        //reward
        unitData[4][1] = 200;      //hitPoints
        unitData[4][2] = 100;      //Damage
        unitData[4][3] = 100;      //Image
        unitData[4][4] = -1;       //Speed

        unitData[5][0] = 0;        //reward
        unitData[5][1] = 200;      //hitPoints
        unitData[5][2] = 100;      //Damage
        unitData[5][3] = 100;      //Image
        unitData[5][4] = -1;       //Speed

        unitData[6][0] = 0;        //reward
        unitData[6][1] = 200;      //hitPoints
        unitData[6][2] = 100;      //Damage
        unitData[6][3] = 100;      //Image
        unitData[6][4] = -1;       //Speed



        unitImages[0] = "A1";
        unitImages[1] = "B1";
        unitImages[2] = "B2";
        unitImages[3] = "B3";
        unitImages[4] = "B4";
        unitImages[5] = "C1";
        unitImages[6] = "D1";

    }
}
