package fizzbuzz;

public class FizzBuzzGenerator {

	public String generate() {

		String sequence = "";

		for (int i = 1; i <= 100; i++) {

			if (i % 3 == 0 && i % 5 == 0) {
				sequence += "fizzbuzz,";
			} else if (i % 3 == 0) {
				sequence += "fizz,";
			} else if (i % 5 == 0) {
				sequence += "buzz,";
			} else {
				sequence += i + ",";
			}
		}

		return sequence;
	}
}
