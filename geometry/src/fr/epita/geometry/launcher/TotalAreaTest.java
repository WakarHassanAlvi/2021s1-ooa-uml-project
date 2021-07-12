package fr.epita.geometry.launcher;

import fr.epita.geometry.datamodel.Circle;
import fr.epita.geometry.datamodel.Square;
import fr.epita.geometry.datamodel.Triangle;

public class TotalAreaTest {


	public static void main(String[] args) {
		Circle c1 = new Circle(10);
		Circle c2 = new Circle(100);
		Circle c3 = new Circle(1000);

		Square s1 = new Square(1);
		Square s2 = new Square(10);
		Square s3 = new Square(100);

		Triangle t1 = new Triangle(1,2,3,4);
		Triangle t2 = new Triangle(10,20,30,40);
		Triangle t3 = new Triangle(100,200,300,400);
		Triangle t4 = new Triangle(1000,2000,3000,4000);


	}
}
