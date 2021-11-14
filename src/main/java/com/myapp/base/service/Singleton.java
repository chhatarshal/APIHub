package com.myapp.base.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



public class Singleton {
	private static volatile Singleton _instance;

	/**
	 * * Double checked locking code on Singleton
	 * 
	 * @return Singelton instance
	 */
	public static Singleton getInstance() {
		if (_instance == null) {
			synchronized (Singleton.class) {
				if (_instance == null) {
					_instance = new Singleton();
				}
			}
		}
		return _instance;
	}
	
	
	public static void main(String[] args) {
		List<String> values = new ArrayList<>();
		values.add("FirstVal");
		values.add(null);
		values.add("SecondVal");
		values = null;
		Optional.ofNullable(values).filter(val -> val.equals("")).ifPresent(System.out::println);//stream().forEach(System.out::println);
		//values.stream().filter(s -> s.equals("")).findAny().i;
		
		
		//System.out.println(Collections.singletonList("test"));
	}
}