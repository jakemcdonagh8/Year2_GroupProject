/*
 * @(#) TestPosition.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import uk.ac.aber.cs211.group07.system.resources.Position;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.resources.Position;

/**
 * Tests addressing system.resources.Position class
 * @author Kamil Cupial
 * @version 0.1 - 17/02/17 - initial creation /kac12
 * @version 0.2 - 17/02/17 - add Position tests /kac12
 * @version 0.3 - 02/04/17 - added Test header above testEquals /kac12
 * @version 1.0 - 05/05/17 - ready for initial release /haa14
 */
public class TestPosition {
	@Test
    public void testXY() {
            Position pos1 = new Position();
            Position pos2 = new Position(10, 15);

            assertEquals("Pos1.x must be 0", 0, pos1.x);
            assertEquals("Pos2.x must be 10", 10, pos2.x);
            assertEquals("Pos1.y must be 0", 0, pos1.y);
            assertEquals("Pos2.y must be 15", 15, pos2.y);
            pos1.x = 15;
            pos1.y = 10;
            assertEquals("Pos1.x must be 15", 15, pos1.x);
            assertEquals("Pos1.y must be 10", 10, pos1.y);
    }
	
	@Test
	public void testEquals(){
		Position pos1 = new Position();
        Position pos2 = new Position(10, 15);
        Position pos3 = new Position(10, 15);
        assertTrue("Pos2 and Pos3 should be equal", pos2.equals(pos3));
        assertTrue("Pos2 and Pos3 should be equal", pos3.equals(pos2));
        assertFalse("Pos2 and Pos1 should not be equal", pos2.equals(pos1));
	}
}
