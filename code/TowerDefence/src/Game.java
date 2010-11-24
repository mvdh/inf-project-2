
public class Game 
{
	public Game()
	{
		Matrix m = new Matrix();
		
		Vector v;
		for (int i = 0; i < 7; i++)
		{
			v = new Vector();
			for (int j = 0; j < 17; j++)
			{
				v.add(new Field());
			}
			m.add(v);
		}
		
		System.out.println(m.toString());
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
}
