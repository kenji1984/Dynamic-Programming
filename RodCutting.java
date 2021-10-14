import java.util.*;

/*
 * Rod Cutting - Dynamic Programming
 * The print statements helps understand the nature of dynamic programming but can be overwhelming.
 * Use smaller rod size with the print statement.
 */
public class RodCutting {
	
	/**
	 * @return Map containing the price for length of rod where the Key is the length of rod.
	 */
	private static Map<Integer, Double> getRodPrices() {
		Map<Integer, Double> rodPrices = new HashMap<>();
		rodPrices.put(2, 1.50);
		rodPrices.put(3, 2.00);
		rodPrices.put(4, 2.25);
		return rodPrices;
	}

	public static void highestPossibleBenefit(int rodLength) {
		Map<Integer, Double> rodPrices = getRodPrices();
		rodLength++; // code using base-0 so adding 1 extra length to rod because that's what user wants
		double[] bestSellingPrices = new double[rodLength];
		String[] cuts = new String[rodLength];

		for (int subLength = 0; subLength < rodLength; subLength++) {
			System.out.println("subLength: " + subLength);

			for (Integer cutLength : rodPrices.keySet()) {
				Double totalSold = 0.00;
				if (subLength >= cutLength) {
					System.out.println("subLength of " + subLength + " can be cut at " + cutLength + " and sold for at least " + rodPrices.get(cutLength));
					totalSold += rodPrices.get(cutLength);
				}
				int remainingRod = Math.max(0, subLength - cutLength); // prevent negative length when cutting small rods
				System.out.println("remaining length of " + remainingRod + " can be sold for " + bestSellingPrices[remainingRod]);
				totalSold += bestSellingPrices[remainingRod];

				if (totalSold > bestSellingPrices[subLength]) {
					cuts[subLength] = cutLength + (cuts[remainingRod] == null ? "" : " - " + cuts[remainingRod]);
					bestSellingPrices[subLength] = Math.max(totalSold, bestSellingPrices[subLength]);
				}
			}
		}
		System.out.println("Highest benefit at each sublength: " + Arrays.toString(bestSellingPrices));
		System.out.println("Best cut as each sublength: " + Arrays.toString(cuts));
		System.out.println("The last item in each of these array corresponds to the \"Answer\"");
	}

	public static void main(String[] args) {
		int rodSize = 10;
		RodCutting.highestPossibleBenefit(rodSize);
	}

}