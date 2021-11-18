import java.util.*;

/*
 * Bob and Sue pick a number x where 0 < x < n. After each pick, n = n - x.
 * Whoever has no more number to pick loses.
 */
public class DivisorGame {

	public static int[] play(int n) {
		// array holding # of moves to win. Odd means first person can win. Even means no chance of winning.
		int[] movesToWin = new int[n+1];

		for (int subN = 2; subN <= n; subN++) {
			for (int x = 1; x < subN; x++) { // 0 < x < subN
				if (subN % x != 0) {
					continue;
				}

				System.out.print("SubN: " + subN + ". Picking " + x + " leaves " + (subN-x) + ". We know movesToWin[" + (subN-x) + "] = " + movesToWin[subN-x]);
				System.out.println(", therefore it is " + (movesToWin[subN-x] % 2 == 0 ? "impossible" : "possible") + " for other person to win.");

				if (movesToWin[subN] == 0) {
					movesToWin[subN] = 1 + movesToWin[subN - x];
				} else {
					movesToWin[subN] = pickSmallerOddNum(movesToWin[subN], 1 + movesToWin[subN - x]);
				}
			}
			System.out.println();
		}
		return movesToWin;
	}

	private static int pickSmallerOddNum(int num1, int num2) {
		if (num1 % 2 == 0 && num2 % 2 == 1) {
			return num2;
		}
		if (num1 % 2 == 1 && num2 % 2 == 0) {
			return num1;
		}
		return Math.min(num1, num2);
	}

	public static void main(String[] args) {
		int[] outcomes = DivisorGame.play(100);
	/*
		int[] forVisualization = new int[11];
		for (int i = 0; i <= 10; i++) {
			forVisualization[i] = i;
		}
		System.out.println("SubN: " + Arrays.toString(forVisualization));*/
		System.out.println("Best: " + Arrays.toString(outcomes));

		/*
			if outcomes[n] == odd, then first player can win in outcomes[n] moves.
			To get the exact simulation of how the game is played out, backtrack by find outcomes[x] such that outcome[x] = outcome[n]-1.
			The index of that item is the remaining number after a pick.
		*/
	}

}