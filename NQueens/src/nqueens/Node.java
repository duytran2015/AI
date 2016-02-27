/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

/**
 *
 * @author duytran
 */
import java.util.*;

public class Node implements Comparable<Node>{
	private static final int N=17; //17 queens
	public Queen[] state; //the node's state
	private ArrayList<Node> neighbors;
	private int hn; //heuristic score
	
	/**
	 * Constructor
	 */
	public Node(){
		state = new Queen[N]; //empty state
		neighbors = new ArrayList<Node>(); //empty neighbor list
	} 
	
	/**
	 * Constructor which creates a copy of a node's state
	 */
	public Node(Node n){
		state = new Queen[N];
		neighbors = new ArrayList<Node>();
		for(int i=0; i<N; i++)
			state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
		hn=0;
	}
	
	/**
	 * Generates the possible chess board configurations given a
	 * specific board state
	 */
	public ArrayList<Node> generateNeighbors(Node startState){
		int count=0;
		
		if(startState==null)
			System.out.println("warning");
		
		for(int i=0; i<N; i++){
			for(int j=1; j<N; j++){
				neighbors.add(count, new Node(startState));
				neighbors.get(count).state[i].moveDown(j);
				//make sure to compute its hn value
				neighbors.get(count).computeHeuristic();
				
				count++;
			}
		}
		
		return neighbors;
	}
	
	/**
	 * Returns a randomly generated neighbor of a given state
	 */
	public Node getRandomNeighbor(Node startState){
		Random gen = new Random();
		
		int col = gen.nextInt(N);
		int d = gen.nextInt(N-1)+1;
		
		Node neighbor = new Node(startState);
		neighbor.state[col].moveDown(d);
		neighbor.computeHeuristic();
		
		return neighbor;
	}
	
	/**
	 * computes the heuristic, which is the number of 
	 * pieces that can attack each other
	 */
	public int computeHeuristic(){
	
		for(int i=0; i<N-1; i++){
			for(int j=i+1; j<N; j++){
				if(state[i].canAttack(state[j])){
						hn++;
				}
			}
		}
		
		return hn;
	}
	

	
	/**
	 * Heuristic getter
	 */
	public int getHeuristic(){
		return hn;
	}
	
	/**
	 * Implements Comparable method compareTo, judges a comparison
	 * on the basis of a Node's heuristic value
	 */
	public int compareTo(Node n){
		if(this.hn < n.getHeuristic())
			return -1;
		else if(this.hn > n.getHeuristic())
			return 1;
		else 
			return 0;
	}
	
	/**
	 * state setter
	 */
	public void setState(Queen[] s){
		for(int i=0; i<N; i++){
			state[i]= new Queen(s[i].getRow(), s[i].getColumn());
		}
	}
	
	
	/**
	 * state getter
	 */
	public Queen[] getState(){
		return state;
	}
	
	/**
	 * toString method prints out Node's state
	 */
	public String toString(){
		String result="";
		String[][] board = new String[N][N];
		
		//initialise board with X's to indicate empty spaces
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				board[i][j]="X ";
		
		//place the queens on the board
		for(int i=0; i<N; i++){
			board[state[i].getRow()][state[i].getColumn()]="Q ";
		}
		
		//feed values into the result string
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				result+=board[i][j];
			}
			result+="\n";
		}
		
		return result;
	}
}
