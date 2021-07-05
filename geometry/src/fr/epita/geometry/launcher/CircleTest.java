package fr.epita.geometry.launcher;

import java.util.Arrays;
import java.util.List;

import fr.epita.geometry.datamodel.Circle;

public class CircleTest {

	public static void main(String[] args) {
		Circle c1 = new Circle(10);

		double area = c1.caculateArea();
		Circle c2 = new Circle(100);
		double areaForC2 = c2.caculateArea();


		double areaForC2ViaStatic = Circle.calculateArea(100);
		double perimeterForC2ViaStatic = Circle.calculatePerimeter(100);

		Circle c3 = new Circle(4);
		Circle c4 = new Circle(4);

		if (c3.equals(c4)){
			System.out.println("equal");
		} else{
			System.out.println("not equal");
		}


		List<Circle> circles = Arrays.asList(c1, c2, c3, c4);
		double globalArea = 0.0;
		double globalPerimeter = 0.0;
		for (Circle circle : circles){
			globalArea += circle.caculateArea();
			globalPerimeter += circle.calculatePerimeter();
		}

		System.out.println("Area for circles: " + globalArea);
		System.out.println("Perimeter for circles: " + globalPerimeter);


	}
}
