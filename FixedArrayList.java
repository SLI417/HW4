
public class FixedArrayList<T> implements ListInterface<T> {
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 15;
	
	
	public FixedArrayList(){
		this(DEFAULT_CAPACITY);
	}
	
	public FixedArrayList(int initialCapacity){
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(initialCapacity);
		
		T[] tempList = (T[])new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	}
	
	public void add(T newEntry){
		checkInitialization();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries ++;
		ensureCapacity();
	}
	
	/*public void add(int newPosition, T newEntry)
	{
	   checkInitialization();
	   if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
	   {
	      if (newPosition <= numberOfEntries)
	         makeRoom(newPosition);
	      list[newPosition] = newEntry;
	      numberOfEntries++;
	      ensureCapacity(); // Ensure enough room for next add
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Given position of add's new entry is out of bounds.");
	} 
	
	private void makeRoom(int newPosition)
	{
	   assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
	   int newIndex = newPosition;
	   int lastIndex = numberOfEntries;
	   // Move each entry to next higher index, starting at end of
	   // list and continuing until the entry at newIndex is moved
	   for (int index = lastIndex; index >= newIndex; index--)
	      list[index + 1] = list[index];
	}  */
	
	public T remove(int givenPosition)
	{
	   checkInitialization();
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      T result = list[givenPosition]; // Get entry to be removed
	      // Move subsequent entries towards entry to be removed,
	      // unless it is last in list
	      if (givenPosition < numberOfEntries)
	         removeGap(givenPosition);
	      numberOfEntries--;
	      return result;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to remove operation.");
	} 
	
	public T replace(int givenPosition, T newEntry)
	{
	   checkInitialization();
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      T originalEntry = list[givenPosition];
	      list[givenPosition] = newEntry;
	      return originalEntry;
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to replace operation.");
	} // end replace
	public T getEntry(int givenPosition)
	{
	   checkInitialization();
	   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
	   {
	      assert !isEmpty();
	      return list[givenPosition];
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Illegal position given to getEntry operation.");
	} 
	
	public boolean contains(T anEntry)
	{
	   checkInitialization();
	   boolean found = false;
	   int index = 1;
	   while (!found && (index <= numberOfEntries))
	   {
	      if (anEntry.equals(list[index]))
	         found = true;
	      index++;
	   } // end while
	   return found;
	} 
	
	public void add(T newEntry)
	{
	   checkInitialization();
	   list[numberOfEntries + 1] = newEntry;
	   numberOfEntries++;
	   ensureCapacity();
	} 
	
	public void add(int newPosition, T newEntry)
	{
	   checkInitialization();
	   if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
	   {
	      if (newPosition <= numberOfEntries)
	         makeRoom(newPosition);
	      list[newPosition] = newEntry;
	      numberOfEntries++;
	      ensureCapacity(); // Ensure enough room for next add
	   }
	   else
	      throw new IndexOutOfBoundsException(
	                "Given position of add's new entry is out of bounds.");
	} // end add
	private void makeRoom(int newPosition)
	{
	   int newIndex = newPosition;
	   int lastIndex = numberOfEntries;
	   for (int index = lastIndex; index >= newIndex; index--)
	      list[index + 1] = list[index];
	}
	
	public T[] toArray()
	   {
			checkInitialization();
	      
	      // The cast is safe because the new array contains null entries
	      @SuppressWarnings("unchecked")
	      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
	      for (int index = 0; index < numberOfEntries; index++)
	      {
	         result[index] = list[index + 1];
	      } // end for
	      
	      return result;
	   } // end toArray
	
	public int getLength()
	{
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty()
	{
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty

   // Doubles the capacity of the array list if it is full.
   // Precondition: checkInitialization has been called.
   private void ensureCapacity()
   {
      int capacity = list.length - 1;
      if (numberOfEntries >= capacity)
      {
         int newCapacity = 2 * capacity;
         checkCapacity(newCapacity); // Is capacity too big?
         list = Arrays.copyOf(list, newCapacity + 1);
      } // end if
   } // end ensureCapacity
   
   private void checkCapacity(int capacity)
   {
   if (capacity > MAX_CAPACITY)
   throw new IllegalStateException("Attempt to create a bag whose " +
   "capacity exeeds allowed " +
   "maximum of " + MAX_CAPACITY);
   }


}
