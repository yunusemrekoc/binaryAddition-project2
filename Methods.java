package binaryAddition2;

public class Methods {

	public long binaryAddition(long binary1, long binary2) {
		if (binary2 == 0)
			return binary1;
		long total = binary1 ^ binary2; // SUM of two integer is A XOR B
		long cry = (binary1 & binary2) << 1; // CARRY of two integer is A AND B
		return binaryAddition(total, cry);
	}

	public long binarySubtraction(long binary1, long binary2) {
		return binaryAddition(binary1, binaryAddition(~binary2, 1));
	}

	public long binaryDivision(long binaryDividend, long binaryDivisor) {
		boolean negative = false;
		if ((binaryDividend & (1 << 31)) == (1 << 31)) {
			negative = !negative;
			binaryDividend = binaryAddition(~binaryDividend, 1);
		}
		if ((binaryDivisor & (1 << 31)) == (1 << 31)) {
			negative = !negative;
			binaryDivisor = binaryAddition(~binaryDivisor, 1);
		}
		long binaryQuotient = 0;
		long t;
		for (long i = 30; i >= 0; i = binarySubtraction(i, 1)) {
			t = (binaryDivisor << i);

			if (t < Long.MAX_VALUE && t >= 0) {
				if (t <= binaryDividend) {
					binaryQuotient |= (1 << i);
					binaryDividend = binarySubtraction(binaryDividend, (int) t);
				}
			}
		}
		if (negative) {
			binaryQuotient = binaryAddition(~binaryQuotient, 1);
		}
		return binaryQuotient;
	}
}
