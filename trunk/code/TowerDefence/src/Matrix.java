
public class Matrix 
{
	private Vector[] matrix;
	
	public Matrix()
	{
		matrix = new Vector[0];
	}

	public void add(Vector in)
	{
		Vector temp[] = new Vector[size()];
		for (int i = 0; i < size(); i++)
		{
			temp[i] = matrix[i];
		}
		
		matrix = new Vector[temp.length + 1];
		
		for (int i = 0; i < temp.length; i++)
		{
			matrix[i] = temp[i];
		}
		
		matrix[temp.length] = in;
		enhance();
	}
	
	public void add(Field[] in)
	{
		Vector inNew = new Vector();
		for (int i = 0; i < in.length; i++)
		{
			inNew.add(in[i]);
		}
		
		Vector temp[] = new Vector[size()];
		for (int i = 0; i < size(); i++)
		{
			temp[i] = matrix[i];
		}
		
		matrix = new Vector[temp.length + 1];
		
		for (int i = 0; i < temp.length; i++)
		{
			matrix[i] = temp[i];
		}
		
		matrix[temp.length] = inNew;
		enhance();
	}
	
	public int size()
	{
		return matrix.length;
	}
	
	public void enhance()
	{
		int longest = getLongest();
		
		for (int i = 0; i < size(); i++)
		{
			while (longest > get(i).size())
			{
				get(i).add(null);
			}
		}
	}
	
	public int getLongest()
	{
		int result = 0;
		for (int i = 0; i < size(); i++)
		{
			if (result < get(i).size())
			{
				result = get(i).size();
			}
		}
		
		return result;
	}
	
	public Vector get(int i)
	{
		return matrix[i];
	}
	
	public Field get(int i, int j)
	{
		return get(i).get(j);
	}
}
