package fizzbuzz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FizzBuzzTest {

	private int index;
	private String value;

	public FizzBuzzTest(int index, String value) {
		this.index = index;
		this.value = value;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1, "1" }, { 2, "2" },
				{ 3, "fizz" }, { 4, "4" }, { 5, "buzz" }, { 6, "fizz" },
				{ 7, "7" }, { 8, "8" }, { 9, "fizz" }, { 10, "buzz" },
				{ 15, "fizzbuzz" } });
	}

	@Test
	public void corectValueAtPositionInSequence() throws Exception {
		assertEquals(value,
				new FizzBuzzGenerator().generate().split(",")[index - 1]);
	}
}
