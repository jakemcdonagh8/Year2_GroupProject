/*
 * @(#) TestPorts.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * 
 * @author juw30
 * @version 0.1 - 26/03/17 - Initial Creation /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release
 *
 */

public class TestPorts {
	
	
	@Test
	public void testAddingTreasure(){
		Port p1 = new Port("Test", null);
		Port p2 = new Port("Treasure Holder", null);
		Treasure newTres = new Treasure(TreasureType.DIAMOND, 5, p2);
		p1.addTreasure(newTres);
		assertEquals("Treasure should be added", 2, p1.getTreasureNum());
	}
	
	@Test
	public void testRemovingTreasureFirst(){
		Port p1 = new Port("Test", null);
		ArrayList<Treasure> treasure = p1.getTreasureList();
		
		p1.removeFirstTreasure();
		assertEquals("Treasure should be removed", p1.getTreasureNum(), 1);
		assertEquals("First treasure should be removed", TreasureType.DIAMOND, treasure.get(0).getType());
	}
	
	@Test
	public void testRemovingTreasureLast(){
		Port p1 = new Port("Test", null);
		ArrayList<Treasure> treasure = p1.getTreasureList();
		
		p1.removeLastTreasure();
		assertEquals("Treasure should be removed", 1, p1.getTreasureNum());
		assertEquals("Last treasure should be removed", TreasureType.RUM, treasure.get(0).getType());
	}
	
	@Test
	public void testSize(){
		Port p1 = new Port("Test", null);
		assertEquals("X should be 1", 1, p1.getSize().x);
		assertEquals("Y should be 1", 1, p1.getSize().y);
	}
	
	@Test
	public void testSortInc(){
		Port p1 = new Port("Test", null);
		p1.sortTreasureInc();
		assertEquals("1. Should be DIAMOND - 5", 2, p1.getTreasureList().get(0).getValue());
		assertEquals("1. Should be GOLD - 4", 4, p1.getTreasureList().get(1).getValue());
		assertEquals("1. Should be RUM - 2", 5, p1.getTreasureList().get(2).getValue());
	}
	
	@Test
	public void testSortDec(){
		Port p1 = new Port("Test", null);
		p1.sortTreasureDec();
		assertEquals("1. Should be DIAMOND - 5", 5, p1.getTreasureList().get(0).getValue());
		assertEquals("1. Should be GOLD - 4", 4,p1.getTreasureList().get(1).getValue());
		assertEquals("1. Should be RUM - 2", 2, p1.getTreasureList().get(2).getValue());
	}
	
	@Test
	public void testName(){
		Port p1 = new Port("Test", null);
		assertEquals("Name should be 'Test'", "Test", p1.getName());
	}
	
	@Test
	public void testCards(){
		Port p1 = new Port("Test", null);
		Card card0 = new CrewCard(3,false);
		Card card1 = new CrewCard(2,false);
		Card card2 = new ChanceCard(1, "lorem ipsum");
		
		p1.addCard(card0);
		p1.addCard(card1);
		p1.removeCard(card1);
		p1.addCard(card2);
		assertEquals("Pack size should be 2", 2, p1.size());
		assertEquals("Card's value should be 3", 3, ((CrewCard) p1.peek()).getValue()); //returns card from the top
	}
	
	
	
}
