package fr.epita.iam.launcher;

import java.util.Scanner;

public class Main {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to this app, please type a user name and a password");
		System.out.print("user: ");
		String userName = scanner.nextLine();
		System.out.print("password: ");
		String password = scanner.nextLine();

		//if user exists and the provided password is matching the expected one
		boolean authenticated = userName.equals("admin") && password.equals("password");
		if (! authenticated){
			//TODO remove this authentication strategy in favor of a real one!!!
			System.out.println("not authenticated... bye!");
			return;
		}
		System.out.println("you were successfully authenticated!");

		System.out.println();


		scanner.close();
	}
}
