package fr.epita.datastructures.demo;

import java.util.*;

public class MapDemo {

	public static void main(String[] args) {
		Map<String, String> airports = new LinkedHashMap<>();

		airports.put("CDG","Charles De Gaulle");
		airports.put("ORY","Orly");
		airports.put("LAX", "Los Angeles");


		System.out.println(airports);
		Set<String> stringSet = airports.keySet();
		Collection<String> values = airports.values();

		Set<Map.Entry<String, String>> entries = airports.entrySet();

		for(Map.Entry<String,String> entry : entries){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		String airport = airports.get("CDG");
		System.out.println(airport);

		airports.clear();

		System.out.println(airports);

	}
}
