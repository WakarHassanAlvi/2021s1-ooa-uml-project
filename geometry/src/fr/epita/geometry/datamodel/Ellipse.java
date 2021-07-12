package fr.epita.geometry.datamodel;

public class Ellipse {

	double semiMinorAxis;
	double semiMajorAxis;

	public double getSemiMinorAxis() {
		return semiMinorAxis;
	}

	public void setSemiMinorAxis(double semiMinorAxis) {
		this.semiMinorAxis = semiMinorAxis;
	}

	public double getSemiMajorAxis() {
		return semiMajorAxis;
	}

	public void setSemiMajorAxis(double semiMajorAxis) {
		this.semiMajorAxis = semiMajorAxis;
	}

	public Ellipse(double semiMinorAxis, double semiMajorAxis) {
		this.semiMinorAxis = semiMinorAxis;
		this.semiMajorAxis = semiMajorAxis;
	}


	public double calculateArea(){
		return 123;
	}



}
