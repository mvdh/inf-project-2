
/**
 * @author averaart
 */
public class UnitData
{
	/*
	 * 0 reward
	 * 1 hitPoints
	 * 2 Damage
	 * 3 Image
	 * 4 Speed
	 */
//	final private int[][] unitData;
//	final private String[] unitImages;	
	private GameStats stats;


	/**
	 * Constructor. Here is all the information of the towers added to unitData
	 */
	public UnitData(GameStats statsIn)
	{
		stats = statsIn;
//		unitData = new int[8][4];
//		unitImages = new String[7];
//
//		unitData[0][0] = 1; // reward
//		unitData[0][1] = 100; // hitPoints
//		unitData[0][2] = 100; // Damage
//		unitData[0][3] = 2; // Speed
//
//		unitData[1][0] = 1; // reward
//		unitData[1][1] = 200; // hitPoints
//		unitData[1][2] = 100; // Damage
//		unitData[1][3] = 2; // Speed
//
//		unitData[2][0] = 1; // reward
//		unitData[2][1] = 400; // hitPoints
//		unitData[2][2] = 100; // Damage
//		unitData[2][3] = 2; // Speed
//
//		unitData[3][0] = 2; // reward
//		unitData[3][1] = 800; // hitPoints
//		unitData[3][2] = 100; // Damage
//		unitData[3][3] = 2; // Speed
//
//		unitData[4][0] = 2; // reward
//		unitData[4][1] = 1600; // hitPoints
//		unitData[4][2] = 100; // Damage
//		unitData[4][3] = 2; // Speed
//
//		unitData[5][0] = 2; // reward
//		unitData[5][1] = 3200; // hitPoints
//		unitData[5][2] = 100; // Damage
//		unitData[5][3] = 3; // Speed
//
//		unitData[6][0] = 3; // reward
//		unitData[6][1] = 6400; // hitPoints
//		unitData[6][2] = 100; // Damage
//		unitData[6][3] = 2; // Speed
//
//                unitData[7][0] = 3; // reward
//		unitData[7][1] = 12800; // hitPoints
//		unitData[7][2] = 100; // Damage
//		unitData[7][3] = 3; // Speed                unitData[7][]

		/*
		unitImages[0] = "unit-mario1-f";
		unitImages[1] = "unit-mario2-f";
		unitImages[2] = "unit-mario3-f";
		unitImages[3] = "B3";
		unitImages[4] = "B4";
		unitImages[5] = "C1";
		unitImages[6] = "D1";
		 */
	}
//
//	public int getReward(int caseNumber)
//	{
//		return unitData[caseNumber][0];
//	}

	public int getDamage()
	{
		return 25;
	}

	public Unit getNewUnit(int waveNumber)
	{
                double speed = 2.0;
                int hitPoints;
                if(waveNumber == 0){
                     hitPoints = 100;
                } else {
                    hitPoints = (waveNumber) * 200;
                }
                int image = (waveNumber % 3) * 3;
                int reward = 0;

                if((waveNumber % 3) == 0) {
                    reward = (waveNumber + 3) / 3;
                } else if ((waveNumber % 3) == 1) {
                    reward = (waveNumber + 2) / 3;
                } else {
                    reward = (waveNumber + 1) / 3;
                }

                Unit res = new Unit(speed, hitPoints, image, reward, stats);
		res.setCaseNumber(waveNumber);
		return res;
	}
}
