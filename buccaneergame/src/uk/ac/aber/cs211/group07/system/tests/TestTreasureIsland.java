/*
 * @(#) AnchorBay.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;
/**
 * Treasure Island Test
 * @author Julia Wojciechowska
 * @version 0.1 - 01/05/17 - Initial Creation /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class TestTreasureIsland {

	@Test (expected = EmptyPackException.class)
	public void testCards() 
	throws EmptyPackException{
		TreasureIsland tIsland = new TreasureIsland(null, null);
		ChanceCard card1 = new ChanceCard(1, "Chance Card 1");
		ChanceCard card2 = new ChanceCard(2, "Chance Card 2");
		ChanceCard card3 = new ChanceCard(3, "Chance Card 3");
		tIsland.addCard(card1);
		tIsland.addCard(card2);
		tIsland.addCard(card3);
		assertEquals("Szie should be 3", 3, tIsland.size());	
		tIsland.removeCard(card2);
		assertEquals("Szie should be 2", 2, tIsland.size());	
		assertEquals("Should be card1", card1, tIsland.peek());
		tIsland.draw();
		assertEquals("Should be card3", card3, tIsland.getCards().get(0));
		tIsland.draw(); //deletes last element in the pack
		tIsland.draw(); 
	}
	
	@Test (expected = TooMuchTreasureException.class)
	public void testTreasure() 
	throws TooMuchTreasureException{
		Port p1 = new Port("Treasure Holder", null);
		TreasureIsland tIsland = new TreasureIsland(null, null);
		Treasure testRum = new Treasure(TreasureType.RUM, 2, p1);
		Treasure testGold = new Treasure(TreasureType.GOLD, 4, p1);
		Treasure testDiam = new Treasure(TreasureType.DIAMOND, 5, p1);
		tIsland.addTreasure(testRum);
		assertEquals("Treasure should be added", testRum, tIsland.getTreasureList().get(0));
		tIsland.addTreasure(testDiam);
		tIsland.addTreasure(testGold);
		
		tIsland.sortTreasureInc();
		assertEquals("1. Should be RUM - 2", 2, tIsland.getTreasureList().get(0).getValue());
		assertEquals("2. Should be GOLD - 4", 4, tIsland.getTreasureList().get(1).getValue());
		assertEquals("3. Should be DIAMOND - 5", 5, tIsland.getTreasureList().get(2).getValue());
		
		tIsland.sortTreasureDec();
		assertEquals("1. Should be DIAMOND - 5", 5, tIsland.getTreasureList().get(0).getValue());
		assertEquals("2. Should be GOLD - 4", 4, tIsland.getTreasureList().get(1).getValue());
		assertEquals("3. Should be DIAMOND - 2", 2, tIsland.getTreasureList().get(2).getValue());
		
		ArrayList<Treasure> treasure = tIsland.getTreasureList();
		
		tIsland.removeFirstTreasure();
		assertEquals("Treasure should be removed", 2, tIsland.getTreasureNum());
		assertEquals("First treasure should be removed", TreasureType.GOLD, tIsland.getTreasureList().get(0).getType());
		
		tIsland.removeLastTreasure();
		assertEquals("Treasure should be removed", 1, tIsland.getTreasureNum());
		assertEquals("Last treasure should be removed", TreasureType.GOLD, treasure.get(0).getType());
		
		for (int index = 0; index <= 20; index++){
			tIsland.addTreasure(testGold);
		}
		System.out.println("num of tres" + tIsland.getTreasureNum());
	}
	
	
}
