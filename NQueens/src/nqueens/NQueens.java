package nqueens;

import java.util.*;
import java.text.NumberFormat;

public class NQueens {
        private static final int N=17;

	public NQueens(){
	}
        
	public static void main(String[] args){
            NQueens board = new NQueens();
            int numberOfRuns = 10;
            int hillClimbNodes = 0, annealNodes = 0;
            int hillClimbSteps = 0, annealSteps = 0;
            int hillClimbSuccesses = 0, annealSuccesses = 0;
            long sTime = 0, eTime = 0;
            long hillTime = 0, annealTime = 0;
            NumberFormat fmt = NumberFormat.getPercentInstance();
            

            for(int i=0; i<numberOfRuns; i++){
		Queen[] startBoard = board.generateBoard();
		
                //Print out the stater board
                
                Node test = new Node();
		test.setState(startBoard);
		System.out.println("Stater Board: \n"+test);
                
			
		HillClimbing hillClimber = new HillClimbing(startBoard);
		SimulatedAnnealing anneal = new SimulatedAnnealing(startBoard);
                
		sTime = System.currentTimeMillis();	
		Node hillSolved = hillClimber.hillClimbing();
                eTime = System.currentTimeMillis();
                hillTime += (eTime - sTime);
                
                sTime = System.currentTimeMillis();	
                Node annealSolved = anneal.simulatedAnneal(10, 0.0001);
                eTime = System.currentTimeMillis();
                annealTime += (eTime - sTime);
                
		if(hillSolved.getHeuristic()==0){
                    System.out.println("Hill Climbing Solved:\n"+hillSolved);//
                    //System.out.println("Hill climb steps when succeeded: "+hillClimber.getSteps());
                    hillClimbSuccesses++;
		}
                      
		if(annealSolved.getHeuristic()==0){
                    System.out.println("Anneal Solved:\n"+annealSolved);//
                    annealSuccesses++;
		}
			
		hillClimbNodes += hillClimber.getNodesGenerated();
                hillClimbSteps += hillClimber.getSteps();
		annealNodes += anneal.getNodesGenerated();
                annealSteps += anneal.getSteps();
            }
				
            System.out.println("Hill climbing:\nNodes: "+(double)(hillClimbNodes/numberOfRuns));
            System.out.println("Exectution time: "+hillTime);
            System.out.println("Hill climb successes: "+hillClimbSuccesses);
            System.out.println("Percent successes: "+fmt.format((double)hillClimbSuccesses/(double)numberOfRuns));
            System.out.println("Number of steps: "+hillClimbSteps/numberOfRuns+"\n");
            
            System.out.println("Simulated Annealing:\nNodes: "+(double)(annealNodes/numberOfRuns));
            System.out.println("Simulated Annealing successes: "+annealSuccesses);
            System.out.println("Exectution time: "+annealTime);
            System.out.println("Percent successes: "+fmt.format((double)(annealSuccesses/(double)numberOfRuns)));
            System.out.println("Number of steps: "+annealSteps/numberOfRuns);

	}
	
	/**
	 * The starter board
	 */
	public Queen[] generateBoard(){
		Queen[] start = new Queen[N];
		Random gen = new Random();
		
		for(int i=0; i<N; i++){
			start[i] = new Queen(gen.nextInt(N),i);
		}
		return start;
	}
}
