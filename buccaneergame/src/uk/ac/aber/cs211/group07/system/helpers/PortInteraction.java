/*
 * @(#) PortInteraction.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.helpers;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.NotInHomePortException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.exceptions.UnevenTradeException;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;

/**
 * PortInteraction class helps manage interactions between a given Player and
 * Port
 * 
 * @author Julia Wojciechowska - juw30
 *	@version 0.1 - 03/05/17 - initial creation /juw30
 *	@version 0.11 - 03/05/17 - changed both depositTreasure methods to static so that they
 *							   can be used globally /jam93
 *	@version 0.12 - 04/05/17 - changed all methods to static /kac12
 *  @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */ 


public abstract class PortInteraction {

	/**
	 * Deposits treasure in player's Home Port
	 * 
	 * @param player
	 * @param port
	 * @throws NotInHomePortException
	 */
	public static void depositTreasure(Player player, PlayerPort port) throws NotInHomePortException {
		if (player.getShip().isAtHomePort()) {
			if (player.getShip().getTreasureNum() > 0) {
				while (!player.getShip().getTreasureList().isEmpty()) {
					port.addTreasure(player.getShip().removeFirstTreasure());
				}
			} else {
				throw new NotInHomePortException();
			}
		}
	}

	/**
	 * Deposits treasure in player's Home Port
	 * 
	 * @param ship
	 * @param port
	 * @throws NotInHomePortException
	 */
	public static void depositTreasure(Ship ship, PlayerPort port) throws NotInHomePortException {
		if (ship.getOwner().getHomePort() == port) {
			if (ship.getTreasureNum() > 0) {
				while (!ship.getTreasureList().isEmpty()) {
					port.addTreasure(ship.removeFirstTreasure());
				}
			}
		} else {
			throw new NotInHomePortException();
		}
	}

	/**
	 * Calculates the total value of Crew Cards
	 * 
	 * @param cards
	 * @return total value of crew cards
	 */
	public static int calculateCards(ArrayList<Card> cards) {
		int totalValue = 0;
		for (Card c : cards) {
			if (c instanceof CrewCard) {
				totalValue += ((CrewCard) c).getValue();
			}
		}
		return totalValue;
	}

	/**
	 * Calculates the total value of Treasure
	 * 
	 * @param treasure
	 * @return total value of treasure
	 */
	public static int calculateTreasure(ArrayList<Treasure> treasure) {
		int totalValue = 0;
		for (Treasure t : treasure) {
			totalValue += t.getValue();
		}
		return totalValue;

	}

	/**
	 * Checks if trade is valid
	 * 
	 * @param cards1
	 * @param treasure1
	 * @param cards2
	 * @param treasure2
	 * @return true or false if trade is valid
	 */
	public static boolean isValidTrade(ArrayList<Card> cards1, ArrayList<Treasure> treasure1, ArrayList<Card> cards2, 
			ArrayList<Treasure> treasure2) {	
		int totalCrew1 = calculateCards(cards1);
		int totalCrew2 = calculateCards(cards2);
		int totalTres1 = calculateTreasure(treasure1);
		int totalTres2 = calculateTreasure(treasure2);

		int totalValue = 0;
		boolean value = false;
		
		// checks if player wants to trade a Chance Card
		for (Card c1 : cards1) {
			if (c1 instanceof ChanceCard) {
				if (((ChanceCard) c1).getNumber() == 21) {
					totalValue = 5 + totalCrew1;
					// player can trade only crew
					if (totalCrew2 <= totalValue && treasure1.isEmpty() && treasure2.isEmpty()) {
						value = true;
					}
				}
				if (((ChanceCard) c1).getNumber() == 23) {
					totalValue = 5 + totalCrew1 + totalTres1;
					// player can trade crew OR treasure
					if (totalCrew2 + totalTres2 <= totalValue && treasure1.isEmpty() && treasure2.isEmpty()) {
						value = true;
					}
					totalValue = 5 + totalTres1;
					if (totalTres2 <= totalValue && cards1.isEmpty() && cards2.isEmpty()) {
						value = true;
					}
				} else if (((ChanceCard) c1).getNumber() == 24) {
					totalValue = 4 + totalCrew2;
					// player can trade crew OR treasure
					if (totalCrew2 <= totalValue && treasure1.isEmpty() && treasure2.isEmpty()) {
						value = true;
					}
					totalValue = 4 + totalTres1;
					if (totalTres2 <= totalValue && cards1.isEmpty() && cards2.isEmpty()) {
						value = true;
					}
				}
			} else {
				// player can trade crew AND treasure
				if (totalCrew2 <= totalValue || totalCrew1 == totalCrew2) {
					value = true;
				} else if (totalTres2 <= totalValue || totalTres1 == totalTres2) {
					value = true;
				}
			}
		}
		if(cards1.size()==0 && cards2.size()==0 && totalTres1==totalTres2){
			value=true;
		}
		return value;
	}

	/**
	 * Performs trade
	 * 
	 * @param player
	 * @param port
	 * @param cards1
	 * @param treasure1
	 * @param cards2
	 * @param treasure2
	 * @throws UnevenTradeException
	 * @throws TooMuchTreasureException
	 */
	public static void performTrade(Player player, Port port, ArrayList<Card> cards1, ArrayList<Treasure> treasure1, 
			ArrayList<Card> cards2, ArrayList<Treasure> treasure2)
	throws UnevenTradeException, TooMuchTreasureException {
		if (isValidTrade(cards1, treasure1, cards2, treasure2)) {
			for (Card c1 : cards1) {
				if (port instanceof PlayerPort){
					((PlayerPort) port).getOwner().addCard(c1);
				} else {
					port.addCard(c1);
				}
				player.removeCard(c1);
			}
			for (Card c2 : cards2) {
				player.addCard(c2);
				if (port instanceof PlayerPort){
					((PlayerPort) port).getOwner().removeCard(c2);
				} else {
					port.removeCard(c2);
				}
			}
			for (Treasure t1 : treasure1) {
				port.addTreasure(t1);
				player.getShip().removeTreasure(t1);
			}
			for (Treasure t2 : treasure2) {
				player.getShip().addTreasure(t2);
				port.removeTreasure(t2);
			}
		} else throw new UnevenTradeException();
	}

	/**
	 * Checks if the player holds one of the relevant cards (Chance Card 25 &
	 * 26)
	 * 
	 * @param player
	 * @return
	 */
	public static boolean isAnchorBayEligible(Player player){
		boolean value = false;
		for (ChanceCard c : player.getChanceCards()) {
			if (c.getNumber() == 25 || c.getNumber() == 26) {
				value = true;
			} else {
				value = false;
			}
		}
		return value;
	}

}
