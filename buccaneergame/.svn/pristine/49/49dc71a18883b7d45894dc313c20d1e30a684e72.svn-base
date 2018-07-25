/*
 * @(#) testIslandInteraction.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;


import uk.ac.aber.cs211.group07.system.helpers.IslandInteraction;
import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * 
 * @author Julia
 * @version 0.1 - 02/05/17 Initial creation /juw30
 * @Version 1.0 - 05/05/17 - Ready for initial release /haa14
 *
 */

public class testIslandInteraction extends IslandInteraction {


	@Test
	public void testReturnCard() {
		Player player = new Player();
		PirateIsland island = new PirateIsland(null, null);
		CrewCard cardVal3 = new CrewCard(3,true);
		CrewCard cardBlack = new CrewCard(3, false);
		CrewCard cardRed = new CrewCard(2, true);
		player.addCard(cardRed);
		player.addCard(cardBlack);
		player.addCard(cardVal3);
		
		returnCard(player, island, cardVal3);
		assertEquals("Card should be taken from player", 2, player.getCrewCardNum());
		assertEquals("Card should be to Pirate Island", 1, island.size());		
	}
	
	@Test
	public void testGetCardsPirateIsland() 
	throws EmptyPackException {
		Player player = new Player();
		PirateIsland island = new PirateIsland(null, null);
		CrewCard cardVal3 = new CrewCard(3,true);
		CrewCard cardBlack = new CrewCard(3, false);
		CrewCard cardRed = new CrewCard(2, true);
		island.addCard(cardRed);
		island.addCard(cardBlack);
		island.addCard(cardVal3);
		
		assertEquals("Cards added to player's hand", 2, getCards(island, player, 2).size());
	}
	
	@Test
	public void testGetCardsFlatIsland() 
	throws EmptyPackException {
		Player player = new Player();
		FlatIsland island = new FlatIsland(null, null);
		CrewCard cardVal3 = new CrewCard(3,true);
		CrewCard cardBlack = new CrewCard(3, false);
		CrewCard cardRed = new CrewCard(2, true);
		island.addCard(cardRed);
		island.addCard(cardBlack);
		island.addCard(cardVal3);
		
		getCards(island, player);
		assertEquals("Player should have 3 cards in hand", 3, player.getCrewCardNum());
	}
	
	@Test
	public void testTransferTreasure() 
	throws TooMuchTreasureException{
		Ship ship = new Ship(null);
		FlatIsland island = new FlatIsland(null, null);
		Treasure testRum = new Treasure(TreasureType.RUM, 2, island);
		island.addTreasure(testRum);

		
		transferTreasure(island, ship);
		assertEquals("Ship should have 2 treasures", 2, ship.getTreasureNum());
		assertEquals("Treasure should be taken from Flat Island", 2, island.getTreasureNum());
		
		ship.removeFirstTreasure();
		transferTreasure(island, ship);
		assertEquals("Gold should be added to the ship", TreasureType.GOLD, ship.getTreasureList().get(1).getType());
		
	}



}
