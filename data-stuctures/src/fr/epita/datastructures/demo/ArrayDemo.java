package fr.epita.datastructures.demo;

import java.util.Arrays;

public class ArrayDemo {


	public static void main(String[] args) {
		String[] strArray = new String[3];
		strArray[0] = "test";
		strArray[1] = "of";
		strArray[2] = "array data structure";


		System.out.println(strArray);

		String[] otherArray = new String[]{"another","test"};

		System.out.println(Arrays.toString(strArray));

		for (int i = 0 ; i < strArray.length; i ++){
			System.out.println(strArray[i]);
		}

		for (String string: strArray){
			System.out.println(string);
		}





	}

}
