package gomokuplay;

/**
 *
 * @author duytran
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Board {
	char[][] board;
	int n;
	int m;
	char nextPlayer;
	char prevPlayer;
	char winner;
	String lastMove;

	/**
	 * Board constructor
	 */
	public Board(int n, int m) {
		this.n = n;
		this.m = m;
		this.board = new char[n][n];
		// fill char[][] with empty spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = '.';
			}
		}
		this.winner = '.';
	}

	/**
	 * Copy constructor for Board
	 */
	public Board(Board other) {
		this.n = other.n;
		this.m = other.m;
		this.board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
		this.nextPlayer = other.nextPlayer;
		this.prevPlayer = other.prevPlayer;
	}

	/**
	 * Get set of empty spots on board
	 */
	Set<String> getEmpties() {
		Set<String> ems = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					ems.add((char)(i+65) + " " + (j+1));
				}

			}
		}
		return ems;
	}

	/**
	 * Place a move on the board
	 */
	String placeMove(char p, String move, boolean out) {
		int[] ij = parseMove(move);
		board[ij[0]][ij[1]] = p;
		if (out)
			System.out.println(move);
		prevPlayer = p;
		// sets next and prev player
		if (p == 'x')
			nextPlayer = 'o';
		else
			nextPlayer = 'x';
		winner = setWinner(p, ij);
		return move;
	}

	/**
	 * Get set of all player's pieces
	 */
	ArrayList<String> getPlayerPlaces(char p) {
		ArrayList<String> places = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == p) {
					places.add((char)(i+65) + " " + (j+1));
				}
			}
		}
		return places;
	}

	/**
	 * Look for empty spots around a location
	 */
	ArrayList<String> lookAround(String pos) {
		ArrayList<String> adjacent = new ArrayList<String>();
		int[] coords = parseMove(pos);
		int i = coords[0];
		int j = coords[1];
		String x;
		if (i - 1 >= 0) {
			if (board[i - 1][j] == '.'){
				x = strMove(i - 1, j);
				adjacent.add(x);
			}
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1] == '.'){
					x = strMove(i - 1, j - 1);
					adjacent.add(x);
				}
			}
                        if (j + 1 < n) {
                                x = strMove(i - 1, j + 1);
                                adjacent.add(x);
                        }
		}
                if (j - 1 >= 0) {
                        if (board[i][j-1] == '.'){
                                x = strMove(i, j - 1);
                                adjacent.add(x);
                        }
                }
                if (j + 1 < n){
                        if (board[i][j+1] == '.'){
                                x = strMove(i, j + 1);
                                adjacent.add(x);
                        }   
                }
		
		if (i + 1 < n) {
			if (board[i + 1][j] == '.') {
				x = strMove(i + 1, j);
				adjacent.add(x);
			}
			if (j + 1 < n) {
				if (board[i + 1][j + 1] == '.') {
					x = strMove(i + 1, j - 1);
					adjacent.add(x);
				}
			}
                        if (j - 1 >= 0){
                                if (board[i + 1][j - 1] == '.') {
					x = strMove(i + 1, j - 1);
					adjacent.add(x);
				}
                        }
		}
		return adjacent;
	}

	/**
	 * Set the board's winner
	 */
	char setWinner(char p, int[] ij) {
		if (isRowWin(p, ij) || isColWin(p, ij)) {
			return p;
		}
		if (getEmpties().isEmpty()) {
			return 'd';
		}
		return '.';
	}

	/**
	 * Check row of last move for win
	 */
	boolean isRowWin(char p, int[] ij) {
		String row = new String(board[ij[0]]);
		if (row.contains(strMatch(p)))
			return true;
		return false;
	}

	/**
	 * Check column of last move for win
	 */
	boolean isColWin(char p, int[] ij) {
		String column = "";
		for (int i = 0; i < n; i++) {
			column += board[i][ij[1]];
		}
		if (column.contains(strMatch(p)))
			return true;
		return false;
	}
        /**
	 * Calculate number of near win chains
	 */
	int nearWins(char p, int away) {
		return nearWinRows(p, away) + nearWinCols(p, away);
	}

	/**
	 * Calculate number of near win chains in rows
	 */
	int nearWinRows(char p, int away) {
		int count = 0;
		int length = m - away;
                char q;
                if (p == 'o') q = 'x';
                else q = 'o';
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
                String match3 = q + match1;
                String match4 = match1 + q;
                String match5 = q + match1 + q;
		match1 += '.';
		for (int i = 0; i < n; i++) {
			String row = new String(board[i]);
			if (row.contains(match1)) {
				int x = row.indexOf(match1);
				while (x >= 0) {
					count++;
					x = row.indexOf(match1, match1.length() + x);
				}
			}
			if (row.contains(match2)) {
				int x = row.indexOf(match2);
				while (x >= 0) {
					count++;
					x = row.indexOf(match2, match2.length() + x);
				}
			}
                        if (row.contains(match5) && away == 1){
                                int x = row.indexOf(match5);
				while (x >= 0) {
                                    count--;
                                    x = row.indexOf(match5, match5.length() + x);
				}
                        }
                        if (row.contains(match3)){
                                int x = row.indexOf(match3);
				while (x >= 0) {
                                    count--;
                                    x = row.indexOf(match3, match3.length() + x);
				}
                        }
                        if (row.contains(match4)){
                                int x = row.indexOf(match4);
				while (x >= 0) {
                                    count--;
                                    x = row.indexOf(match4, match4.length() + x);
				}
                        }
		}
		return count;
	}

	/**
	 * Calculate number of near win chains in columns
	 */
	int nearWinCols(char p, int away) {
		int count = 0;
		int length = m - away;
		char q;
                if (p == 'o') q = 'x';
                else q = 'o';
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
                String match3 = q + match1;
                String match4 = match1 + q;
                String match5 = q + match1 + q;
		match1 += '.';
		for (int j = 0; j < n; j++) {
			String column = "";
			for (int i = 0; i < n; i++) {
				column += board[i][j];
			}
			if (column.contains(match1)) {
				int x = column.indexOf(match1);
				while (x >= 0) {
					count++;
					x = column.indexOf(match1, match1.length() + x);
				}
			}
			if (column.contains(match2)) {
				int x = column.indexOf(match2);
				while (x >= 0) {
					count++;
					x = column.indexOf(match2, match2.length() + x);
				}
			}
                        if (column.contains(match5) && away == 1){
                                int x = column.indexOf(match5);
				while (x >= 0) {
                                    count--;
                                    x = column.indexOf(match5, match5.length() + x);
				}
                        }
                        if (column.contains(match3)){
                                int x = column.indexOf(match3);
				while (x >= 0) {
                                    count--;
                                    x = column.indexOf(match3, match3.length() + x);
				}
                        }
                        if (column.contains(match4)){
                                int x = column.indexOf(match4);
				while (x >= 0) {
                                    count--;
                                    x = column.indexOf(match4, match4.length() + x);
				}
                        }
		}

		return count;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 */
	String strMatch(char p) {
		String match = "";
		for (int i = 0; i < m; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 */
	String strMatch(char p, int length) {
		String match = "";
		for (int i = 0; i < length; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	/**
	 * Parse move from a string into int[]
	 */
	int[] parseMove(String s) {
		String[] ss = s.split(" ");
		int[] ij = { (int)(ss[0].charAt(0))-65, Integer.parseInt(ss[1])-1 };
		return ij;
	}

	/**
	 * Create string move from coordinates
	 * @param  i row index
	 * @param  j column index
	 * @return move string
	 */
	String strMove(int i, int j) {
		return (char)(i+65) + " " + (j+1);
	}

	/**
	 * Create string move from int[] coordinates
	 */
	String strMove(int[] ij) {
		return (char)(ij[0]+65) + " " + (ij[1]+1);
	}

	/**
	 * Print a nice board
         */ 
	@Override
	public String toString() {
		String str = "";
                str += "  1 2 3 4 5 6 7 8\n";
		for (int i = 0; i < n; i++) {
                        str += (char)(i+65) + " ";
			for (int j = 0; j < n; j++) {
				str += board[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

}
