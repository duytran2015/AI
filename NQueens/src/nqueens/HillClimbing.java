package nqueens;

import java.util.*;

public class HillClimbing {
	private final static int N=17;
	private Queen[] startState;
	private Node start; //start state
	private int nodesGenerated;
        private int steps;
	
	public HillClimbing(){
            start = new Node(); //empty start node
            startState = new Queen[N]; //empty start state
            startState();
            nodesGenerated=0;
	}
	
	public HillClimbing(Queen[] s){
            start = new Node();
            startState = new Queen[N];
            for(int i=0; i<s.length; i++){
		startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
            }
            start.setState(startState);
            start.computeHeuristic();
		
            nodesGenerated=0;
	}
	
	/**
	 * Sets the starting state
	 */
	public void startState(){
            Random gen = new Random();
            for(int i=0; i<N; i++){
		startState[i] = new Queen(gen.nextInt(N), i);
            }
            start.setState(startState);
            start.computeHeuristic();
	}
	 
	/**
	 * The hill climbing algorithm
	 */
	public Node hillClimbing(){
            Node currentNode = start;
		
            while(true){
		ArrayList<Node> successors = currentNode.generateNeighbors(currentNode);
		nodesGenerated+=successors.size();
		steps++;
                        
		Node nextNode = null;
			
		for(int i=0; i<successors.size(); i++){
                    if(successors.get(i).compareTo(currentNode) < 0){
			nextNode = successors.get(i);
                    }
		}
			
                if(nextNode==null)
                    return currentNode;
			
		currentNode = nextNode;
            }
	}
	
	/**
	 * Returns the Node's state
	 */
	public Node getStartNode(){
            return start;
	}
	
	/**
	 * Returns amount of nodes generated
	 */
	public int getNodesGenerated(){
            return nodesGenerated;
	}
        
        /**
         * Return number of steps 
         */
        public int getSteps(){
            return steps;
        }
}
