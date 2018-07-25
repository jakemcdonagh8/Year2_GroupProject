/*
 * @(#) IslandInteraction.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.helpers;

import java.util.LinkedList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;

/**
 * IslandInteraction class helps to manage an interaction between a given Player/Ship and Islands
 * @author Julia Wojciechowska
 * @version 0.1 - 02/05/17 - initial creation /juw30
 * @version 0.2 - 02/05/17 - moved to helpers package. /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 *
 */
public abstract class IslandInteraction {
	
	/**
	 * Returns cards to Pirate Island
	 * @param player
	 * @param island
	 * @param card
	 */
	public static void returnCard(Player player, PirateIsland island, CrewCard card) {
		player.getHand().remove(card);
		island.addCard(card);
	}
	
	/**
	 * Adds a specific number of cards from Pirate Island to player's hand
	 * @param island
	 * @param player
	 * @param num
	 * @return Returns the list of the cards added to player's hand
	 * @throws EmptyPackException
	 */
	public static LinkedList<CrewCard> getCards(PirateIsland island, Player player, int num) 
	throws EmptyPackException {
		LinkedList<CrewCard> crew = new LinkedList<CrewCard>();
		for (int index = 0; index < num; index++){
			if (island.size() != 0) {
				crew.add(island.peek());
				player.addCard(island.draw());
			} else {
				break;
			}
		}
		return crew;
	}
	
	/**
	 * Adds any cards on Flat Island to player's hand
	 * @param island
	 * @param player
	 * @return Returns the list of cards added to player's hand
	 * @throws EmptyPackException
	 */
	public static LinkedList<CrewCard> getCards(FlatIsland island, Player player) 
	throws EmptyPackException {
		LinkedList<CrewCard> crew = new LinkedList<CrewCard>();
		while(island.size() != 0) {
			if (island.size() != 0) {
				crew.add(island.peek());
				player.addCard(island.draw());
			} else {
				break;
			}
		}
		return crew;
	}
	
	/**
	 * Awards the player any treasure on Flat Island, if they only have room for one piece of treasure
	 * then it should be the most valuable treasure.
	 * @param island
	 * @param ship
	 * @return Returns the list of treasure taken from Flat Island
	 * @throws TooMuchTreasureException
	 */
	public static LinkedList<Treasure> transferTreasure(FlatIsland island, Ship ship) 
	throws TooMuchTreasureException {
		LinkedList<Treasure> treasure = new LinkedList<Treasure>();
		int size = island.getTreasureNum();
		if (ship.getTreasureNum() == 0) {
			for (int index = 0; index < size; index++){
				treasure.add(island.getTreasureList().get(index));
				ship.addTreasure(island.getTreasureList().remove(index));
			}
		} else {
			island.sortTreasureDec();
			treasure.add(island.getTreasureList().get(0));
			ship.addTreasure(island.getTreasureList().remove(0));
		}
		return treasure;
	}	

}
