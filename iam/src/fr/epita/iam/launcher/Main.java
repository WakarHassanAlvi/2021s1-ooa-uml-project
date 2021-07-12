package fr.epita.iam.launcher;

import java.util.Scanner;

import fr.epita.iam.execution.menus.AuthenticationConsoleActivity;
import fr.epita.iam.execution.menus.MainMenu;

public class Main {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to this app, please type a user name and a password");

		if (!new AuthenticationConsoleActivity().authenticateUser(scanner)){
			return;
		}
		System.out.println("you were successfully authenticated!");
		String answer = "";
		//scope: level 0
		do {

			answer = new MainMenu().proposeMenuAndGetAnswer(scanner);
			//loop from here

			switch (answer) {
			case "a":
				System.out.println("create");
				break;

			case "b":
				System.out.println("modify");
				break;

			case "c":
				System.out.println("delete");
				break;

			case "q":
				System.out.println("goodbye!");
				break;
			default:
				System.out.println("option not recognized: " + answer);
			}
			//loop to here, and exit if the answer is equal to q
		} while (!"q".equals(answer)); //test made in level 0 scope
		scanner.close();
	}
}
