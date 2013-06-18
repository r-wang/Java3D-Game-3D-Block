package blocksGame;

import java.util.*;

/**
 * Level stores the definition and progress of a level of the game
 * 
 * @author Ruiqi Wang
 * 
 */
public class Level {

	private Blocks blocks = new Blocks();

	/*
	 * Level definitions
	 */
	private static int[][] level1 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 0, 2, 1, 1, 1, 1 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	private static int[][] level2 = { { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 1, 0, 0, 1, 1, 1, 2, 1, 1, 1, 1, 0 },
			{ 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0 },
			{ 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 } };

	private static int[][] level3 = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
			{ 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 2, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
			{ 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0 },
			{ 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0 } };

	private static int[][] level4 = { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	private static int[][] level5 = { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 } };
	private static int[] steps = { 200, 200, 200, 40, 30 };
	private static int[] times = { 55, 55, 50, 40, 20 };

	private static int[][][] levels = { level1, level2, level3, level4, level5 };

	private int maxSteps;

	private int maxTime;

	/**
	 * Gets max steps for a level
	 * 
	 * @return Max steps for a level
	 */
	public int getMaxSteps() {
		return maxSteps;
	}

	/**
	 * Gets blocks composing the board for a level
	 * 
	 * @return Blocks composing the board for a level
	 */
	public Blocks getBlocks() {
		return blocks;
	}

	/**
	 * Gets max time granted for a level
	 * 
	 * @return Max time granted for a level
	 */
	public int getMaxTime() {
		return maxTime;
	}

	/**
	 * Loads the data of a specific level
	 * 
	 * @param level
	 *            The level to be loaded
	 */
	public void load(int level) {
		level--;
		int[][] board = this.levels[level];

		blocks = new Blocks();
		// Iterator iterator = blocks.getIterator();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 1) {
					Block block = new Block(i, j);
					block.setLandedOn(false);
					blocks.addBlock(block);
				}

				if (board[i][j] == 2) {
					Block block = new Block(i, j);
					block.updateBoxGridColor();
					blocks.addBlock(block);
					block.setRolledOver(true);
					block.setLandedOn(true);

				}
			}
		}
		// max time, current time, winning color,
		maxSteps = steps[level];
		maxTime = times[level];

	}

}
