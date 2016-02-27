/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzlegame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStarSearch {
	// The starting state, represented as array of length
	// 9 forming 3x3 board

	public static void search(int[] board, char heuristic) {
		SearchNode root = new SearchNode(new EightPuzzleState(board));
		Queue<SearchNode> q = new LinkedList<SearchNode>();
		q.add(root);

		int searchCount = 1; // counter for number of iterations
                int depth = 1;
                
		while (!q.isEmpty()) {
			SearchNode tempNode = (SearchNode) q.poll();
			// if the tempNode is not the goal state
			if (!tempNode.getCurState().isGoal()) {
				// generate tempNode's immediate successors
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors();
				ArrayList<SearchNode> nodeSuccessors = new ArrayList<SearchNode>();

				// Check successors, wrap them in a SearchNode
				// if they've already been evaluated, and if not, add them to
				// the queue
				 
				for (int i = 0; i < tempSuccessors.size(); i++) {
					SearchNode checkedNode;
					// Create a new SearchNode, with tempNode as the parent,
					// tempNode's cost + new cost for this state,
					// and Out of Place h(n) / Manhattan Distance h(n)
					if (heuristic == 'o') {
						checkedNode = new SearchNode(tempNode,
								tempSuccessors.get(i), 
								tempNode.getCost() + tempSuccessors.get(i).findCost(),
								((EightPuzzleState) tempSuccessors.get(i)).getOutOfPlace());
					}
                                        else {
						checkedNode = new SearchNode(tempNode,
								tempSuccessors.get(i), 
								tempNode.getCost() + tempSuccessors.get(i).findCost(),
								((EightPuzzleState) tempSuccessors.get(i)).getManDist());
                                        }

					// Check for repeats before adding the new node
					if (!checkRepeats(checkedNode)) {
						nodeSuccessors.add(checkedNode);
					}
				}
                                

				// If nodeSuccessors is empty continue the loop from the top
				if (nodeSuccessors.size() == 0)
					continue;
                                
				SearchNode lowestNode = nodeSuccessors.get(0);

				// find the lowest f(n) in a node, and then sets that
				// node as the lowest.				
				for (int i = 0; i < nodeSuccessors.size(); i++) {
					if (lowestNode.getFCost() > nodeSuccessors.get(i).getFCost()) {
						lowestNode = nodeSuccessors.get(i);
					}
				}

				int lowestValue = (int) lowestNode.getFCost();

				// Add any nodes that have that same lowest value.
				for (int i = 0; i < nodeSuccessors.size(); i++) {
					if (nodeSuccessors.get(i).getFCost() == lowestValue) {
						q.add(nodeSuccessors.get(i));
					}
				}
                                searchCount++;
			}
			// The goal state has been found. Print the path it took to get to it.
			else {
				// Use a stack to track the path from the starting state to the goal state
				Stack<SearchNode> solutionPath = new Stack<SearchNode>();
				solutionPath.push(tempNode);
				tempNode = tempNode.getParent();

				while (tempNode.getParent() != null){
					solutionPath.push(tempNode);
					tempNode = tempNode.getParent();
				}
				solutionPath.push(tempNode);

				// The size of the stack before looping through and emptying it.
				int loopSize = solutionPath.size();
                                
				for (int i = 0; i < loopSize; i++){
					tempNode = solutionPath.pop();
					//tempNode.getCurState().printState();
					//System.out.println();
					//System.out.println();
				}
				System.out.println("The depth explored: " + tempNode.getCost()); // for this problem f(n) = depth
                                System.out.println("The number of nodes examined: " + searchCount);
                                break;
			}
		}

	}

	// check whether SearchNode has already been evaluated.
	private static boolean checkRepeats(SearchNode n)
	{
		boolean retValue = false;
		SearchNode checkNode = n;

		// While n's parent isn't null, check to see if it's equal to the node
		// we're looking for.
		while (n.getParent() != null && !retValue)
		{
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				retValue = true;
			}
			n = n.getParent();
		}

		return retValue;
	}

}
