package nqueens;

import java.util.*;

public class SimulatedAnnealing {
    private final static int N=17;
    int nodesGenerated;
    int steps;
    private Queen[] startState;
    private Node start;
	
    /**
     * Constructor
     */
    public SimulatedAnnealing(Queen[] s){
        nodesGenerated = 0;
	start = new Node();
	startState = new Queen[N];
		
	for(int i=0; i<N; i++){
            startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
	}
	start.setState(startState);
	start.computeHeuristic();
	//startState();
	}
	
    /**
     * Initializes a pseudorandom configuration of queens on
     * a board
     */
    public void startState(){
	start = new Node();
	startState = new Queen[N];
	Random gen = new Random();
		
	for(int i=0; i<N; i++){
            startState[i] = new Queen(gen.nextInt(N), i);
	}
	start.setState(startState);
	start.computeHeuristic();
    }
	
    /**
     * The simulated annealing algorithm
     */
    public Node simulatedAnneal(double temperature, double step){
	Node currentNode = start;
	double probability;
	int delta;
	double determine;
		
	Node nextNode = new Node();
		
	while(currentNode.getHeuristic()!=0 && temperature > 0){
            //select a random neighbor from currentNode
            nextNode = currentNode.getRandomNeighbor(currentNode);
            nodesGenerated++;
			
            if(nextNode.getHeuristic()==0)
               return nextNode;
			
            delta = currentNode.getHeuristic() - nextNode.getHeuristic();
			
            //currentNode has a higher heuristic
            if(delta > 0){ 
		currentNode = nextNode;
            }
            else{ 
		probability = Math.exp(delta/temperature);
		//Do we want to choose nextNode or stick with currentNode?
		determine = Math.random();
		//choose nextNode
		if(determine <= probability){ 
                    currentNode = nextNode;
                    steps++;
		}
            }
            temperature = temperature - step;
	}
		
	return currentNode;
    }
	
    /**
     * nodesGenerated getter
     */
    public int getNodesGenerated(){
	return nodesGenerated;
    }
    
    /**
     * get number of steps
     */
    public int getSteps(){
        return steps;
    }
	
    /**
     * start getter
     */
    public Node getStartNode(){
	return start;
    }
}
