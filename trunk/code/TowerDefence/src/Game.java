import javax.swing.JFrame;

public class Game extends JFrame
{
	public Game()
	{
		init();
		
		Matrix m = new Matrix();
		
		Vector v;
		for (int i = 0; i < 7; i++)
		{
			v = new Vector();
			v.add(new Tower());
			for (int j = 0; j < 15; j++)
			{
				v.add(new Field());
			}
			v.add(new Boom());
			m.add(v);
		}
		
		Field f;
		for (int i = 0; i < m.size(); i++)
		{
			for (int j = 0; j < m.get(i).size(); j++)
			{
				f = m.get(i, j);
				f.setLocation(j * 40, i * 40);
				if (f != null)
				{
					add(f);
				}
			}
		}
		System.out.println(m.toString());
		paintAll(getGraphics());
	}
	
	public void init()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
		setLayout(null);
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
}
