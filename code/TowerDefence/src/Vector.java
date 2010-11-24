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
