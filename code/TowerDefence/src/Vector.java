public class Vector
{

	Field[] vector;
	int empty;

	public Vector()
	{
		vector = new Field[0];
		empty = 0;
	}

	public Vector(Field[] f)
	{
		vector = f;
		empty = 1;
	}

	/**
	 * Add f to the Vector at the selected index, if that index didn't contain a
	 * Field
	 * object already
	 * 
	 * @param f
	 *            Field
	 * @param i
	 *            int
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
	 * Add a Field object to the first position which is equal to null
	 * or to the end of the Vector object, after it's length is increased
	 * 
	 * @param in
	 *            Field
	 */
	public void add(Field in)
	{
		if (in != null && empty > 0)
		{
			for (int i = 0; i < size(); i++)
			{
				if (get(i) == null)
				{
					vector[i] = in;
					empty--;
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
	 * @param f
	 *            Field
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
	 * @param f
	 *            Field
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
	 * @return vector.length
	 */
	public int size()
	{
		return vector.length;
	}

	/**
	 * Get the amount of empty Field objects in the Vector object
	 * 
	 * @return empty
	 */
	public int getEmpty()
	{
		return empty;
	}

	/**
	 * Get the Field object at index i
	 * 
	 * @param i
	 *            int
	 * @return vector[i]
	 */
	public Field get(int i)
	{
		return vector[i];
	}

	/**
	 * Get the index of f
	 * 
	 * @param f
	 *            Field
	 * @return
	 *         Number in the Vector
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

	/**
	 * @param f
	 *            Field
	 * @return
	 *         This Vector split up in an x amount of smaller Vector objects,
	 *         this depends on the amount of times
	 *         this Vector contains f (it should be 1 time, which would result
	 *         in an array with a length of 2)
	 */
	public Vector[] split(Field f)
	{
		// Check how much times this Vector contains f
		int n = 1;
		for (int i = 0; i < size(); i++)
		{
			if (get(i).equals(f))
			{
				n++;
			}
		}

		// Create a new array with the amount of times f exists + 1
		Vector[] result = new Vector[n];

		// Split this Vector object in smaller Vector objects, split at Field f
		int x = 0;
		result[x] = new Vector();
		for (int i = 0; i < size(); i++)
		{
			if (!get(i).equals(f))
			{
				result[x].add(get(i));
			}
			else
			{
				x++;
				result[x] = new Vector();
			}
		}

		return result;
	}

	/**
	 * @param v
	 *            Vector
	 * @return
	 *         The combined Vector, without the Field objects which are present
	 *         a second time
	 */
	public Vector mergeAndCleanUp(Vector v)
	{
		if (v != null)
		{
			Vector result = new Vector();
			// Find a node of the 2 Vector objects
			Field node = findNode(v);

			// While the node isn't reached, copy this Vector object in result
			int n = 0;
			while (!get(n).equals(node))
			{
				result.add(get(n));
				n++;
			}

			// Get the first Field object which has to be added, this is the
			// first Field object
			// after the node in v
			n = 0;
			while (!v.get(n).equals(node))
			{
				n++;
			}

			// Add all Field objects which are after the node to result
			for (int i = n; i < v.size(); i++)
			{
				result.add(v.get(i));
			}

			return result;
		}
		else
		{
			return this;
		}
	}

	/**
	 * @param v
	 *            Vector
	 * @return
	 *         The first Field which is present in both Vector objects
	 */
	public Field findNode(Vector v)
	{
		Field result = null;

		// Find the first Field which is also present in v
		for (int i = 0; i < size(); i++)
		{
			if (v.contains(get(i)))
			{
				result = get(i);
				break;
			}
		}

		return result;
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
			else if (get(i) instanceof Tree)
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

	/**
	 * @return
	 *         Debug function
	 */
	public String print()
	{
		String result = "<Vector:\n";

		for (int i = 0; i < size(); i++)
		{
			if (get(i) != null)
			{
				result += get(i).toString() + "\n";
			}
			else
			{
				result += "null\n";
			}
		}

		return result + ">";
	}

	public Field[] getAll()
	{
		return vector;
	}
}
