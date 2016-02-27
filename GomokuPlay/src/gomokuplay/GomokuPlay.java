package gomokuplay;

/**
 *
 * @author duytran
 */
import java.util.Scanner;

public class GomokuPlay {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 8; // size of board
		int m = 4; // length of winning chain
                
		// human vs smart agent
                System.out.println("Would you like to go first or second? Please enter 1 or 2");
                int turn = Integer.parseInt(scan.nextLine());
                if (turn == 1) {
                        Human o = new Human(n, m);
                        System.out.println(o.board);
                        SmartAgent x = new SmartAgent(n, m);
                        String xMove, oMove = "";
                        oMove = o.takeTurn();
                        System.out.println(oMove);
                        boolean play = true;
                        while (play) {
                                x.receiveTurn(oMove);
                                xMove = x.takeTurn();
                                if (x.board.winner == 'x') {
                                        System.out.println("Winner: x");
                                        play = false;
                                        break;
                                }
                                if (x.board.winner == 'o') {
                                        System.out.println("Winner: o");
                                        play = false;
                                        break;
                                }
                                if (x.board.winner == 'd') {
                                        System.out.println("Winner: d");
                                        play = false;
                                        break;
                                }
                                o.receiveTurn(xMove);
                                oMove = o.takeTurn();
                                if (o.board.winner == 'x') {
                                        System.out.println("Winner: x");
                                        play = false;
                                        break;
                                }
                                if (o.board.winner == 'o') {
                                        System.out.println("Winner: o");
                                        play = false;
                                        break;
                                }
                                if (o.board.winner == 'd') {
                                        System.out.println("Winner: d");
                                        play = false;
                                        break;
                                }
                        }

                } else {
                        SmartAgent x = new SmartAgent(n, m);
                        Human o = new Human(n, m);
                        String xMove, oMove = "";
                        xMove = x.firstTurn();
                        System.out.println(xMove);
                        boolean play = true;
                        while (play) {
                                o.receiveTurn(xMove);
                                oMove = o.takeTurn();
                                if (o.board.winner == 'x') {
                                        System.out.println("Winner: x");
                                        play = false;
                                        break;
                                }
                                if (o.board.winner == 'o') {
                                        System.out.println("Winner: o");
                                        play = false;
                                        break;
                                }
                                if (o.board.winner == 'd') {
                                        System.out.println("Winner: d");
                                        play = false;
                                        break;
                                }
                                x.receiveTurn(oMove);
                                xMove = x.takeTurn();
                                if (x.board.winner == 'x') {
                                        System.out.println("Winner: x");
                                        play = false;
                                        break;
                                }
                                if (x.board.winner == 'o') {
                                        System.out.println("Winner: o");
                                        play = false;
                                        break;
                                }
                                if (x.board.winner == 'd') {
                                        System.out.println("Winner: d");
                                        play = false;
                                        break;
                                }
                        }
                }
		
        }
}