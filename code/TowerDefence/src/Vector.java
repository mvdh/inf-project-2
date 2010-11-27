public class Vector 
{
	Field[] vector;
	int empty;
	
	public Vector()
	{
		vector = new Field[0];
		empty = 0;
	}
	
	/**
	 * 
	 * @param f Field
	 * @param i int
	 * @return true if the selected index was empty
	 */
	public boolean add(Field f, int i)
	{
		boolean result = false;
		
		if (get(i) == null)
		{
			vector[i] = f;
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param in Field
	 */
	public void add(Field in)
	{
		if (in != null && empty > 0)
		{
			for (int i = 0; i < size(); i++)
			{
				if (get(i).equals(""))
				{
					vector[i] = in;
					break;
				}
			}
		}
		else
		{
			Field temp[] = new Field[size()];
			for (int i = 0; i < size(); i++)
			{
				temp[i] = vector[i];
			}
			
			vector = new Field[temp.length + 1];
			
			for (int i = 0; i < temp.length; i++)
			{
				vector[i] = temp[i];
			}
			
			vector[temp.length] = in;
			
			if (in == null)
			{
				empty++;
			}
		}
	}
	
	/**
	 * 
	 * @param f Field
	 * @return The field which is removed from this Vector
	 */
	public Field remove(Field f)
	{
		Field result = null;
		
		if (contains(f))
		{
			for (int i = 0; i < size(); i++)
			{
				if (get(i) != null && get(i).equals(f))
				{
					result = f;
					vector[i] = null;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param f Field
	 * @return true if this Vector contains f
	 */
	public boolean contains(Field f)
	{
		boolean result = false;
		
		for (int i = 0; i < size(); i++)
		{
			if (get(i) != null)
			{
				result |= get(i).equals(f);
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return vector.length
	 */
	public int size()
	{
		return vector.length;
	}
	
	/**
	 * 
	 * @return empty
	 */
	public int getEmpty()
	{
		return empty;
	}
	
	/**
	 * 
	 * @param i int
	 * @return vector[i]
	 */
	public Field get(int i)
	{
		return vector[i];
	}
	
	/**
	 * 
	 * @param f Field
	 * @return
	 * Number in the Vector
	 */
	public int get(Field f)
	{
		int n = -1;
		for (int i = 0; i < size(); i++)
		{
			if (get(i).equals(f))
			{
				n = i;
				break;
			}
		}
		
		return n;
	}
	
	public String toString()
	{
		String result = "";
		
		for (int i = 0; i < size(); i++)
		{
			if (get(i) == null)
			{
				result += ".";
			}
			else if (get(i) instanceof Tower)
			{
				result += "&";
			}
			else if (get(i) instanceof Boom)
			{
				result += "$";
			}
			else
			{
				result += "#";
			}
		}
		
		return result + "\n";
	}
}