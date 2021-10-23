import java.util.*;

public class RobotWalking {

	public int[][] bestWalks(int[][] grid) {
		if (grid.length == 0) {
			throw new IllegalArgumentException("grid is empty");
		}
		int[][] bestWalks = new int[grid.length][grid[0].length];
		String bestPath = "";

		// start with 1 cell as the sub problem, and save the best walk for that sub problem
		// increase sub problem by 1 cell, then save the best walk for the new cell based result from previous sub problems
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {

				int currentCell = grid[row][column];
				int cellAbove = Integer.MAX_VALUE;
				int leftCell = Integer.MAX_VALUE;

				if (row == 0 && column == 0) {
					bestWalks[row][column] = currentCell;
					continue;
				}

				if (rowAboveExist(row)) {
					cellAbove = bestWalks[row-1][column]; // Math.max in case it's look at negative cells
				}
				if (rowToTheLeftExist(column)) {
					leftCell = bestWalks[row][column-1];
				}
				
				System.out.println(String.format("[%d][%d] - best path above is: %d, and best path to the left is: %d", row, column, cellAbove, leftCell));
				System.out.println(String.format("Taking the smaller one %d so total cost is %d + %d = %d\n", 
					Math.min(cellAbove, leftCell), bestWalks[row][column], Math.min(cellAbove, leftCell), currentCell));
				
				bestWalks[row][column] = Math.min(cellAbove, leftCell) + currentCell;
			}
		}

		return bestWalks;
	}

	private boolean rowAboveExist(int rowNumber) {
		return rowNumber > 0;
	}

	private boolean rowToTheLeftExist(int colNumber) {
		return colNumber > 0;
	}

	public static void main(String[] args) {
		int[][] grid = {
			{1, 6, 1, 1},
			{4, 2, 4, 1},
			{3, 2, 5, 1}
		};

		RobotWalking rw = new RobotWalking();
		int[][] bestWalks = rw.bestWalks(grid);

		System.out.println("Grid: ");
		rw.print2D(grid);

		System.out.println("\nBest Costs:");
		rw.print2D(bestWalks);

		System.out.println("\n" + rw.walkBackward(grid, bestWalks));
		
	}

	private void print2D(int[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}

	private String walkBackward(int[][] grid, int[][] bestWalks) {
		int row = bestWalks.length-1;
		int column = bestWalks[0].length-1;

		String path = String.format("[%d][%d]", row+1, column+1);

		do {
			int previousBest = bestWalks[row][column] - grid[row][column];
			int topBest = bestWalks[Math.max(row-1, 0)][column];
			int leftBest = bestWalks[row][Math.max(column-1, 0)];

			System.out.println(String.format("previous best is %d, top is %d, left is %d\n", previousBest, topBest, leftBest));
			if (previousBest == topBest) {
				row--;
				path = String.format("[%d][%d] --> ", row+1, column+1) + path;
			} else if (previousBest == leftBest) {
				column--;
				path = String.format("[%d][%d] --> ", row+1, column+1) + path;
			} else { // to end loop
				row--;
				column--;
			}
		} while (row >= 0 && column >= 0);

		return path;
	}

}