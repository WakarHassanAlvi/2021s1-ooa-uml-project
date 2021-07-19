package fr.epita.mob;

public class Image {

	double[][] rawHandWrittenData;

	double label;

	public Image(double[] flatMatrixPlusLabel) {
		this.label = flatMatrixPlusLabel[0];

		double[] firstLineData = new double[flatMatrixPlusLabel.length - 1];
		System.arraycopy(flatMatrixPlusLabel, 1, firstLineData, 0, firstLineData.length);
		double[][] matrix = new double[28][28];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = firstLineData[i * matrix.length + j];
			}
			System.out.println();
		}
		this.rawHandWrittenData = matrix;

	}

	public Image(double[][] rawHandWrittenData, double label) {
		this.rawHandWrittenData = rawHandWrittenData;
		this.label = label;
	}

	public double[][] getRawHandWrittenData() {
		return rawHandWrittenData;
	}

	public void setRawHandWrittenData(double[][] rawHandWrittenData) {
		this.rawHandWrittenData = rawHandWrittenData;
	}

	public double getLabel() {
		return label;
	}

	public void setLabel(double label) {
		this.label = label;
	}


	@Override
	public String toString() {
		String result = "";

		for (int i = 0; i < rawHandWrittenData.length; i++) {
			for (int j = 0; j < rawHandWrittenData.length; j++) {
				if (rawHandWrittenData[i][j] > 127) {
					result += "o";
				} else {
					result += " ";
				}

			}
			result += System.getProperty("line.separator");

		}
		return result;

	}
}
