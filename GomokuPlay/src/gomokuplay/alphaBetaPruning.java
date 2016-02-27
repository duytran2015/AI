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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class alphaBetaPruning {

    boolean cutOffTest(long start){
        long current = System.currentTimeMillis();
        //System.out.println(current - start);
        if (current-start <= 5000) return false;
        else return true;
    }
    
    String makeMove(Board board, int d){
        int best = Integer.MAX_VALUE;
        long start = System.currentTimeMillis();
        ArrayList<String> moveList;
        Set<String> moves =  new HashSet<String>();
        String bestMove = "";
        
        // generate all possible moves
        ArrayList<String> places = board.getPlayerPlaces(board.prevPlayer);
        for (int i = 0; i < places.size(); i++) {
                moves.addAll(board.lookAround(places.get(i)));
        }
        moves.retainAll(board.getEmpties());
        
        if (moves.isEmpty())
                moveList =  new ArrayList<String>(board.getEmpties());
        else
                moveList =  new ArrayList<String>(moves);
        
        // alpha beta pruning to find best move
        while(moveList.size() > 0){
            int depth = d;
            Board newBoard = new Board(board);
            String move = moveList.get(0);
            newBoard.placeMove(newBoard.nextPlayer, move, false);
            int score = Min_Value(newBoard, depth-1, start,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (score < best){
                    bestMove = move;
                    best = score;
            }
            moveList.remove(0);
        }
        return bestMove;
    }
        
    int Max_Value(Board board, int d, long startTime, 
            int alpha, int beta){
        
        int bestScore = Integer.MIN_VALUE;
        Object[] temp;
        int tempScore;
        String bestmove = "";
        ArrayList<String> moveList;
        Set<String> moves =  new HashSet<String>();
        
        if (d == 0 || cutOffTest(startTime)) {
                return evaluate(board);
        } 
        
        ArrayList<String> places = board.getPlayerPlaces(board.prevPlayer);
        for (int i = 0; i < places.size(); i++) {
                moves.addAll(board.lookAround(places.get(i)));
        }
        moves.retainAll(board.getEmpties());
        // make sure that moves is not empty
        // otherwise, pick from list of empty locations
        if (moves.isEmpty())
                moveList =  new ArrayList<String>(board.getEmpties());
        else
                moveList =  new ArrayList<String>(moves);

        
        bestScore = alpha;
        while (moveList.size() > 0) {
                Board newBoard =  new Board(board);
                String move = moveList.get(0);
                newBoard.placeMove(newBoard.nextPlayer, move, false);
                bestScore = Math.max(bestScore, 
                        Min_Value(newBoard, d - 1, startTime, alpha, beta));
                
                if (bestScore >= beta) {
                        return bestScore;
                }
                alpha = Math.max(alpha, bestScore);
                moveList.remove(0);
        }
        return bestScore;
    }

    int Min_Value(Board board, int d, long startTime, int alpha, int beta) {

        int worstScore = Integer.MAX_VALUE;
        ArrayList<String> moveList;
        Set<String> moves =  new HashSet<String>();
        
        if (d == 0 || cutOffTest(startTime)) {
                return evaluate(board);
        }
        
        ArrayList<String> places = board.getPlayerPlaces(board.prevPlayer);
        for (int i = 0; i < places.size(); i++) {
                moves.addAll(board.lookAround(places.get(i)));
        }
        moves.retainAll(board.getEmpties());
        // make sure that moves is not empty
        // otherwise, pick from list of empty locations
        if (moves.isEmpty())
                moveList =  new ArrayList<String>(board.getEmpties());
        else
                moveList =  new ArrayList<String>(moves);
        
        worstScore = beta;
        while (moveList.size() > 0) {
                Board newBoard = new Board(board);
                String move = moveList.get(0);
                newBoard.placeMove(newBoard.nextPlayer, move, false);
                worstScore = Math.min(worstScore, 
                        Max_Value(newBoard, d - 1, startTime, alpha, beta));
                
                if (worstScore <= alpha) {
                        return worstScore;
                }
                beta = Math.min(beta, worstScore);
                moveList.remove(0);
        }
        return worstScore;
    }
    
    /**
	 * Evaluation function
	 * @param  board node to evaluate
	 * @return score of board
	 */
	int evaluate(Board board) {
		int oneAway = board.nearWins(board.nextPlayer, 1);
		int twoAway = board.nearWins(board.nextPlayer, 2);
		int threeAway = board.nearWins(board.nextPlayer, 3);
		int score = oneAway * 1000 + twoAway * 100 + threeAway * 1;
		return score;
	}
        
        
}

        
