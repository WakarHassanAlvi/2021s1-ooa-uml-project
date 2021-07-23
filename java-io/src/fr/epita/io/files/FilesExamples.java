package fr.epita.io.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class FilesExamples {


	public static void main(String[] args) throws IOException {
		//readOperations();


		Files.writeString(new File("testouput.txt").toPath(), "test",StandardOpenOption.APPEND, StandardOpenOption.WRITE);

		PrintWriter pw = new PrintWriter(new FileOutputStream("testouput.txt", true));
		pw.println("hey this is an example from printwriter");
		pw.flush();


	}

	private static void readOperations() throws IOException {
		List<String> lines = Files.readAllLines(new File("2021s2-java.iml").toPath());
		System.out.println(lines);

		Scanner scanner = new Scanner(new File("2021s2-java.iml"));
		while (scanner.hasNext()){
			String s = scanner.nextLine();
			System.out.println(s);
		}
		scanner.close();

		BufferedReader reader = new BufferedReader(new FileReader("2021s2-java.iml"));
		String currentLine;
		while ((currentLine = reader.readLine()) != null){
			System.out.println(currentLine);
		}

		FileInputStream ips =new FileInputStream("2021s2-java.iml");
		byte[] byteArray = new byte[1024];
		int repetitions =0;
		//TODO: fix this read operation
		while (ips.read(byteArray, repetitions *byteArray.length, byteArray.length) != 0){
			String string = new String(byteArray);
			System.out.println(string);
			repetitions++;
		}
	}

}
