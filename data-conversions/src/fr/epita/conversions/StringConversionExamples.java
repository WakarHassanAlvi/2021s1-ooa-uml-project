package fr.epita.conversions;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Arrays;

public class StringConversionExamples {

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static void main(String[] args) throws ParseException {
//		getStringFromCSVLine();
//
//		doubleConversion("3.0");

		String dateAsString = "2021-01-01";
		String dateAndTimeAsString = "2021-01-01_20:00:01";

		String dateAndTimePattern = "yyyy-MM-dd_HH:mm:ss";


		Date date = new Date();
		System.out.println(date);

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);


		Date parsedDate = dateFormat.parse(dateAsString);
		System.out.println(parsedDate);


		LocalDate localDate = LocalDate.now();






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
