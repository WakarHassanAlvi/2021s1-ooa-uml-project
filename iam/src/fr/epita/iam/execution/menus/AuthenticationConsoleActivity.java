package fr.epita.iam.execution.menus;

import java.util.Scanner;

public class AuthenticationConsoleActivity {


	public boolean authenticateUser(Scanner scanner){
		IamLog.info("the user is entering in the application");
		System.out.print("user: ");
		String userName = scanner.nextLine();
		System.out.print("password: ");
		String password = scanner.nextLine();

		//if user exists and the provided password is matching the expected one
		return userName.equals("admin") && password.equals("password");

	}

}
