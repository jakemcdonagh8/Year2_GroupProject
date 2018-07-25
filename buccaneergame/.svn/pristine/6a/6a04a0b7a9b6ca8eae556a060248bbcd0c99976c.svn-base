/*
 * @(#) TestShip.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;


/**
 * Tests addressing system.resources.Ship class
 * @author Harry Adams
 * @version 0.1 - 21/02/17 - initial creation /haa14
 * @version 0.2 - 21/03/17 - Created initial testDirection /haa14
 * @version 0.3 - 25/03/17 - fixed exception testing /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class TestShip {

	@Test
	public void testDirection() {
		Ship testShip = createTestShip();
		testShip.setOrientation(Direction.N);
		assertTrue("testShip should be facing North (N)", testShip.getOrientation() == Direction.N);
		testShip.setOrientation(Direction.SE);
		assertTrue("testShip should now be facing Southeast (SE)", testShip.getOrientation() == Direction.SE);
	}
	
	
	@Test(expected = TooMuchTreasureException.class) 
	public void empty() throws Throwable{ 
		Ship testShip = createTestShip();
		Treasure treasure1 = new Treasure(TreasureType.DIAMOND, 5, testShip);
		testShip.addTreasure(treasure1);
	}
	
	
	private Ship createTestShip(){
		Player player = new Player();
		Ship testPlayer = new Ship(player);
		return testPlayer;
	}

}
