package fr.epita.java.syntax;

import java.util.Scanner;

public class SwitchCaseDemo {

	public static void main(String[] args) {

		System.out.println("Please choose your favorite game: ");
		System.out.println("a. space invaders");
		System.out.println("b. pacman");
		System.out.println("c. street fighters");
		System.out.println("your choice : (a|b|c)?");

		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();

//		if ("a".equals(answer)){ //answer.equals("a")
//			System.out.println("you've chosen : space invaders");
//		} else if ("b".equals(answer)){
//			System.out.println("you've chosen : pacman");
//		} else if ("c".equals(answer)){
//			System.out.println("you've chosen : street fighters");
//		} else {
//			System.out.println(answer +" is not a valid answer");
//		}

		switch (answer){
		case "a":
			System.out.println("you've chosen : space invaders");
			break;
		case "b":
			System.out.println("you've chosen : pacman");
			break;
		case "c":
			System.out.println("you've chosen : street fighters");
			break;
		default:
			System.out.println(answer +" is not a valid answer");
		}

		scanner.close();

	}
}
