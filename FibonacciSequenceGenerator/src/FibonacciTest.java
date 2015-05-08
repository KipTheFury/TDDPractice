import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FibonacciTest {

	private FibonacciGenerator generator;

	@Before
	public void setUp() {
		generator = new FibonacciGenerator();
	}

	@Test
	@Parameters({ "0,0", "1,1", "2,1", "3,2", "4,3", "5,5", "6,8" })
	public void numberInSequenceIsCorrect(int position, int value) {

		int[] sequence = generator.generateSequence(8);
		assertEquals(value, sequence[position]);
	}

	@Test(expected = RuntimeException.class)
	public void cannotGenerateSequenceShorterThan8() throws Exception {
		generator.generateSequence(7);
	}

	@Test(expected = RuntimeException.class)
	public void cannotGenerateSequenceLongerThan50() throws Exception {
		generator.generateSequence(51);
	}
}
