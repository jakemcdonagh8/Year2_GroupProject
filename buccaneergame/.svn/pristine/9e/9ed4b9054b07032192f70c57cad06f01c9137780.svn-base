/*
 * @(#) TestTreasure.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureHolder;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Tests addressing system.resources.Treasure class
 * @author Robert Buchan Terrey
 * @version 0.1 - 21/02/17 - initial creation /rob27
 * @version 0.2 - ??/03/17 - testTreasureNames /haa14
 * @version 0.3 - 18/03/17 - fixed according to changes made in Treasure (variable type), Added (at)Test overhead /kac12
 * @version 0.4 - 19/03/17 - fixed testValue(), added testOwnership() /kac12
 * @version 0.5 - 19/03/17 - Added testTreasureAssignment, corrected version control, comments & conflicts /haa14
 * @version 0.51 - 25/03/17 - Added multiple ships in testTreasureNames() due to treasure count limitations /kac12
 * @version 1.0 - 05/05/17 - Ready for Initial Release
 */


public class TestTreasure {

	/**
	 * Tests treasure values are being assigned correctly
	 * Author - Harry Adams 
	 */
	@Test
	public void testValue(){
		/*
		 * Diamonds, worth 5 points
		 * Rubies, worth 5 points
		 * Gold bars, worth 4 points
		 * Pearls, worth 3 points
		 * Barrels of rum, worth 2 points
		 * Random, undefined-type treasure worth 100 points
		 */
		TreasureHolder testPort = new Port("Port", new Position(0,0));
		Treasure t1 = new Treasure(TreasureType.DIAMOND, 5, testPort); 
		Treasure t2 = new Treasure(TreasureType.RUBY, 5, testPort); 
		Treasure t3 = new Treasure(TreasureType.GOLD, 4, testPort); 
		Treasure t4 = new Treasure(TreasureType.PEARL, 3, testPort); 
		Treasure t5 = new Treasure(TreasureType.RUM, 2, testPort); 
		Treasure t6 = new Treasure(null, 100, testPort); //should still work
		assertEquals("Value should be 5", t1.getValue(), 5);
		assertEquals("Value should be 5", t2.getValue(), 5);
		assertEquals("Value should be 4", t3.getValue(), 4);
		assertEquals("Value should be 3", t4.getValue(), 3);
		assertEquals("Value should be 2", t5.getValue(), 2);
		assertEquals("Value should be 100", t6.getValue(), 100);
	
	}
	
	
	/**
	 * Test all treasure names, one for each item in ENUM.
	 * Author - Harry Adams
	 */
	@Test
	public void testTreasureNames(){
		Ship testPlayer = createTestShip();
		Ship testPlayer2 = createTestShip();
		Ship testPlayer3 = createTestShip();
		Treasure testDiamond = new Treasure(TreasureType.DIAMOND, 5, testPlayer);
		Treasure testRuby = new Treasure(TreasureType.RUBY, 5, testPlayer);
		Treasure testGold = new Treasure(TreasureType.GOLD, 4, testPlayer2);
		Treasure testPearl = new Treasure(TreasureType.PEARL, 3, testPlayer2);
		Treasure testRum = new Treasure(TreasureType.RUM, 2, testPlayer3);
		 
		assertTrue("Name should be DIAMOND", testDiamond.getType().equals(TreasureType.DIAMOND));
		assertTrue("Name should be RUBY", testRuby.getType().equals(TreasureType.RUBY));
		assertTrue("Name should be GOLD", testGold.getType().equals(TreasureType.GOLD));
		assertTrue("Name should be PEARL", testPearl.getType().equals(TreasureType.PEARL));
		assertTrue("Name should be RUM", testRum.getType().equals(TreasureType.RUM));
	}
	
	/**
	 * Tests tracking of who's the treasure's owner
	 */
	@Test
	public void testOwnership(){
		Player p1 = new Player();
		Player p2 = new Player();
		Ship s1 = new Ship(p1);
		Ship s2 = new Ship(p2);
		Treasure t1 = new Treasure(null, 0, s1);
		Treasure t2 = new Treasure(null, 0, s2);
		t2.setOwner(s1);
		assertTrue("Owner of t1 should be s1.", t1.getOwner() == s1);
		assertTrue("Owner of t2 should be s1.", t2.getOwner() == s1);
	}
	
	/**
	 * Test treasures are being assigned to the correct player
	 * Author - Harry Adams
	 */
	@Test
	public void testTreasureAssignment(){
		Ship testOne = createTestShip();
		Ship testTwo = createTestShip();
		Ship testThree = createTestShip();
		Ship testFour = createTestShip();
		
		Treasure testDiamond = new Treasure(TreasureType.DIAMOND, 5, testOne);
		Treasure testRuby = new Treasure(TreasureType.RUBY, 5, testTwo);
		Treasure testRum = new Treasure(TreasureType.RUM, 2, testThree);
		Treasure testPearl = new Treasure(TreasureType.PEARL, 3, testFour);
		
		
		assertTrue("Player Two should return a ruby", testTwo.removeLastTreasure().equals(testRuby));
		assertTrue("Player Three should return rum", testThree.removeLastTreasure().equals(testRum));
		assertTrue("Player One should return a diamond", testOne.removeLastTreasure().equals(testDiamond));
		assertTrue("Player Four should return a pearl", testFour.removeLastTreasure().equals(testPearl));
	}
	
	/**
	 * Creates a test player, to assign the treasure's owner
	 * Author - Harry Adams
	 */
	private Ship createTestShip(){
		Player player = new Player();
		Ship testPlayer = new Ship(player);
		return testPlayer;
	}
}
