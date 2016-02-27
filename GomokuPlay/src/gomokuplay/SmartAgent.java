package gomokuplay;

/**
 *
 * @author duytran
 */

public class SmartAgent extends Agent {

	alphaBetaPruning AImove;

	public SmartAgent(int n, int m) {
		super(n, m);
		AImove = new alphaBetaPruning();
	}

	String firstTurn() {
		// pick default first move
		String move = "D 3";
		board.placeMove('x', move, true);
		System.out.println(board);
		return move;
	}
        
        /**
	 * Get opponent's turn and update board
	 */
	String receiveTurn(String move) {
		board.placeMove('o', move, false);
		return move;
	}

	String takeTurn() {
		String move = pickMove();
		board.placeMove('x', move, true);
		System.out.println(board);
		return move;
	}

	String pickMove() {
		String move = AImove.makeMove(board, 5);
		return move;
	}
}
