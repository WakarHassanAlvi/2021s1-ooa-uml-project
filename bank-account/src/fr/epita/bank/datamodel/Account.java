package fr.epita.bank.datamodel;

public class Account {
	protected double balance;

	public Account(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
