public class BankAccount {

	private int balance;

	public void credit(int amount) {
		balance += amount;
	}

	public void transfer(BankAccount payee, int transferAmount) {
		credit(-transferAmount);
		payee.credit(transferAmount);
	}

	public int getBalance() {
		return balance;
	}

}
