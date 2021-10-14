import java.util.*;

/*
 * Largest Increasing Sequence - Dynamic Programming
 */
public class LIS {

	public static Stack<Integer> getSequence(int[] inputs) {
		int[] subProblemLisCounts = new int[inputs.length];
		int largestSequenceCount = 0;
		
		for (int newIdx = 0; newIdx < inputs.length; newIdx++) {
			for (int oldIdx = 0; oldIdx < newIdx; oldIdx++) {
				// update the largest sequence when new number is added based on all previous largest sequences
				if (inputs[oldIdx] < inputs[newIdx]) {
					subProblemLisCounts[newIdx] = subProblemLisCounts[oldIdx] + 1;
					largestSequenceCount = Math.max(largestSequenceCount, subProblemLisCounts[newIdx]);
				}
			}
		}
		System.out.println("Input array: " + Arrays.toString(inputs));
		System.out.println("LIS of sub problems: " + Arrays.toString(subProblemLisCounts) + " with largest sequence count of : " + largestSequenceCount);
		Stack<Integer> longestSequence = getTheSequence(inputs, subProblemLisCounts, largestSequenceCount);
		return longestSequence;
	}

	private static Stack<Integer> getTheSequence(int[] inputs, int[] subProblemLis, int largestSequenceCount) {
		Stack<Integer> stack = new Stack<>();

		for (int i = subProblemLis.length-1; i >= 0; i--) {
			if (largestSequenceCount >= 0 && subProblemLis[i] == largestSequenceCount) {
				if (stack.isEmpty()) {
					stack.push(inputs[i]);
				} else if (stack.peek() > inputs[i]) {
					stack.push(inputs[i]);
				}
				largestSequenceCount--;
			}
		}
		return stack;
	}

	private static String stackToString(Stack<Integer> stack) {
		if (stack.size() == 1) {
			return String.valueOf(stack.pop());
		}
		String s = String.valueOf(stack.pop()) + ", ";
		return s + stackToString(stack);
	}

	public static void main(String[] args) {
		int[] inputs = { 1, 7, 10, 3, 4, 5, 100, 6, 11, 8, 2 };
		Stack<Integer> longestSequence = LIS.getSequence(inputs);
		System.out.println(stackToString(longestSequence));
	}

}