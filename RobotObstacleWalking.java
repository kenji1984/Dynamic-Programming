import java.util.*;

/**
 * Given a 2D matrix, containing value Xs and Os. O represents walkable and X represents Obstacle (not walkable). 
 * A Robot starts from the top left of the matrix needs to walk to the bottom right of the matrix. 
 * The Robot can only walk right or down. Find any path.
 */
public class RobotObstacleWalking {

	/*
	 * This can be solve with simple recursive call and undo the stack when encountering an obstacle but I'm going to use
	 * the same algorithm to prove it works. It's the same as the Lowest Cost Robot Walk problem.
	 */
	public Integer[][] obstacleCourse(String[][] grid) {
		if (grid.length == 0) {
			throw new IllegalArgumentException("grid is empty");
		}
		Integer[][] bestWalks = new Integer[grid.length][grid[0].length];

		// start with 1 cell as the sub problem, consider N as cost = 0 and O as cost = 1. Save the best walk for that sub problem
		// increase sub problem by 1 cell, then save the best walk for the new cell based result from previous sub problems
		// non-zero cost will be consider impossible (no path found).
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {

				int currentCell = grid[row][column] == "X" ? 1 : 0;
				int cellAbove = 1;
				int leftCell = 1;

				if (row == 0 && column == 0) {
					bestWalks[row][column] = currentCell;
					continue;
				}
				bestWalks[row][column] = 0;

				if (rowAboveExist(row)) {
					cellAbove = bestWalks[row-1][column]; // Math.max in case it's look at negative cells
				}
				if (rowToTheLeftExist(column)) {
					leftCell = bestWalks[row][column-1];
				}
				
				System.out.println(String.format("[%d][%d] - best path above is already examined: %d, and best path to the left is already examined: %d", 
						row, column, cellAbove, leftCell));
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
		String[][] grid = {
			{"O", "X", "O", "O"},
			{"O", "O", "X", "O"},
			{"X", "O", "X", "O"},
			{"X", "O", "O", "O"}
		};

		RobotObstacleWalking rw = new RobotObstacleWalking();
		Integer[][] bestWalks = rw.obstacleCourse(grid);

		System.out.println("Grid: ");
		rw.print2D(grid);

		System.out.println("\nBest Costs:");
		rw.print2D(bestWalks);

		int lastRow = bestWalks.length-1;
		int lastCol = bestWalks[lastRow].length-1;
		if (bestWalks[lastRow][lastCol] > 0) {
			throw new IllegalArgumentException("All path are obstructed. It's not possible to cross.");
		}
		System.out.println("\n" + rw.walkBackward(grid, bestWalks));
		
	}

	private void print2D(Object[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}

	private String walkBackward(String[][] grid, Integer[][] bestWalks) {
		int row = bestWalks.length-1;
		int column = bestWalks[0].length-1;

		String path = String.format("[%d][%d]", row+1, column+1);

		do {
			int topBest = row == 0 ? 1 : bestWalks[row-1][column];
			int leftBest = column == 0 ? 1: bestWalks[row][column];

			//System.out.println(String.format("[%d][%d] - Looking for path with 0 costs - top is %d, left is %d\n", row, column, topBest, leftBest));
			if (topBest == 0) {
				row--;
				path = String.format("[%d][%d] --> ", row+1, column+1) + path;
			} else if (leftBest == 0) {
				column--;
				path = String.format("[%d][%d] --> ", row+1, column+1) + path;
			} else { // to force end loop
				row--;
				column--;
			}
		} while (row >= 0 && column >= 0);

		return path;
	}

}