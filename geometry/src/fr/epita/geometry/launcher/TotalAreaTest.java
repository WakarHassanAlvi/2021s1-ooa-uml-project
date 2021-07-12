package fr.epita.geometry.launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.epita.geometry.datamodel.Circle;
import fr.epita.geometry.datamodel.Shape;
import fr.epita.geometry.datamodel.Square;
import fr.epita.geometry.datamodel.Triangle;

public class TotalAreaTest {


	public static void main(String[] args) {

		List<Shape> shapeList = new ArrayList<>();

		Circle c1 = new Circle(10);
		Circle c2 = new Circle(100);
		Circle c3 = new Circle(1000);
		shapeList.addAll(Arrays.asList(c1,c2,c3));

		Square s1 = new Square(1);
		Square s2 = new Square(10);
		Square s3 = new Square(100);
		shapeList.addAll(Arrays.asList(s1,s2,s3));

		Triangle t1 = new Triangle(1,2,3,4);
		Triangle t2 = new Triangle(10,20,30,40);
		Triangle t3 = new Triangle(100,200,300,400);
		Triangle t4 = new Triangle(1000,2000,3000,4000);
		shapeList.addAll(Arrays.asList(t1,t2,t3,t4));

		double area = 0.0;
//		for (Circle circle : circles){
//			area += circle.calculateArea();
//		}
//		for (Triangle circle : triangles){
//			area += circle.calculateArea();
//		}

		for (Shape shape :shapeList){
			area += shape.calculateArea();
			System.out.println(shape.getClass());
			//type casting example:
			if ( shape instanceof Triangle){
				Triangle triangle = (Triangle) shape;
				System.out.println("triangle base : " + triangle.getBase());
			}
		}

		System.out.println("global area: "+area);

	}
}
