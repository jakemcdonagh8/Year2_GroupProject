/*
 * @(#) StateMachine.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system;

import java.util.HashMap;

import uk.ac.aber.cs211.group07.system.exceptions.InvalidStateException;
import uk.ac.aber.cs211.group07.system.exceptions.NullStateException;

/**
 * 
 * @author Kamil Cupial
 * @version 0.1 - 18/03/17 - initial creation /kac12
 * @version 0.2 - 19/03/17 - setState() now throws InvalidStateException /kac12
 * @version 0.21 - 05/05/17 - Comments updated to match QA9 /haa14
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class StateMachine {
	private HashMap<String, State> states;
	
	/**
	 * Default constructor
	 * Initialises the state hashmap
	 */
	public StateMachine(){
		states = new HashMap<String, State>();
	}
	
	/**
	 * Returns current state
	 * @param name	String for which the state should be returned
	 * @return	State object
	 * @throws NullStateException	no state defined or incorrect string given
	 */
	public State getState(String name) throws NullStateException{
		State st = states.get(name);
		if (st == null)
			throw new NullStateException();
		else
			return st;
	}
	
	/**
	 * Sets a state for given string
	 * @param name	string to set the state for
	 * @param state	State object to set to
	 */
	public void setState(String name, State state) throws InvalidStateException{
		if (isValidState(name, state)){
			states.put(name, state);
		} else {
			throw new InvalidStateException();
		}
	}
	
	/**
	 * Checks if the State given is valid for the String according to the design specification
	 * @param name	String to check
	 * @param checkState	State to check if it's valid with corresponding String
	 */
	public boolean isValidState(String name, State checkState){
		State s = checkState;
		switch(name){
		case "game":
			return (s == State.GAME || s == State.OVER || s == State.PAUSED);
		case "turn":
			return (s == State.T01 || s == State.T02 || s == State.T03 || s == State.T04);
		case "action":
			return (s == State.MOVE || s == State.PREPARE || s == State.ATTMOVE || s == State.TURN ||  s == State.ATTURN);
		case "attloser":
			return (s == State.T01 || s == State.T02 || s == State.T03 || s == State.T04);
		}
		return false;
	}
}
