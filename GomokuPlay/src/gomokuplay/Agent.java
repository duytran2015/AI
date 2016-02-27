/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuplay;

/**
 *
 * @author duytran
 */
/**
 * Agent class that is extended by other players
 */
public class Agent {
	Board board;
	boolean isFirst;

	/**
	 * Agent constructor
	 */
	public Agent(int n, int m) {
		this.board = new Board(n, m);
	}

	/**
	 * Execute a turn
         */
	String takeTurn(String move) {
		return null;
	}

	/**
	 * Get the board's winner
	 */
	char getWinner() {
		return board.winner;
	}
}
