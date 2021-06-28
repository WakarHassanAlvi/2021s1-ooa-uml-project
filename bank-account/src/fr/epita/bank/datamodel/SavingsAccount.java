package fr.epita.bank.datamodel;

/**
 * javadoc about savings account
 */
public class SavingsAccount extends Account{
	double interestRate;

	public double computeInterests(){
		return interestRate * balance;
	}

	public void withDraw(int moneyAmount){
		balance = balance - moneyAmount;
	}

	public SavingsAccount(double initialBalance, double initialInterestRate){
		super(initialBalance);
		interestRate = initialInterestRate;
	}

	public SavingsAccount(){
		super(0);
	}

}
