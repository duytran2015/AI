/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzlegame;

import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzleState implements State {
	private final int PUZZLE_SIZE = 9;
	private int outOfPlace = 0;
	private int manDist = 0;
	private final int[] GOAL = new int[]
	{ 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	private int[] curBoard;

	// Constructor for EightPuzzleState
	public EightPuzzleState(int[] board)
	{
		curBoard = board;
		setOutOfPlace();
		setManDist();
	}

	@Override
	public int findCost()
	{
		return 1;
	}

	// Find the 'tiles out of place' distance for the current board
	private void setOutOfPlace()
	{
		for (int i = 0; i < curBoard.length; i++)
		{
			if (curBoard[i] != GOAL[i])
			{
				outOfPlace++;
			}
		}
	}

	// Find the Manhattan Distance for the current board
	private void setManDist() {
            for (int i = 0; i < 9; i++) {
                manDist += Math.abs(curBoard[i]/3-i/3) 
                        + Math.abs(curBoard[i]%3 - i%3);
            }
        }

	// Find the "0" spot on the current board return its index
	private int getHole()
	{
		int holeIndex = -1;

		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			if (curBoard[i] == 0)
				holeIndex = i;
		}
		return holeIndex;
	}

	// Get out of place value
	public int getOutOfPlace()
	{
		return outOfPlace;
	}

	// Get Manhattan Distance value
	public int getManDist()
	{
		return manDist;
	}

	// Makes a copy of the array passed to it
	private int[] copyBoard(int[] state)
	{
		int[] ret = new int[PUZZLE_SIZE];
		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			ret[i] = state[i];
		}
		return ret;
	}

	// "0" tiles from 4 directions if it is in middle; 
	// two directions if it is at corner; 
	// three directions if it is in middle of a row
	@Override
	public ArrayList<State> genSuccessors()
	{
		ArrayList<State> successors = new ArrayList<State>();
		int hole = getHole();

		// try to generate a state by sliding a tile left into the hole
		if (hole != 0 && hole != 3 && hole != 6)
		{
			slideTile(hole - 1, hole, successors);
		}

		// try to generate a state by sliding a tile up into the hole
		if (hole != 6 && hole != 7 && hole != 8)
		{
			slideTile(hole + 3, hole, successors);
		}

		// try to generate a state by sliding a tile down into the hole
		if (hole != 0 && hole != 1 && hole != 2)
		{
			slideTile(hole - 3, hole, successors);
		}
		// try to generate a state by sliding a tile right into the hole
		if (hole != 2 && hole != 5 && hole != 8)
		{
			slideTile(hole + 1, hole, successors);
		}

		return successors;
	}

	/*
	 * Switches the data at indices d1 and d2, in a copy of the current board
	 * creates a new state based on this new board and pushes into s.
	 */
	private void slideTile(int d1, int d2, ArrayList<State> s)
	{
		int[] cpy = copyBoard(curBoard);
		int temp = cpy[d1];
		cpy[d1] = curBoard[d2];
		cpy[d2] = temp;
		s.add((new EightPuzzleState(cpy)));
	}

	// Check to see if the current state is the goal state.
	@Override
	public boolean isGoal()
	{
		if (Arrays.equals(curBoard, GOAL))
		{
			return true;
		}
		return false;
	}

	// print out the current state. Prints the puzzle board.
	@Override
	public void printState()
	{
		System.out.println(curBoard[0] + " | " + curBoard[1] + " | "
				+ curBoard[2]);
		System.out.println("---------");
		System.out.println(curBoard[3] + " | " + curBoard[4] + " | "
				+ curBoard[5]);
		System.out.println("---------");
		System.out.println(curBoard[6] + " | " + curBoard[7] + " | "
				+ curBoard[8]);

	}

	// Compare two states whether they are equal
	@Override
	public boolean equals(State s)
	{
		if (Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard()))
		{
			return true;
		}
		else
			return false;

	}

	// get current board 
	public int[] getCurBoard()
	{
		return curBoard;
	}

}
