/*
 * @(#) TestPirateIsland.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;

/**
 * 
 * @author juw30 - Julia Wojciechowska
 * @version 0.1 - 30/04/17 - Initial creation /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class TestPirateIsland {

	@Test (expected = EmptyPackException.class)
	public void testCards() 
	throws EmptyPackException{
		PirateIsland pIsland = new PirateIsland(null, null);
		CrewCard card1 = new CrewCard(1, false);
		CrewCard card2 = new CrewCard(2, false);
		CrewCard card3 = new CrewCard(3, true);
		pIsland.addCard(card1);
		pIsland.addCard(card2);
		pIsland.addCard(card3);
		
		pIsland.sortCardsDec();
		assertEquals("1st card should be of value 3", 3, pIsland.getCards().get(0).getValue());
		assertEquals("2nd card should be of value 2", 2, pIsland.getCards().get(1).getValue());
		assertEquals("3rd card should be of value 1", 1, pIsland.getCards().get(2).getValue());
		
		pIsland.sortCardsInc();
		assertEquals("1st card should be of value 1", 1, pIsland.getCards().get(0).getValue());
		assertEquals("2nd card should be of value 2", 2, pIsland.getCards().get(1).getValue());
		assertEquals("3rd card should be of value 3", 3, pIsland.getCards().get(2).getValue());
		
		assertEquals("Szie should be 3", 3, pIsland.size());	
		pIsland.removeCard(card2);
		assertEquals("Szie should be 2", 2, pIsland.size());	
		assertEquals("Should be card1", card1, pIsland.peek());
		pIsland.draw();
		assertEquals("Should be card3", card3, pIsland.getCards().get(0));
		pIsland.draw(); //deletes last element in the pack
		pIsland.draw(); 
	
	}
}
