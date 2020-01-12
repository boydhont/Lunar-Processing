package lunar;

import java.util.ArrayList;

public class Lists {
	
	/**
	 * Combines items out several lists into one list
	 * @param <T> The type of items stored in the lists
	 * @param lists The input lists as a nested ArrayList
	 * @return The combined list
	 */
	
	static final public <T> ArrayList<T> combinedList(ArrayList<ArrayList<T>> lists)
	{
		ArrayList<T> combinedData = new ArrayList<T>();
		  
		for(ArrayList<T> list : lists)
		{
			combinedData.addAll(list);
		}
		  
		return combinedData;
	}
	
	/**
	 * Dispatches items in a list into two lists
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param pattern The dispatching pattern
	 * @return The dispatched lists as a nested ArrayList
	 */
	
	static final public <T> ArrayList<ArrayList<T>> dispatchedLists(ArrayList<T> list,ArrayList<Boolean> pattern) //Tested and works
	{
		ArrayList<ArrayList<T>> dispatchedLists = new ArrayList<ArrayList<T>>();
		ArrayList<T> listA = new ArrayList<T>();
		ArrayList<T> listB = new ArrayList<T>();
  
		while(pattern.size() < list.size())
		{
			pattern.addAll(pattern);
		}
  
		for(int i = 0; i < list.size(); i++)
		{
			if(pattern.get(i))
			{
				listA.add(list.get(i));
			}
			else
			{
				listB.add(list.get(i));
			}
		}
  
		dispatchedLists.add(listA);
		dispatchedLists.add(listB);
  
		return dispatchedLists;
	}
	
	/**
	 * Inserts a collection of items into a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param items The items to insert into the list
	 * @param index The position to insert the items into the list
	 * @return The list with the inserted items
	 */
	
	static final public <T> ArrayList<T> insertedItemsList(ArrayList<T> list, ArrayList<T> items, int index)
	{
		ArrayList<T> mergedList = new ArrayList<T>();  
		ArrayList<ArrayList<T>> splittedLists = splitLists(list, index);
  
		for(T t : splittedLists.get(0))
		{
			mergedList.add(t);
		}
  
		for(T t : items)
		{
			mergedList.add(t);
		}
  
		for(T t : splittedLists.get(1))
		{
			mergedList.add(t);
		}
  
		return mergedList;
	}
	
	/**
	 * Tests a list for null items
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @return The pattern describing the null items
	 */
	
	static final public <T> ArrayList<Boolean> nullPattern(ArrayList<T> list)
	{
		ArrayList<Boolean> isNull = new ArrayList<Boolean>();
  
		for(Object o : list)
		{
			if(o == null)
			{
				isNull.add(true);
			}
			else
			{
				isNull.add(false);
			}    
		}
  
		return isNull;
	}
	
	/**
	 * Retrieves the index of a certain item in a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param item The input item
	 * @return The index of the item in the list
	 */
	
	static final public <T> Integer itemIndex(ArrayList<T> list, T item)
	{
		for(int i = 0; i < list.size(); i++)
		{
			T t = list.get(i);
			if(t.equals(item))
			{
				return i;
			}
		}
  
		return null;
	}
	
	/**
	 * Retrieves a specific items from a list
	 * @param <T> The type of item stored in the list
	 * @param list The input list
	 * @param index The input index
	 * @return The item that is on the given index in the list
	 */
	
	static final public <T> T listItem(ArrayList<T> list, int index)
	{
		return list.get(index);
	}
	
	/**
	 * Measures the length of a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @return The length of the list
	 */
	
	static final public <T> int listLength(ArrayList<T> list)
	{
		return list.size();
	}
	
	/**
	 * Partitions a list into sub-lists
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param size The size of the sub-lists
	 * @return A list containing the sub-lists
	 */
	
	static final public <T> ArrayList<ArrayList<T>> listPartitions(ArrayList<T> list, int size)
	{
		ArrayList<ArrayList<T>> partitions = new ArrayList<ArrayList<T>>();
		ArrayList<T> partition = new ArrayList<T>();
  
		int count = 0;
  
		for(T t : list)
		{
			partition.add(t);
    
			count++;
			if(count == size)
			{
				partitions.add(partition);
				partition = new ArrayList<T>();
				count = 0;
			}
		}
  
		if(partition.size() > 0)
		{
			partitions.add(partition);
		}
    
		return partitions;
	}
	
	/**
	 * Pick and choose from a set of input data
	 * @param <T> The type of items stored in the list
	 * @param listA The first list
	 * @param listB The second list
	 * @param pattern The pattern for the data picking
	 * @return A list with the picked data
	 */
	
	static final public <T> ArrayList<T> pickedAndChosenList(ArrayList<T> listA, ArrayList<T> listB, ArrayList<Boolean> pattern)
	{
		ArrayList<T> chosenList = new ArrayList<T>();
  
		int listSize = listA.size();
		if(listB.size() > listA.size())
		{
			listSize = listB.size();
		}
  
		while(pattern.size() < listSize)
		{
			pattern.addAll(pattern);
		}
  
		for(int i = 0; i< listSize; i++)
		{
			if(pattern.get(i))
			{
				if(i < listA.size())
				{
					T t = listA.get(i);
					chosenList.add(t);
				}
				else
				{
					T t = listB.get(i);
					chosenList.add(t);
				}
			}
			else
			{
				if(i < listB.size())
				{
					T t = listB.get(i);
					chosenList.add(t);
				}
				else
				{
					T t = listA.get(i);
					chosenList.add(t);
				}
			}
		}
  
		return chosenList;  
	}
	
	/**
	 * Replaces certain items in a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param replacements The items that will replace the items in the list
	 * @param indexes The indices of the items to replace
	 * @return The list with replaced items
	 */
	
	static final public <T> ArrayList<T> replacedItemsList(ArrayList<T> list, ArrayList<T> replacements, ArrayList<Integer> indexes)
	{
		ArrayList<T> replacedList = list;
  
		for(int i = 0; i < indexes.size(); i++)
		{
			int index = indexes.get(i);
			T t = replacements.get(i);
			replacedList.set(index, t);    
		}
  
		return replacedList;
	}
	
	/**
	 * Replaces nulls with other data
	 * @param <T> The type of items stored in the list
	 * @param list The input list The items that will replace the items in the list
	 * @param replacements
	 * @return The list with replaced items
	 */
	
	static final public <T> ArrayList<T> replacedNullsList(ArrayList<T> list, ArrayList<T> replacements)
	{
		ArrayList<T> replacedNulls = list;
		int index = 0;
  
		for(int j = 0; j < replacedNulls.size(); j++)
		{
			T t = replacedNulls.get(j);
			if(t == null)
			{
				T r = replacements.get(index);
				replacedNulls.set(j, r);
				index++;
			}
		}
  
		return replacedNulls;
	}
	
	/**
	 * Reverses the order of a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @return The reversed list
	 */
	
	static final public <T> ArrayList<T> reversedList(ArrayList<T> list)
	{
		ArrayList<T> reversedList = new ArrayList<T>();
  
		for(int i = 0; i < list.size(); i++)
		{
			T t = list.get((list.size()-1)-i);
			reversedList.add(t);
		}
  
		return reversedList;
	}
	
	/**
	 * Offsets all items in a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param size The amount of steps to shift the list
	 * @return The shifted list
	 */
	
	static final public <T> ArrayList<T> shiftedList(ArrayList<T> list, int size)
	{
		ArrayList<T> shiftedList = new ArrayList<T>();
  
		while(size > list.size())
		{
			size = size - list.size();
		}
		ArrayList<ArrayList<T>> splittedLists = splitLists(list, list.size()-size);
		shiftedList = splittedLists.get(1);
  
		for(T t : splittedLists.get(0))
		{
			shiftedList.add(t);
		}
  
		return shiftedList;
	}
	
	/**
	 * Sifts elements using a repeating index pattern
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param pattern The sift pattern
	 * @return The sifted list
	 */
	
	static final public <T> ArrayList<T> siftedList(ArrayList<T> list, ArrayList<Boolean> pattern)
	{
		ArrayList<T> siftedList = new ArrayList<T>();
  
		while(pattern.size() < list.size())
		{
			pattern.addAll(pattern);
		}
  
		for(int i = 0; i < list.size(); i++)
		{
			if(pattern.get(i))
			{
				siftedList.add(null);
			}
			else
			{
				T t = list.get(i);
				siftedList.add(t);
			}
		}
  
		return siftedList;
	}
	
	/**
	 * Splits a list into separate parts
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param index The index at which the list will be split
	 * @return A list containing the two list halves
	 */
	
	static final public <T> ArrayList<ArrayList<T>> splitLists(ArrayList<T> list, int index)
	{
		ArrayList<ArrayList<T>> splittedLists = new ArrayList<ArrayList<T>>();
		ArrayList<T> listA = new ArrayList<T>();
		ArrayList<T> listB = new ArrayList<T>();
  
		for(int i = 0; i < list.size(); i++)
		{
			T t = list.get(i);
			if(i<index)
			{
				listA.add(t);
			}
			else
			{
				listB.add(t);
			}
		}
  
		splittedLists.add(listA);
		splittedLists.add(listB);
  
		return splittedLists;
	}
	
	/**
	 * Extracts a sublist from a list
	 * @param <T> The type of items stored in the list
	 * @param list The input list
	 * @param start The starting index to extract the items
	 * @param end The ending index to extract the items
	 * @return The sublist
	 */
	
	static final public <T> ArrayList<T> subList(ArrayList<T> list, int start, int end)
	{
		ArrayList<T> subList = new ArrayList<T>();
  
		for(int i = 0; i<list.size(); i++)
		{
			if(i >= start && i < end)
			{
				T t = list.get(i);
				subList.add(t);
			}
		}
  
		return subList;
	}
	
	/**
	 * Weaves a set of input data
	 * @param <T>  The type of items stored in the list
	 * @param listA The first list
	 * @param listB The second list
	 * @param pattern The weave patter
	 * @return THe woven list
	 */
	
	static final public <T> ArrayList<T> wovenList(ArrayList<T> listA, ArrayList<T> listB, ArrayList<Boolean> pattern)
	{
		ArrayList<T> wovenList = new ArrayList<T>();
  
		int listSize = listA.size() + listB.size();
		while(pattern.size() < listSize)
		{
			pattern.addAll(pattern);
		}
  
		int indexA = 0;
		int indexB = 0; //WHY IS THIS 1????
  
		for(int i = 0; i < listSize; i++)
		{
			if(pattern.get(i))
			{
				if(indexA < listA.size())
				{
					T t = listA.get(indexA);
					wovenList.add(t);
					indexA++;
				}
				else
				{
					T t = listB.get(indexB);
					wovenList.add(t);
					indexB++;
				}
			}
			else
			{
				if(indexB < listB.size())
				{
					T t = listB.get(indexB);
					wovenList.add(t);
					indexB++;
				}
				else
				{
					T t = listA.get(indexA);
					wovenList.add(t);
					indexA++;
				}
			}
		}
  
		return wovenList;
	}
	
}
