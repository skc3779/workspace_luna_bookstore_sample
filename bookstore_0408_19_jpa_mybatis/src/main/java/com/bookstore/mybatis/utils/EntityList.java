package com.bookstore.mybatis.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

public class EntityList {
	
	public static <T> void list(List<T> list) {
		System.out.println("Listing of :");	
		
		Integer i = 0;
		for(T t : list) {
			try {
				
				System.out.println( t.getClass().getName() + " class : " + String.valueOf(i++)); 
				for( PropertyDescriptor pd : 
					Introspector.getBeanInfo(t.getClass()).getPropertyDescriptors())
				{
					if(!pd.getName().equals("class")) {
						System.out.println( pd.getName() + " : " + pd.getReadMethod().invoke(t));
					}
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
