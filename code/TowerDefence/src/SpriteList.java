import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Slayer
 */
public class SpriteList
{
	private ArrayList<Unit> unitList;
	private ArrayList<Projectile> projectileList;

	public SpriteList()
	{
		unitList = new ArrayList<Unit>();
		projectileList = new ArrayList<Projectile>();
	}

	public void step()
	{
		for (Unit a : unitList)
		{
			// System.out.println(a);
			a.step();
		}
		for (Projectile a : projectileList)
		{
			// System.out.println(a);
			a.step();
		}
	}

	public ArrayList<Unit> getUnitList()
	{
		return unitList;
	}

	public ArrayList<Projectile> getProjectileList()
	{
		return projectileList;
	}

	public void add(Sprite a)
	{
		if (a instanceof Unit)
		{
			unitList.add((Unit) a);
		}
		else if (a instanceof Projectile)
		{
			projectileList.add((Projectile) a);
		}
	}

	public Sprite remove(Sprite a)
	{
		Sprite res = null;
		if (a instanceof Unit)
		{
			res = a;// (Unit) a;
			for (int i = 0; i < unitList.size(); i++)
			{
				if (res == unitList.get(i))
				{
					unitList.remove(i);
					break;
				}
			}
		}
		if (a instanceof Projectile)
		{
			res = (Projectile) a;
			for (int i = 0; i < projectileList.size(); i++)
			{
				if (res == projectileList.get(i))
				{
					projectileList.remove(i);
					break;
				}
			}
		}
		return res;
	}
}
