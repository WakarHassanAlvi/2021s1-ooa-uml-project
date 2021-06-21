package fr.epita.bank.launcher;


import fr.epita.bank.datamodel.Customer;
import fr.epita.bank.datamodel.SavingsAccount;

public class Launcher {

	public static void main(String[] args) {
		Customer customer = new Customer();

		SavingsAccount savingsAccount = new SavingsAccount(22980, 0.005);
		savingsAccount.withDraw(300);

		double interests = savingsAccount.computeInterests();

		System.out.println(interests);

	}
}
