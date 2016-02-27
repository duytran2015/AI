/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzlegame;

import java.util.ArrayList;

public interface State{
	boolean isGoal(); // determin whether current state is goal
	ArrayList<State> genSuccessors(); // generate successors of current state
	int findCost(); // determine cost from initial state to this state
	public void printState(); // print current state
	public boolean equals(State s); //compare state data
}
