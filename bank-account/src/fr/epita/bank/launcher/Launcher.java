package fr.epita.bank.launcher;


import java.util.Scanner;

import fr.epita.bank.datamodel.Customer;
import fr.epita.bank.datamodel.SavingsAccount;

public class Launcher {

	public static void main(String[] args) {

		System.out.println("Hello, welcome to this application");
		System.out.println("please, create the customer account details");
		System.out.println("user name: ");

		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		System.out.println("address:" );
		String address = scanner.nextLine();

		Customer customer = new Customer(name, address);

		int initialBalance = 22980;
		double initialInterestRate = 0.005;
		SavingsAccount savingsAccount = new SavingsAccount(initialBalance, initialInterestRate);
		savingsAccount.withDraw(300);

		double interests = savingsAccount.computeInterests();

		System.out.println(interests);

	}
}
