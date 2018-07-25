/*
 * @(#) TestPortInteraction.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.exceptions.NotInHomePortException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.exceptions.UnevenTradeException;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;

/**
 * Tests addressing the system.helpers.PortInteraction class
 * @author Jake McDonagh
 * @version 0.1 - 03/05/17 - initial creation /jam93
 * @version 0.2 - 04/05/17 - Fixed nullpoint exception, added testAnchorBayEligible /jam93
 * @version 1.0 - 05/05/17 - Ready for initial release 
 */

public class TestPortInteraction {
	
	/**
	 * Tests that players are able to deposit held treasures into ports
	 * Author - Jake McDonagh
	 */
	
	
	@Test
	public void testDepositTreasure() throws NotInHomePortException  {
		Player player = new Player();
		Ship ship = new Ship(player);
		player.setShip(ship);
		Position pos = new Position (3, 4);
		PlayerPort port = new PlayerPort("Test Port", null);
		
		player.setHomePort(port);
		port.setPos(pos);
		ship.setPos(pos);
		
		CrewCard cardBlack3 = new CrewCard(3, false);
		CrewCard cardRed2 = new CrewCard(2, true);
		player.addCard(cardBlack3);
		player.addCard(cardRed2);
		
		uk.ac.aber.cs211.group07.system.helpers.PortInteraction.depositTreasure(ship, port);
		assertEquals("Treasure should be succesfully deposited from player ship to port", 0, ship.getTreasureNum());
		assertEquals("Treasure should be succesfully stored in player port", 2, port.getTreasureNum());
	}
	
	/**
	 * For this test, assume that the port is a neutral port, hence why it has crew cards
	 */
	@Test
	public void testisValidTrade() {
		Player player = new Player();
		Ship ship = new Ship(player);
		Ship ship2 = new Ship(player);
		Position pos = new Position (3, 4);	
		Port port = new Port("Port", pos);;
		
		ship.setPos(pos);
	
		CrewCard cardBlack3 = new CrewCard(3, false);
		CrewCard cardRed2 = new CrewCard(2, true);
		CrewCard cardBlack1 = new CrewCard(1, false);
		
		player.addCard(cardBlack3);
		player.addCard(cardRed2);
		port.addCard(cardBlack1);
		port.addCard(cardRed2);
		
		assertEquals("This trade with port 1 should be valid", true, uk.ac.aber.cs211.group07.system.helpers.PortInteraction.isValidTrade(player.getCards(), ship.getTreasureList(), port.getCards(), port.getTreasureList()));
		assertEquals("This trade with port 2 should not be valid", false, uk.ac.aber.cs211.group07.system.helpers.PortInteraction.isValidTrade(player.getCards(), ship2.getTreasureList(), port.getCards(), port.getTreasureList()));
	}
	
	/**
	 * Tests that players are able to trade with ports
	 * @throws UnevenTradeException
	 * @throws TooMuchTreasureException
	 */
	
	@Test
	public void testPerformTrade() throws UnevenTradeException, TooMuchTreasureException{
		Player player = new Player();
		Ship ship = new Ship(player);
		Position pos = new Position (3, 4);	
		Port port = new Port("Port", pos);;
		
		ship.setPos(pos);
	
		CrewCard cardBlack3 = new CrewCard(3, false);
		CrewCard cardRed2 = new CrewCard(2, true);
		CrewCard cardBlack1 = new CrewCard(1, false);
		
		player.addCard(cardBlack3);
		player.addCard(cardRed2);
		port.addCard(cardBlack1);
		port.addCard(cardRed2);
		
		ArrayList<Treasure> toTradeTreasure1 = new ArrayList<Treasure>();
		toTradeTreasure1.add(ship.removeFirstTreasure());
		
		ArrayList<Card> toTradeCard1 = new ArrayList<Card>();
		toTradeCard1.add(cardBlack3);
		
		ArrayList<Treasure> toTradeTreasure2 = new ArrayList<Treasure>();
		toTradeTreasure2.add(port.removeFirstTreasure());
		
		ArrayList<Card> toTradeCard2 = new ArrayList<Card>();
		toTradeCard2.add(cardRed2);
		
		uk.ac.aber.cs211.group07.system.helpers.PortInteraction.performTrade(player, port, toTradeCard1, toTradeTreasure1, toTradeCard2, toTradeTreasure2);
		assertEquals("This trade should succeed", 2, player.getCrewCardNum());
	}
	
	/**
	 * Tests the functionality of isAnchorBay eligible
	 */
	
	@Test
	public void testAnchorBayEligible() {
		Player player = new Player();
		ChanceCard kiddsChart = new ChanceCard(25, "Kidd's Chart");
		
		player.addCard(kiddsChart);
		assertEquals("The player should be eligible for Anchor Bay", true, uk.ac.aber.cs211.group07.system.helpers.PortInteraction.isAnchorBayEligible(player));
		
		player.removeCard(kiddsChart);
		assertEquals("The [;auer should no longer be elgible for Anchor Bay", false, uk.ac.aber.cs211.group07.system.helpers.PortInteraction.isAnchorBayEligible(player)); 	
	}	
}
