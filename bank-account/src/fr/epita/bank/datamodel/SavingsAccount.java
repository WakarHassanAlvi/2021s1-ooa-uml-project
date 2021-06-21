package fr.epita.bank.datamodel;

public class SavingsAccount extends Account{
	double interestRate;

	public double computeInterests(){
		return interestRate * balance;
	}

	public void withDraw(int moneyAmount){
		balance = balance - moneyAmount;
	}

	public SavingsAccount(double initialBalance, double initialInterestRate){
		balance = initialBalance;
		interestRate = initialInterestRate;
	}

	public SavingsAccount(){
	}

}
