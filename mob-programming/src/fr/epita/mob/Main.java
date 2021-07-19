package fr.epita.mob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) throws IOException {

		double[][] dataset = readMatrixFromFile("mnist_test.csv");
		double[] firstLine = dataset[0];

		fr.epita.mob.Image image = new fr.epita.mob.Image(firstLine);
		System.out.println(image.toString());
	}


	public static double[][] readMatrixFromFile(String path) throws FileNotFoundException {


		Scanner scanner = new Scanner(new File(path));
		String line = "";
		List<double[]> matrixAsList = new ArrayList<>();
		List<String> errors = new ArrayList<>();
		scanner.nextLine();
		int maxLines = 100;
		int counter = 0;
		while (scanner.hasNext() && counter < maxLines) {
			counter++;
			line = scanner.nextLine();
			String[] cells = line.split(",");
			double[] doubleArray = new double[cells.length];
			for (int i = 0; i < cells.length ; i++){
				try {
					double cellAsDouble = Double.parseDouble(cells[i]);
					doubleArray[i]= cellAsDouble;
					matrixAsList.add(doubleArray);
				}catch(NumberFormatException nfe){
					errors.add("error with line : " + line);
					continue;
				}

			}
		}

		double[][] matrix = new double[matrixAsList.size()][];
		for (int i = 0; i < matrixAsList.size(); i++){
			matrix[i] = matrixAsList.get(i);
		}
		scanner.close();
		return matrix;
	}

	private void fileLoadingWithFilesAPI() throws IOException {

		//file loading with the Files api
		List<String> lines = Files.readAllLines(new File("mnist_test.csv").toPath());
		System.out.println(lines);

	}
}
