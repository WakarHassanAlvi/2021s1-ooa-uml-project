package fr.epita.geometry.datamodel;

public class Circle {

	private double radius;

	public Circle(double radius){
		this.radius = radius;
	}

	public void setRadius(double radius) {
		if (radius < 0){
			return;
		}
		this.radius = radius;
	}

	public static double calculatePerimeter(double radius) {
		return Math.PI * 2 * radius;
	}

	public double caculateArea(){
		return Math.PI * Math.pow(this.radius,2);
	}

	public double calculatePerimeter(){
		return Math.PI * 2 * this.radius;
	}

	public static double calculateArea(double radius){
		return Math.PI * Math.pow(radius,2);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Circle circle = (Circle) o;

		return Double.compare(circle.radius, radius) == 0;
	}

	@Override
	public int hashCode() {
		long temp = Double.doubleToLongBits(radius);
		return (int) (temp ^ (temp >>> 32));
	}
}
