public class FibonacciGenerator {

	public int[] generateSequence(int length) {

		if (length < 8 || length > 50) {
			throw new RuntimeException();
		}

		int[] sequence = new int[length];

		for (int i = 0; i < length; i++) {

			if (i > 1) {
				sequence[i] = sequence[i - 1] + sequence[i - 2];
			} else {

				sequence[i] = i;
			}
		}

		return sequence;
	}
}
