package com.socialnet.map_entry;

import java.util.ArrayList;
import java.util.List;

public class ListEntry {
	List <MyEntry<String, String>> ListEntry=new  ArrayList<MyEntry<String, String>>();
	
	public ListEntry()
	{
		
	}

		public void Add(MyEntry<String, String> entry)
		{
			
			ListEntry.add(entry);
			
		}
		
		public void Delete(int i)
		{
			ListEntry.remove(i);
			
		}
		
		public void Clear()
		{
			ListEntry.clear();
			
		}
		
		@SuppressWarnings("rawtypes")
		public MyEntry GetObject(int i)
		{
			return ListEntry.get(i);
		}
		
		public int  Count()
		{
			return ListEntry.size();
		}
}
