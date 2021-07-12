package fr.epita.iam.execution.menus;

import java.util.Scanner;

public class MainMenu {

	public String proposeMenuAndGetAnswer(Scanner scanner){
		//scope: level 1
		System.out.println("What operation would you like to do?");
		System.out.println("a. Create an identity");
		System.out.println("b. Update an identity");
		System.out.println("c. Delete an identity");
		System.out.println("q. Quit");
		System.out.print("your choice:");
		return scanner.nextLine();


	}

}
