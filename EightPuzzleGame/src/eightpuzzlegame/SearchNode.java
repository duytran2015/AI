/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzlegame;

public class SearchNode{
	private State curState;
	private SearchNode parent;
	private int cost; // cost to this state
	private int hCost; // heuristic cost
	private int fCost; // f(n)
	
	// Construct root node
	public SearchNode(State s){
		curState = s;
		parent = null;
		cost = 0;
		hCost = 0;
		fCost = 0;
	}

	// Construct other nodes
	public SearchNode( SearchNode prev, State s, int c, int h){
		parent = prev;
		curState = s;
		cost = c;
		hCost = h;
		fCost = cost + hCost;
	}
	
	// Get current state
	public State getCurState(){
		return curState;
	}

	// Get parent node
	public SearchNode getParent(){
		return parent;
	}

	// Get cost
	public int getCost(){
		return cost;
	}

	// Get h(n)
	public int getHCost(){
		return hCost;
	}
	
	// Get f(n)
	public int getFCost(){
		return fCost;
	}
}
	
