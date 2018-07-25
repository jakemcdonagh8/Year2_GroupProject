/*
 * @(#) TestStateMachine.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.StateMachine;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidStateException;
import uk.ac.aber.cs211.group07.system.exceptions.NullStateException;

/**
 * Tests addressing system.StateMachine class
 * @author Kamil Cupial
 * @version 0.1 - 26/04/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class TestStateMachine {

	@Test
	public void testGetSetState() throws InvalidStateException, NullStateException {
		StateMachine sm = new StateMachine();
		sm.setState("game", State.OVER);
		assertEquals("State returned should be State.OVER", State.OVER, sm.getState("game"));
	}
	
	@Test(expected = NullStateException.class)
	public void testGetNullState() throws NullStateException {
		StateMachine sm = new StateMachine();
		sm.getState("game");
	}
	
	@Test(expected = InvalidStateException.class)
	public void testSetInvalidState() throws InvalidStateException {
		StateMachine sm = new StateMachine();
		sm.setState("gaem", State.OVER);
	}
	
	/**
	 * Tests the return value of the isValidState() method
	 */
	@Test
	public void testValidStates(){
		StateMachine sm = new StateMachine();
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> statest = new ArrayList<State>();
		ArrayList<State> statesa = new ArrayList<State>();
		states.add(State.GAME);
		states.add(State.OVER);
		states.add(State.PAUSED);
		statest.add(State.T01);
		statest.add(State.T02);
		statest.add(State.T03);
		statest.add(State.T04);
		statesa.add(State.MOVE);
		statesa.add(State.PREPARE);
		statesa.add(State.ATTMOVE);
		statesa.add(State.ATTURN);
		
		/* "game" */
		for (State s : states){
			assertTrue("State " + s.toString() + " should be a valid"
					+ " 'game' state", sm.isValidState("game", s));
		}
		for (State s : statest){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'game' state", sm.isValidState("game", s));
		}
		for (State s : statesa){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'game' state", sm.isValidState("game", s));
		}
		
		/* "turn" */
		for (State s : states){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'turn' state", sm.isValidState("turn", s));
		}
		for (State s : statest){
			assertTrue("State " + s.toString() + " should be a valid"
					+ " 'turn' state", sm.isValidState("turn", s));
		}
		for (State s : statesa){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'turn' state", sm.isValidState("turn", s));
		}
		
		/* "action" */
		for (State s : states){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'action' state", sm.isValidState("action", s));
		}
		for (State s : statest){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'action' state", sm.isValidState("action", s));
		}
		for (State s : statesa){
			assertTrue("State " + s.toString() + " should be a valid"
					+ " 'action' state", sm.isValidState("action", s));
		}
		
		/* "attloser" */
		for (State s : states){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'attloser' state", sm.isValidState("attloser", s));
		}
		for (State s : statest){
			assertTrue("State " + s.toString() + " should be a valid"
					+ " 'attloser' state", sm.isValidState("attloser", s));
		}
		for (State s : statesa){
			assertFalse("State " + s.toString() + " should NOT be a valid"
					+ " 'attloser' state", sm.isValidState("attloser", s));
		}
	}

}
