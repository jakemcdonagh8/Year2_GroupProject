/*
 * @(#) TestFlatIsland.java 1.0 2017/05/05
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
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;
/**
 * 
 * @author juw30 - Julia Wojciechowska
 * @version 0.1 - 30/04/17 - Initial creation /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class TestFlatIsland {

		@Test (expected = EmptyPackException.class)
		public void testCards() 
		throws EmptyPackException{
			FlatIsland flatIsland = new FlatIsland(null, null);
			CrewCard card1 = new CrewCard(1, false);
			CrewCard card2 = new CrewCard(2, false);
			CrewCard card3 = new CrewCard(3, true);
			flatIsland.addCard(card1);
			flatIsland.addCard(card2);
			flatIsland.addCard(card3);
			
			flatIsland.sortCardsDec();
			assertEquals("1st card should be of value 3", 3, flatIsland.getCards().get(0).getValue());
			assertEquals("2nd card should be of value 2", 2, flatIsland.getCards().get(1).getValue());
			assertEquals("3rd card should be of value 1", 1, flatIsland.getCards().get(2).getValue());
			
			flatIsland.sortCardsInc();
			assertEquals("1st card should be of value 1", 1, flatIsland.getCards().get(0).getValue());
			assertEquals("2nd card should be of value 2", 2, flatIsland.getCards().get(1).getValue());
			assertEquals("3rd card should be of value 3", 3, flatIsland.getCards().get(2).getValue());
			
			assertEquals("Szie should be 3", 3, flatIsland.size());	
			flatIsland.removeCard(card2);
			assertEquals("Szie should be 2", 2, flatIsland.size());	
			assertEquals("Should be card1", card1, flatIsland.peek());
			flatIsland.draw();
			assertEquals("Should be card3", card3, flatIsland.getCards().get(0));
			flatIsland.draw(); //deletes last element in the pack
			flatIsland.draw(); 
		}
		
		@Test (expected = TooMuchTreasureException.class)
		public void testTreasure() 
		throws TooMuchTreasureException{
			FlatIsland flatIsland = new FlatIsland(null, null);
			Treasure testRum = new Treasure(TreasureType.RUM, 2, flatIsland);
			Treasure testGold = new Treasure(TreasureType.GOLD, 4, flatIsland);
			flatIsland.addTreasure(testRum);
			assertEquals("Treasure should be added", testRum, flatIsland.getTreasureList().get(0));
			flatIsland.removeTreasure(testRum);
		
			
			flatIsland.sortTreasureInc();
			assertEquals("1. Should be RUM - 2", 2, flatIsland.getTreasureList().get(0).getValue());
			assertEquals("2. Should be GOLD - 4", 4, flatIsland.getTreasureList().get(1).getValue());
			assertEquals("3. Should be DIAMOND - 5", 5, flatIsland.getTreasureList().get(2).getValue());
			System.out.println("sorted inc");
			flatIsland.sortTreasureDec();
			assertEquals("1. Should be DIAMOND - 5", 5, flatIsland.getTreasureList().get(0).getValue());
			assertEquals("2. Should be GOLD - 4", 4, flatIsland.getTreasureList().get(1).getValue());
			assertEquals("3. Should be DIAMOND - 2", 2, flatIsland.getTreasureList().get(2).getValue());
			System.out.println("sorted dec");
			ArrayList<Treasure> treasure = flatIsland.getTreasureList();
			
			flatIsland.removeFirstTreasure();
			assertEquals("Treasure should be removed", 2, flatIsland.getTreasureNum());
			assertEquals("First treasure should be removed", TreasureType.GOLD, flatIsland.getTreasureList().get(0).getType());
			
			flatIsland.removeLastTreasure();
			assertEquals("Treasure should be removed", 1, flatIsland.getTreasureNum());
			assertEquals("Last treasure should be removed", TreasureType.GOLD, treasure.get(0).getType());
			
			for (int index = 0; index <= 21; index++){
				flatIsland.addTreasure(testGold);
			}
		}
		
		
}


