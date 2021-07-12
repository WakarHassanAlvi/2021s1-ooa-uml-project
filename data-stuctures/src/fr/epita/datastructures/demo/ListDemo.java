package fr.epita.datastructures.demo;

import java.util.*;

public class ListDemo {


	public static void main(String[] args) {
		List<String> strList = new ArrayList<>();

		strList.add("this is");
		strList.add("a test");
		strList.add("a test");

		System.out.println(strList);

		//from a varargs
		List<String> anotherList = Arrays.asList("this is","another", "test", "with", "a", "lot of"," elements");
		System.out.println(anotherList);

		//from array
		String[] strArray = new String[]{"a","b","c"};
		List<String> strings = Arrays.asList(strArray);
		System.out.println(strings);

		for (String s:strList){
			System.out.println(s);
		}

		for (int i = 0; i< strList.size(); i++){
			System.out.println(strList.get(i));
		}

	}


}
