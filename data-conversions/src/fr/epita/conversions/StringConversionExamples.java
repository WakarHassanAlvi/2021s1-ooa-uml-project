package fr.epita.conversions;

import java.util.Arrays;

public class StringConversionExamples {

	public static void main(String[] args) {
		getStringFromCSVLine();

		doubleConversion("3.0");

		String dateAsString = "2021-01-01";
		String dateAndTimeAsString = "2021-01-01_20:00:01";

		String dateAndTimePattern = "yyyy-MM-dd_HH:mm:ss";



	}

	private static void doubleConversion(String input) {
		double d = Double.parseDouble(input);

		System.out.println("parsed double: " + d);
	}

	private static String getStringFromCSVLine() {
		String rawInput = "'a','3','c,zeaj'";

		String[] split = rawInput.split("','");

		String first = split[0];
		String last = split[split.length -1];
		split[0] = first.substring(1);
		split[split.length -1] = last.substring(0, last.length() - 2);

		System.out.println(Arrays.toString(split));

		String s = split[1];
		return s;
	}
}
