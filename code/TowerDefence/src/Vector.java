
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
     * Add f to the Vector at the selected index, if that index didn't contain a Field
     * object already
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
     * Add a Field object to the first position which is equal to null
     * or to the end of the Vector object, after it's length is increased
     * @param in Field
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
                    break;
                }
            }
        } else
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
     * Get the amount of empty Field objects in the Vector object
     * @return empty
     */
    public int getEmpty()
    {
        return empty;
    }

    /**
     *
     * Get the Field object at index i
     * @param i int
     * @return vector[i]
     */
    public Field get(int i)
    {
        return vector[i];
    }

    /**
     *
     * Get the index of f
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
    
    public Vector[] split(Field f)
    {
        int n = 1;
        for (int i = 0; i < size(); i++)
        {
            if (get(i).equals(f, 1))
            {
                n++;
            }
        }
        
        Vector[] result = new Vector[n];
        System.out.println("n = " + n);
        
        int x = 0;
        result[x] = new Vector();
        for (int i = 0; i < size(); i++)
        {
            if (!get(i).equals(f, 1))
            {
                System.out.println("x = " + x);
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
    
    public Vector mergeAndCleanUp(Vector v)
    {
        Vector result = new Vector();
        Field node = findNode(v);
        
        int n = 0;
        while (!get(n).equals(node, 1))
        {
            result.add(get(n));
            n++;
        }
        
        n = 0;        
        while(!v.get(n).equals(node, 1))
        {
            n++;
        }
        
        for (int i = n; i < v.size(); i++)
        {
            result.add(v.get(i));
        }
        
        return result;
    }
    
   /* public Vector merge(Vector v, Vector base)
    {
        Vector result = new Vector();
        
        if (size() <= v.size())
        {
            for (int i = 0; i < size(); i++)
            {
                if (v.get(i).equals(get(i), 1))
                {
                    result.add(get(i));
                }
                else if (base.contains(get(i)))
                {
                    int n = i;
                    while(!v.get(n).equals(get(i + 1)))
                    {
                        result.add(v.get(n));
                        n++;
                    }
                }
                else if (base.contains(v.get(i)))
                {
                    int n = i;
                    while(!get(n).equals(v.get(i + 1)))
                    {
                        result.add(get(n));
                        n++;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < v.size(); i++)
            {
                if (v.get(i).equals(get(i), 1))
                {
                    result.add(get(i));
                }
                else if (base.contains(get(i)))
                {
                    int n = i;
                    while(!v.get(n).equals(get(i + 1)))
                    {
                        result.add(v.get(n));
                        n++;
                    }
                }
                else if (base.contains(v.get(i)))
                {
                    int n = i;
                    while(!get(n).equals(v.get(i + 1)))
                    {
                        result.add(get(n));
                        n++;
                    }
                }
            }
        }

        return result;
    }*/
    
    public Field findNode(Vector v)
    {
        Field result = null;
        
        if (v != null)
        {
            for (int i = 0; i < size(); i++)
            {
                if (v.contains(get(i)))
                {
                    result = get(i);
                    break;
                }
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
            } else if (get(i) instanceof Tower)
            {
                result += "&";
            } else if (get(i) instanceof Tree)
            {
                result += "$";
            } else
            {
                result += "#";
            }
        }

        return result + "\n";
    }
    
    public String print()
    {
        String result = "<Vector:\n";
        
        for (int i = 0; i < size(); i++)
        {
            result += get(i).toString() + "\n";
        }
        
        return result + ">";
    }
}
