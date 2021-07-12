package fr.epita.datastructures.demo;

import java.util.*;

public class SetDemo {

	public static void main(String[] args) {
		Set<String> stringSet = new LinkedHashSet<>();

		stringSet.add("this is");
		stringSet.add("a test");
		stringSet.add("a test");

		System.out.println(stringSet);

		for(String s : stringSet){
			System.out.println(s);
		}
		stringSet.size();

		List<String> strings = new ArrayList<>(stringSet);

		String[] stringArray = strings.toArray(new String[0]);

	}
}
