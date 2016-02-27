/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzlegame;

import java.util.Scanner;
import java.util.Random;
import java.util.Formatter;
/**
 *
 * @author duytran
 */
public class EightPuzzleGame { 
   public static void main(String[] args) {
        boolean choice = true;
        Scanner input = new Scanner(System.in);
        int h;
        String option;
        String line;
        
        while (choice){
            System.out.println("1: input your 8 puzzle game.");
            System.out.println("2: randomize your 8 puzzle game.");
            System.out.println("3: exit puzzle");
            System.out.print("Input your option: ");
            h = input.nextInt();
            switch(h){
                case 1: System.out.println("Enter your 8 puzzle: ");
                        int[] board1 = inputEightPuzzle();
                        if(isSolvable(board1)){
                            System.out.println("A* Search with out of place heuristic function");
                            AStarSearch.search(board1, 'o');
                            System.out.println("A* Search with Manhattan distance heuristic function");
                            AStarSearch.search(board1, 'm');
                        }
                        else { 
                            System.out.print("Can not solve this puzzle!");
                        }
                        System.out.print("Continue?(y/n)");
                        switch (input.next()){
                            case "n":   choice = false;
                                        break;
                            case "y":   break;
                            default :   System.out.println("Invalid input!");
                                        choice = false;
                                        break;
                        }
                        break;
                
                case 2: for(int j = 0; j < 100; j++){
                        int[] board2 = randomEightPuzzle();
                        if(isSolvable(board2)){
                            System.out.println(j + "A* Search with out of place heuristic function:");
                            AStarSearch.search(board2, 'o');
                            System.out.println("\n" + j + "A* Search with Manhattan distance heuristic function:");
                            AStarSearch.search(board2, 'm');
                        }
                        else { 
                            System.out.print("Can not solve this puzzle!");
                        }
                        /*
                        System.out.print("Continue?(y/n)");
                        switch (input.next()){
                            case "n":   choice = false;
                                        break;
                            case "y":   break;
                            default :   System.out.println("Invalid input!");
                                        choice = false;
                                        break;
                        }*/
                        }
                        break;
                
                case 3: choice = false;
                        break;
                
                default:System.out.print("Please input 1, 2, or 3!");
                        break;
            }
        }
    }
   
   private static int[] inputEightPuzzle() {
        Scanner input = new Scanner(System.in);
        int[] inputBoard = new int[9];
	for (int i = 0; i < 9; i++) {
            inputBoard[i] = input.nextInt();
	}
	return inputBoard;
    }
   
   private static int[] randomEightPuzzle(){
        int[] board = new int[]{0,1,2,3,4,5,6,7,8};
        Random random = new Random();
        do {
            for (int i = 0; i < board.length; i++) {
                int j = random.nextInt(board.length);
                int x = board[i];
                board[i] = board[j];
                board[j] = x;
            }
        } while (!isSolvable(board));
        return board;
    }
   
   public static boolean isSolvable(int[] tiles) {
        boolean solvable = true;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == 0) continue;
            for (int j = i + 1; j < tiles.length; j++) {
                if (tiles[j] == 0) continue;
                if (tiles[i] > tiles[j]) solvable = !solvable;
            }
        }
            return solvable;
    }
}
   
    
    
    

