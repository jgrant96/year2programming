
public class SavingsAccount extends BankAccount{
private double interestRate;


/**
 * Contructor method to create a Saving Account instance
 * @param initialAmount The initial amount in the account
 * @param rate The interest rate
 */
public SavingsAccount (double initialAmount, double rate){	
	super (initialAmount);
	this.interestRate = rate;
}

/**
 * Accessor method to get the interest rate
 * @return the interest rate as a double
 */
public double getRate(){
	return this.interestRate;
}

/**
 * Method to add interest to the account balance
 */
public void calculateInterest(){
	this.deposit(this.getBalance()*this.interestRate);
}

/**
 * Returns a string containing the Balance and the Interest Rate
 */
public String toString(){
	return ("Balance: $" + this.getBalance() + " Interest Rate: " + this.interestRate + "%");
}

}
