/*
 * @(#) TestGameEngine.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidDirectionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Tests addressing uk.ac.aber.cs211.group07.system.Board class
 * Engine is shared between all tests! Make sure to .initGame() if needed to reset the game
 * @author Kamil Cupial
 * @version 0.1 - 21/04/17 - initial creation (tests up to isValidTurn) /kac12
 * @version 0.2 - 27/04/17 - added more tests (still needs improvement,
 * 								isValidMove() seems to not work)/kac12
 * @version 0.21 - 29/04/17 - fixed and finished testMovement() /kac12
 * @version 0.3 - 01/05/17 - added missing tests /kac12
 * @version 0.4 - 05/05/17 - added testGetDistanceBetween, testGetDirectionTo and testGetPlayerIndex tests to class /jam93
 * @version 1.0 - 05/05/17 - Ready for Initial Release /haa14
 */
public class TestGameEngine {
	GameEngine engine;
	
	/**
	 * Initialises a GameEngine object as it would be
	 * at the start of the game/match
	 */
	public TestGameEngine(){
		String names[] = new String[4];
		names[0] = "name1";
		names[1] = "name2";
		names[2] = "name3";
		names[3] = "name4";
		engine = new GameEngine();
		engine.initGame(names);
	}
	
	@Test
	public void testStates() {
		engine.setState("action", State.MOVE);
		assertEquals("State should be State.MOVE", State.MOVE, engine.getState("action"));
		engine.setState("action", State.PREPARE);
		assertEquals("State should be State.PREPARE", State.PREPARE, engine.getState("action"));
	}
	
	@Test
	public void testPlayerIncreasing(){
		Player current = engine.getCurrentPlayer();
		State currentst = engine.getState("turn");
		engine.incCurrentPlayer();
		assertNotEquals("Current player should have changed", current, engine.getCurrentPlayer());
		assertNotEquals("Current player state should have changed", currentst, engine.getState("turn"));
		engine.incCurrentPlayer();
		engine.incCurrentPlayer();
		engine.incCurrentPlayer();
		assertEquals("Current player should again be the same", current, engine.getCurrentPlayer());
		assertEquals("Current state should again be the same", currentst, engine.getState("turn"));
	}
	
	@Test
	public void testWinner(){
		assertEquals("There should be no winner at the start of the game", null, engine.getWinner());
		Player p3 = engine.getPlayer(3);
		Treasure winTreas = new Treasure(TreasureType.DIAMOND, 20,p3.getHomePort());
		assertEquals("Player of index 3 should be the winner now", p3, engine.getWinner());
		p3.getHomePort().removeTreasure(winTreas);
		assertEquals("P3 should no longer be the winner", null, engine.getWinner());
	}
	
	@Test
	public void testGetFightLoser(){
		engine.setState("attloser", State.T02);
		Player p1 = engine.getPlayer(1);
		assertEquals("Losing player should be of index 1", p1, engine.getFightLoser());
		p1 = engine.getPlayer(0);
		engine.setState("attloser", State.T01);
		assertEquals("Losing player should be of index 0", p1, engine.getFightLoser());
	}
	
	/**
	 * Tests the 'getPlayerDestinationObject' method
	 * @throws InvalidPositionException
	 */
	@Test
	public void testGetPlayerDestinationObject() throws InvalidPositionException{
		/* Move p1's ship to a port */
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Port p1 = engine.getPlayer(0).getHomePort();
		b.moveMapObject(engine.getPlayer(0).getShip(), p1.getPos()); //port of london
		
		/* Should return the port itself */
		assertEquals("The method should return player's homeport", p1, engine.getPlayerDestinationObject(player1));
		
		/* Check near-island occurence */
		b.moveMapObject(player1.getShip(), new Position(13,13));
		assertEquals("The method should return Treasure Island", b.getTreasureIsland(), engine.getPlayerDestinationObject(player1));
		
		/* Check two ships */
		b.moveMapObject(player1.getShip(), new Position(14, 14));
		Player player2 = engine.getPlayer(1);
		b.moveMapObject(player2.getShip(), new Position(14, 14));
		assertEquals("The method should return player2's ship", player2.getShip(), engine.getPlayerDestinationObject(player1));
		
		/* Check null */
		b.moveMapObject(player1.getShip(), new Position(15,14));
		assertEquals("The method should return null", null, engine.getPlayerDestinationObject(player1));
	}
	
	/**
	 * Tests the isValidTurn method
	 * @throws InvalidPositionException 
	 */
	@Test
	public void testIsValidTurn() throws InvalidPositionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		/* Move to the corner */
		b.moveMapObject(player1.getShip(), new Position(1,1));
		/* Check all cases */
		assertTrue("N should be a valid turn", engine.isValidTurn(player1, Direction.N));
		assertTrue("NE should be a valid turn", engine.isValidTurn(player1, Direction.NE));
		assertTrue("E should be a valid turn", engine.isValidTurn(player1, Direction.E));
		assertFalse("SE should NOT be a valid turn", engine.isValidTurn(player1, Direction.SE));
		assertFalse("S should NOT be a valid turn", engine.isValidTurn(player1, Direction.S));
		assertFalse("SW should NOT be a valid turn", engine.isValidTurn(player1, Direction.SW));
		assertFalse("W should NOT be a valid turn", engine.isValidTurn(player1, Direction.W));
		assertFalse("NW should NOT be a valid turn", engine.isValidTurn(player1, Direction.NW));
	}
	
	/**
	 * Tests turning the player in valid directions
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 */
	@Test
	public void testTurnPlayer() throws InvalidPositionException, InvalidDirectionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Player player2 = engine.getPlayer(1);
		/* Move to the corner */
		b.moveMapObject(player1.getShip(), new Position(2,2));
		b.moveMapObject(player2.getShip(), new Position(2,3));
		engine.turnPlayer(player1, Direction.N);
		engine.turnPlayer(player1, Direction.NE);
		engine.turnPlayer(player1, Direction.E);
		engine.turnPlayer(player1, Direction.SE);
		engine.turnPlayer(player1, Direction.S);
		engine.turnPlayer(player1, Direction.SW);
		engine.turnPlayer(player1, Direction.W);
		engine.turnPlayer(player1, Direction.NW);
		b.moveMapObject(player1.getShip(), new Position(1,1));
		engine.turnPlayer(player1, Direction.N);
		engine.turnPlayer(player1, Direction.NE);
		engine.turnPlayer(player1, Direction.E);
		assertEquals("Direction should be E", player1.getShip().getOrientation(), Direction.E);
		/* return player2 to homeport to not mess up other tests */
		b.moveMapObject(player2.getShip(), player2.getHomePort().getPos());
	}
	
	/**
	 * Tests one of the invalid turns
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 */
	@Test (expected = InvalidDirectionException.class)
	public void testInvalidTurn() throws InvalidPositionException, InvalidDirectionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		/* Move to the corner */
		b.moveMapObject(player1.getShip(), new Position(1,1));
		engine.turnPlayer(player1, Direction.S);
	}
	
	/**
	 * Tests another case of an invalid turn
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 */
	@Test (expected = InvalidDirectionException.class)
	public void testInvalidTurn2() throws InvalidPositionException, InvalidDirectionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		/* Move to the corner */
		b.moveMapObject(player1.getShip(), new Position(20,20));
		engine.turnPlayer(player1, Direction.SE);
	}
	
	/**
	 * Tests another case of an invalid turn (island)
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 */
	@Test (expected = InvalidDirectionException.class)
	public void testInvalidTurn3() throws InvalidPositionException, InvalidDirectionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		/* Move next to an island (diagonally) */
		b.moveMapObject(player1.getShip(), new Position(1,15));
		engine.turnPlayer(player1, Direction.NE);
	}
	
	/**
	 * Tests another case of an invalid turn (occupied port)
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 */
	@Test (expected = InvalidDirectionException.class)
	public void testInvalidTurn4() throws InvalidPositionException, InvalidDirectionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Player player2 = engine.getPlayer(1);
		int x = player2.getShip().getPos().x;
		int y = player2.getShip().getPos().y;
		/* taking some safety measures (due to random ports) */
		if (y == 1){
			y++;
		} else {
			y--;
		}
		/* Move next to a player in a port */
		b.moveMapObject(player1.getShip(), new Position(x, y));
		engine.turnPlayer(player1, Direction.N); //if y == 20
		engine.turnPlayer(player1, Direction.S); //in case y == 1
	}
	
	/**
	 * Tests a sequence of valid moves around the board
	 * @throws InvalidPositionException
	 * @throws InvalidDirectionException
	 * @throws InvalidMoveException 
	 */
	@Test
	public void testMovement() throws InvalidPositionException, InvalidDirectionException, InvalidMoveException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Player player2 = engine.getPlayer(1);
		b.moveMapObject(player2.getShip(), new Position(1,7));
		b.moveMapObject(player1.getShip(), new Position(1, 1));
		engine.turnPlayer(player1, Direction.N);
		
//		////////////////////////////////////////////////////////////////////
		/* ONLY VALID SEQUENCE OF MOVES BELOW DON'T CHANGE IF NOT NECESSARY */
//		////////////////////////////////////////////////////////////////////
		engine.movePlayer(player1, 5, Direction.N); //stop right before port
		/* port + player */
		assertEquals("Moving further should not be possible (player in a port)", false, engine.isValidMove(player1, 1, Direction.N));
		/* move around diagonally too */
		engine.turnPlayer(player1, Direction.NE);
		engine.movePlayer(player1, 10, Direction.NE);
		engine.turnPlayer(player1, Direction.S);
		engine.movePlayer(player1, 3, Direction.S);
		/* test treasure island crash */
		assertFalse("Moving further should not be possible (island)", engine.isValidMove(player1, 1, Direction.S));
		/* move back up once */
		engine.movePlayer(player1, 1, Direction.N);
		b.moveMapObject(player2.getShip(), new Position(11, 13));
		/* test ship near treasure island */
		assertFalse("Moving further should not be possible (player near island)", engine.isValidMove(player1, 1, Direction.S));
//		////////////////////////////////////////////////////////////////////
		/* ONLY VALID SEQUENCE OF MOVES BELOW DON'T CHANGE IF NOT NECESSARY */
		//////////////////////////////////////////////////////////////////////
		
		/* move player2 back */
		b.moveMapObject(player2.getShip(), player2.getHomePort().getPos());
	}
	
	/**
	 * Tests "crossesShipOnMove() and getBlockingShip() method"
	 * @throws InvalidPositionException
	 */
	@Test
	public void testCrossesShipOnMove() throws InvalidPositionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Player player2 = engine.getPlayer(1);
		b.moveMapObject(player1.getShip(), new Position(1,1));
		b.moveMapObject(player2.getShip(), new Position(1,3));
		assertTrue("Player1 should be crossing player2's ship.", engine.crossesShipOnMove(player1, 7, Direction.N));
		assertTrue("Player1 should be crossing player2's ship.", engine.crossesShipOnMove(player1, 3, Direction.N));
		assertFalse("Player1 should NOT be crossing player2's ship.", engine.crossesShipOnMove(player1, 1, Direction.N));
		assertFalse("Player1 should NOT be crossing player2's ship.", engine.crossesShipOnMove(player1, 3, Direction.NE));
		assertEquals("Blocking ship for Player1 should be of Player2", player2.getShip(), engine.getBlockingShip(player1, Direction.N));
		assertEquals("Blocking ship for Player1 should be null", null, engine.getBlockingShip(player1, Direction.S));
		
		/* Return players */
		b.moveMapObject(player1.getShip(), player1.getHomePort().getPos());
		b.moveMapObject(player2.getShip(), player2.getHomePort().getPos());
	}
	
	/**
	 * Tests isValidRetreatingMove method
	 * @throws InvalidPositionException
	 */
	@Test
	public void testIsValidRetreatingMove() throws InvalidPositionException{
		Board b = engine.getBoard();
		Player player1 = engine.getPlayer(0);
		Player player2 = engine.getPlayer(1);
		b.moveMapObject(player1.getShip(), new Position(1,1));
		b.moveMapObject(player2.getShip(), new Position(1,3));
		assertFalse("Player should not be able to retreat on an island", engine.isValidRetreatingMove(player1, 9, Direction.NE));
		assertFalse("Player should not be able to retreat next to an island", engine.isValidRetreatingMove(player1, 7, Direction.NE));
		assertFalse("Player should not be able to retreat to a Port", engine.isValidRetreatingMove(player1, 6, Direction.N));
		assertTrue("Player should be able to retreat further from an Island", engine.isValidRetreatingMove(player1, 6, Direction.NE));
		assertFalse("Player should not be able to retreat by crossing through a player", engine.isValidRetreatingMove(player1, 5, Direction.N));
	}
	
	/**
	 * Tests getDistanceBetween method
	 */
	@Test
	public void testGetDistanceBetween() {		
		Position pos1 = new Position (1, 1);
		Position pos2 = new Position (4, 4);
		assertEquals("The distance between both position 1 and position 2 should be 3 squares", 3, engine.getDistanceBetween(pos1, pos2));
		assertNotEquals("The distance between both position 1 and position 2 should not be 1 square", 1, engine.getDistanceBetween(pos1, pos2));
		pos1 = new Position(3, 3);
		assertEquals("The distance between both position 1 and position 2 should now be 1 square", 1, engine.getDistanceBetween(pos1, pos2));
	}
	
	/**
	 * Tests getDirectionTo method
	 */
	@Test
	public void testGetDirectionTo() {
		Position pos1 = new Position (1, 1);
		Position pos2 = new Position (4, 4);
		assertEquals("The direction needed to advance towards position 2 from position 1 would be NE", Direction.NE, engine.getDirectionTo(pos1, pos2));
		assertNotEquals("The direction needed to advance towards position 2 from position 1 should not be SW", Direction.SW, engine.getDirectionTo(pos1, pos2));
		pos2 = new Position (1, 1);
		assertEquals("As position 1 & 2 are the exact same, the direction should be set to N as it's a special case", Direction.N, engine.getDirectionTo(pos1, pos2));
	}
	
	/**
	 * Tests getPlayerIndex method
	 */
	@Test
	public void testGetPlayerIndex() {
		Player player = engine.getPlayer(0);
		assertEquals("The index of the player should be 0", 0, engine.getPlayerIndex(player));
		assertNotEquals("The index of the player should 1", 1, engine.getPlayerIndex(player));
		player = engine.getPlayer(3);
		assertEquals("The index of the player should now be 3", 3, engine.getPlayerIndex(player));
		
	}
	

}
