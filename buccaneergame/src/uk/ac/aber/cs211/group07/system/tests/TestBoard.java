/*
 * @(#) TestBoard.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameSetup;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;

/**
 * Tests addressing system.Board class
 * @author Kamil Cupial
 * @version 0.1 - 21/04/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class TestBoard {
	Board board;
	Ship invalidShip1;
	Ship invalidShip2;
	Ship invalidShip3;
	Ship invalidShip4;
	
	
	public TestBoard(){
		board = getBoard();
		invalidShip1 = new Ship(board.getPlayer(0));
		invalidShip2 = new Ship(board.getPlayer(0));
		invalidShip3 = new Ship(board.getPlayer(0));
		invalidShip4 = new Ship(board.getPlayer(0));
		invalidShip1.setPos(0,1);
		invalidShip2.setPos(1,0);
		invalidShip3.setPos(5,21);
		invalidShip4.setPos(21,5);
	}
	
	@Test
	public void testGetIslands() {
		assertNotEquals("getTreasureIsland should not be null", null, board.getTreasureIsland());
		assertNotEquals("getFlatIsland should not be null", null, board.getFlatIsland());
		assertNotEquals("getPirateIsland should not be null", null, board.getPirateIsland());
	}
	
	
	/**
	 * Tests addMapObject(), getObjectByPos(), removeMapObject(), moveMapObject() methods
	 */
	@Test
	public void testMapObjectFunctions(){
		Ship testShip1 = new Ship(board.getPlayer(0));
		Ship testShip2 = new Ship(board.getPlayer(1));
		testShip1.setPos(3,3);
		testShip2.setPos(4,4);
		try{
			board.addMapObject(testShip1);
			board.addMapObject(testShip2);
			assertTrue("position 3,3 should contain testShip1", board.getObjectByPos(3, 3).contains(testShip1));
			assertTrue("position 3,3 should contain testShip1", board.getObjectByPos(new Position(3, 3)).contains(testShip1));
			assertTrue("position 4,4 should contain testShip2", board.getObjectByPos(4, 4).contains(testShip2));
			board.moveMapObject(testShip1, new Position(4, 4));
			assertTrue("position 4,4 should now also contain testShip1", board.getObjectByPos(4, 4).contains(testShip1));
			assertFalse("position 3,3 should now not contain testShip1", board.getObjectByPos(3,3).contains(testShip1));
			board.removeMapObject(testShip1);
			board.removeMapObject(testShip2);
		}catch (InvalidPositionException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Tests has...() methods according to req spec
	 */
	@Test
	public void testHasMethods(){
		assertTrue("Position 1,14 should be occupied by a Ship", board.hasShip(1,14));
		assertTrue("Position 1,14 should be occupied by a Ship", board.hasShip(new Position(1,14)));
		assertFalse("Position 1,15 should not be occupied by a Ship", board.hasShip(1,15));
		assertFalse("Position 1,15 should not be occupied by a Ship", board.hasShip(new Position(1,15)));
		assertTrue("Position 1,14 should be occupied by a Port", board.hasPort(1,14));
		assertTrue("Position 1,14 should be occupied by a Port", board.hasPort(new Position(1,14)));
		assertFalse("Position 1,15 should not be occupied by a Port", board.hasPort(1,15));
		assertFalse("Position 1,15 should not be occupied by a Port", board.hasPort(new Position(1,15)));
		assertTrue("Position 3,18 should be occupied by an Island", board.hasIsland(3,18));
		assertTrue("Position 3,18 should be occupied by an Island", board.hasIsland(new Position(3, 18)));
		assertFalse("Position 5,19 should not be occupied by an Island", board.hasIsland(5, 19));
		assertFalse("Position 5,19 should not be occupied by an Island", board.hasIsland(new Position(5, 19)));
		
	}
	
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException1() throws InvalidPositionException{
		board.getObjectByPos(new Position(0,1));
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException2() throws InvalidPositionException{
		board.getObjectByPos(new Position(1,0));
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException3() throws InvalidPositionException{
		board.getObjectByPos(new Position(19,21));
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException4() throws InvalidPositionException{
		board.getObjectByPos(new Position(21,19));
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException5() throws InvalidPositionException{
		board.addMapObject(invalidShip1);
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException6() throws InvalidPositionException{
		board.addMapObject(invalidShip2);
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException7() throws InvalidPositionException{
		board.addMapObject(invalidShip3);
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException8() throws InvalidPositionException{
		board.addMapObject(invalidShip4);
	}
	@Test(expected = InvalidPositionException.class)
	public void testInvalidPosException9() throws InvalidPositionException{
		board.removeMapObject(invalidShip1);
	}

	/**
	 * Gets a prepared Board object
	 * (copied from TestGameSetup)
	 */
	private Board getBoard(){
		/* Prepare player names */
		Player[] array = new Player[5];
		array[0] = new Player();
		array[1] = new Player();
		array[2] = new Player();
		array[3] = new Player();
		array[0].setName("testPlayer1");
		array[1].setName("testPlayer2");
		array[2].setName("testPlayer3");
		array[3].setName("testPlayer4");
		/* Create GameSetup object and set the game up */
		GameSetup setup = new GameSetup(array);
		Board board = setup.setupGame();
		return board;
	}
}
