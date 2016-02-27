package gomokuplay;

/**
 *
 * @author duytran
 */
import java.util.Scanner;

public class Human extends Agent {

	Scanner scan;

	public Human(int n, int m) {
		super(n, m);
		scan = new Scanner(System.in);
	}
        
        /**
	 * Get opponent's turn and update board
	 */
	String receiveTurn(String move) {
		board.placeMove('x', move, false);
		return move;
	}
        
	String takeTurn() {
		String move = pickMove();
		board.placeMove('o', move, true);
		System.out.println(board);
		return move;
	}

	String pickMove() {
            boolean invalid = true;
            String move = "";
            while(invalid){
		System.out.println("Please enter your move. e.g. "
				+ "A 2; F 5");
		move = scan.nextLine();
		if (board.getEmpties().contains(move)){
                        invalid = false;
			return move;
                }
                else {
                    System.out.println("Please enter a valid move!");
                    
                }
		//return "-1 -1";
            }
            return move;
	}

}
