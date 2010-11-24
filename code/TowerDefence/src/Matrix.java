public class Matrix 
{
	private Vector[] matrix;
	
	public Matrix()
	{
		matrix = new Vector[0];
	}

	/**
	 * 
	 * @param in Vector
	 */
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
	
	/**
	 * 
	 * @param in Field[]
	 */
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
	
	/**
	 * 
	 * @return matrix.length
	 */
	public int size()
	{
		return matrix.length;
	}
	
	/**
	 * Enhances the matrix, so all Vector Objects have the same size
	 */
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
	
	/**
	 * 
	 * @return The amount of Objects in the Vector Object with the most Objects
	 */
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
	
	/**
	 * 
	 * @param i int
	 * @return matrix[i]
	 */
	public Vector get(int i)
	{
		return matrix[i];
	}
	
	/**
	 * 
	 * @param i int
	 * @param j int
	 * @return get(i).get(j)
	 */
	public Field get(int i, int j)
	{
		return get(i).get(j);
	}
	
	public String toString()
	{
		String result = "";
		
		for (int i = 0; i < size(); i++)
		{
			result += get(i).toString();
		}
		
		return result;
	}
}
