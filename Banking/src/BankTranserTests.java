import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BankTranserTests {

	private static final int initialBalance = 100;

	private BankAccount payer;
	private BankAccount payee;

	@Before
	public void setUp() {

		payer = new BankAccount();
		payee = new BankAccount();

		payer.credit(initialBalance);
	}

	@Test
	@Parameters({ "99", "50", "75", "100" })
	public void transferTest(int amount) {

		payer.transfer(payee, amount);

		assertEquals(initialBalance - amount, payer.getBalance());
		assertEquals(amount, payee.getBalance());
	}
}
