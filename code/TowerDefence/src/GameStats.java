/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Maarten van den Hoek
 */
public class GameStats
{

    private boolean started;
    private TowerData towerData;
    private int points;
    private int gold;
    private int waveCounter;
    private int waveUnits;
    private int wave;
    private UnitData unitData;

    public UnitData getUnitData()
    {
        return unitData;
    }

    public void setUnitData(UnitData unitData)
    {
        this.unitData = unitData;
    }

    public int getWaveUnits()
    {
        return waveUnits;
    }

    public void setWaveUnits(int waveUnits)
    {
        this.waveUnits = waveUnits;
    }

    public int getWave()
    {
        return wave;
    }

    public void setWave(int wave)
    {
        this.wave = wave;
    }

    public int getWaveCounter()
    {
        return waveCounter;
    }

    public void setWaveCounter(int waveCounter)
    {
        this.waveCounter = waveCounter;
    }

    public void raiseWaveCounter()
    {
        waveCounter++;
    }

    public void raiseWaveUnits()
    {
        waveUnits++;
    }

    public void raiseWave()
    {
        wave++;
    }

    public GameStats()
    {
        started = false;
        points = 0;
        gold = 20;
        waveCounter = -75;
        waveUnits = 0;
        wave = 0;
        this.setTowerData(new TowerData());
        unitData = new UnitData();
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public void setTowerData(TowerData td)
    {
        this.towerData = td;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public void updateGold(int dir, int gold)
    {
        if (dir == -1)
        {
            this.gold -= gold;
        } else
        {
            this.gold += gold;
        }
    }

    public void updatePoints(int dir, int points)
    {
        if (dir == -1)
        {
            this.points -= points;
        } else
        {
            this.points += points;
        }
    }

    public TowerData getTowerData()
    {
        return this.towerData;
    }

    public int getPoints()
    {
        return this.points;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setStarted(boolean started)
    {
        this.started = started;
    }
}
