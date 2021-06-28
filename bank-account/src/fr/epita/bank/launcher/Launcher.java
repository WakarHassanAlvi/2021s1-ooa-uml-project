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


		Double initialBalance = null;
		Double initialInterestRate = 0.005;
		try{
			System.out.println("Please input a the initial balance of the savings account");
			initialBalance = scanner.nextDouble();
			System.out.println("Please input a the interrest rate (default = 0.005):");
			initialInterestRate = scanner.nextDouble();
		}catch (Exception e){
			e.printStackTrace();
		}
		//check if values are correct
		if (initialBalance == null){ //we were not able to read correctly
			return; //?
		}


		SavingsAccount savingsAccount = new SavingsAccount(initialBalance, initialInterestRate);

		System.out.println("what amount would you like to withdraw?");
		try {
			int amount = scanner.nextInt();
			savingsAccount.withDraw(amount);
		}catch(Exception e){
			System.out.println("something went wrong, invalid amount");

		}

		double interests = savingsAccount.computeInterests();

		System.out.println(interests);

		scanner.close();

	}
}
